package top.tinx.blog.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import top.tinx.blog.bean.Login;
import top.tinx.blog.bean.User;
import top.tinx.blog.service.UserService;
import top.tinx.blog.utils.IPAddressUtil;
import top.tinx.blog.utils.Md5EncryptionUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 创建人: Wills
 * 创建时间：2019/8/21 16:02
 * 描述:在没有前后端分离时调用的url来验证用户相关信息
 */
@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("loginForm")
    public String loginForm(HttpServletRequest req, HttpServletResponse resp, Login login){
        String addr = IPAddressUtil.getIpAddr(req);
        System.out.println(login);
        if(login.getPassword() ==null || StringUtils.isEmpty(login.getPassword())){
            req.setAttribute("msg","用户名密码为空！");
            return "login.old";
        }
        //将password加密
        login.setPassword(Md5EncryptionUtil.encrypt(login.getPassword(), "", 2));
        User user = userService.findUserByUserNameAndPassword(login.getUserName(),login.getPassword());
        System.out.println(user);
        //修改登陆IP地址
        userService.updateSignInIP(addr,user.getUserId()+"");
        if(user!=null){
            return "foreground/index";
        }else{
            req.setAttribute("msg","用户名密码错误！！");
            return "login.old";
        }
    }
}
