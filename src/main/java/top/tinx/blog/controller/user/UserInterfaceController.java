package top.tinx.blog.controller.user;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import top.tinx.blog.bean.JsonData;
import top.tinx.blog.bean.Login;
import top.tinx.blog.bean.User;
import top.tinx.blog.bean.UserEmailCode;
import top.tinx.blog.service.TempUserService;
import top.tinx.blog.service.UserService;
import top.tinx.blog.utils.IPAddressUtil;
import top.tinx.blog.utils.Md5EncryptionUtil;
import top.tinx.blog.utils.RandomNumber;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * 创建人: Wills
 * 创建时间：2019/8/19 17:44
 * 描述: 前后端分离时可以调用的接口，返回json数据
 */
@RestController
@RequestMapping("user")
public class UserInterfaceController {

    @Autowired
    private UserService userService;
    @Autowired
    private JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String sender;
    @Autowired
    private TempUserService tempUserService;

    @GetMapping("findUserById")
    public JsonData findUserById(@RequestParam("userId")int userId){
        User user = userService.findUserByUserId(userId);
        return JsonData.buildSuccess(user);
    }

    @GetMapping("findUserByUserName")
    public JsonData findUserByUserName(@RequestParam("userName")String userName){
        User user = userService.findAllUserInfoByUserName(userName);
        return JsonData.buildSuccess(user);
    }

    @PostMapping("login")
    @ResponseBody
    public JsonData login(HttpServletRequest req, HttpServletResponse resp, @RequestBody Login login){
        String addr = IPAddressUtil.getIpAddr(req);
        System.out.println("IP地址为："+addr);
        System.out.println(login);
        if(login.getPassword() ==null || StringUtils.isEmpty(login.getPassword())){
            return JsonData.buildSuccess("用户名密码错误",-1);
        }
        //将password加密
        login.setPassword(Md5EncryptionUtil.encrypt(login.getPassword(), "", 2));
        User user = userService.findUserByUserNameAndPassword(login.getUserName(),login.getPassword());
        System.out.println(user);
        if(user!=null){
            //修改登陆IP地址
            userService.updateSignInIP(addr,user.getUserId()+"");
            return JsonData.buildSuccess("登陆成功！欢迎回来~！",1);
        }else{
            return JsonData.buildSuccess("用户名密码错误",-1);
        }
    }

    @PostMapping("regEmial")
    @ResponseBody
    public JsonData regEmial(HttpServletRequest req, HttpServletResponse resp, @RequestBody UserEmailCode userEmailCode) {
        System.out.println(userEmailCode);
        //正则表达式，验证是不是有效的邮箱地址
        String regex = "\\w+@\\w+(\\.\\w{2,3})*\\.\\w{2,3}";
        int activationCode = RandomNumber.getRandomNumber(100000);
        //检查邮箱是否重复
        if(userService.getRepeatEmial(userEmailCode.getEmail()) !=0){
            return JsonData.buildSuccess("此邮箱已存在!code:-3",-3);
        }
        //向数据库中插入临时用户的数据，供用户点击注册按钮时验证是否是同一个验证码
        tempUserService.insertTempUser(userEmailCode.getEmail(),activationCode+"");

        if((!StringUtils.isEmpty(userEmailCode.getEmail())) && (userEmailCode.getEmail().matches(regex))){
            //使用javaEmail 来给用户发送邮件
            try{
                MimeMessage message = mailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(message,true);
                helper.setFrom(sender);
                helper.setTo(userEmailCode.getEmail());
                helper.setSubject("Wills博客：注册新的账号");
                StringBuffer sb = new StringBuffer();
                sb.append("<h1>注册新的账号：</h1>").append("<p>您的验证码是：<span style='color:#F00'>"+ activationCode +"</span></p>");
                helper.setText(sb.toString(),true);
                mailSender.send(message);
            }catch (Exception ex){
                ex.printStackTrace();
                return JsonData.buildSuccess("可能没有这个邮箱哦~code:-2",-2);
            }
            return JsonData.buildSuccess("发送邮件成功！："+userEmailCode.getEmail(),1);
        }else{
            return JsonData.buildSuccess("请您正确填写邮箱后再试！code:-1",-1);
        }
    }

    @PostMapping("reg")
    @ResponseBody
    public JsonData reg(HttpServletRequest req, HttpServletResponse resp, @RequestBody User user){
        //正确得到了一个从前台传过来的user信息
        //第一步先验证验证码是否和发送时的一样
        String activationCode;
        try {
            activationCode = tempUserService.getRegActivationCode(user.getEmail());
        }catch (Exception ex){
            ex.printStackTrace();
            return JsonData.buildSuccess("您还没有获取账号激活码，请您尝试获取!code:-1",-1);
        }
        //验证验证码的正确性
        if(!user.getActivationCode().equals(activationCode)){
            return JsonData.buildSuccess("您的验证码填写错误!code:-2",-2);
        }
        //验证表中是否存在这个用户名/邮箱
        if((userService.getEmailCount(user.getEmail()) >=0) ||(userService.getUserNameCount(user.getUserName()) >=1)){
            return JsonData.buildSuccess("用户名/邮箱重复!code:-4",-4);
        }
        //验证成功就开始向数据库插入信息
        try {
            SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String registDate = sm.format(new Date());
            //设置用户的注册时间
            user.setRegistDate(registDate);
            //设置用户注册的IP地址
            String ipAddr = IPAddressUtil.getIpAddr(req);
            user.setLastSignIP(ipAddr);
            //设置用户注册时候的注册码
            user.setActivationCode(activationCode);
            //用户密码加密
            user.setPassword(Md5EncryptionUtil.encrypt(user.getPassword(),"",2));
            userService.insertUser(user);
        }catch (Exception ex){
            ex.printStackTrace();
            return JsonData.buildSuccess("向数据库添加用户失败，请您联系loveing490@qq.com，感谢！code:-3",-3);
        }
        return JsonData.buildSuccess("注册成功，您现在可以使用新账户登陆了！",1);
    }
}