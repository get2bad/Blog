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
import top.tinx.blog.service.UserService;
import top.tinx.blog.utils.IPAddressUtil;
import top.tinx.blog.utils.Md5EncryptionUtil;
import top.tinx.blog.utils.RandomNumber;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        if((!StringUtils.isEmpty(userEmailCode.getEmail())) && (userEmailCode.getEmail().matches(regex))){
            //测试javaEmail
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
                return JsonData.buildSuccess("可能没有这个邮箱哦~",-2);
            }
            return JsonData.buildSuccess("发送邮件成功！："+userEmailCode.getEmail(),1);
        }else{
            return JsonData.buildSuccess("请您正确填写邮箱后再试！",-1);
        }
    }

    @PostMapping("reg")
    @ResponseBody
    public JsonData reg(HttpServletRequest req, HttpServletResponse resp, @RequestBody User user){
        System.out.println(user);
        return JsonData.buildSuccess("后台已经收到了注册信息，正在验证合理性",1);
    }
}