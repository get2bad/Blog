package top.tinx.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class BaseController {

    @GetMapping("/")
    public String getIndex(){
        return "foreground/index";
    }

    @GetMapping("/login")
    public String login(){
        return "foreground/user/login";
    }

    @GetMapping("/search")
    public String search(){
        return "foreground/search/search";
    }

    @GetMapping("/comment")
    public String common(){
        return "foreground/common/comment";
    }

    @GetMapping("/background")
    public String background(){
        return "background/index";
    }

    @GetMapping("/writeArtical")
    public String writeArtical(){
        return "background/artical/writeArtical";
    }

    @GetMapping("/manageArtical")
    public String manageArtical(){
        return "background/artical/manageArticals";
    }

    @GetMapping("/backBase")
    public String backBase(){ return "background/info";}

    @GetMapping("/pageManage")
    public String pageManage(){ return "background/page/pageManage";}

    @GetMapping("/categoryManage")
    public String categoryManage(){ return "background/page/categoryManage";}

    @GetMapping("/commonManage")
    public String commonManage(){ return "background/comment/commonManage";}

    @GetMapping("/setArticalStatus")
    public String setArticalStatus(){ return "background/artical/setArticalStatus";}

    @GetMapping("/userInfo")
    public String userInfo(){ return "background/user/userInfo";}

    @GetMapping("/password")
    public String password(){ return "background/user/password";}

    @GetMapping("/auth")
    public String auth(){ return "background/auth/setAuth";}

    @GetMapping("/websiteSettings")
    public String websiteSettings(){ return "background/website/websiteSettings";}

    @GetMapping("/fileManage")
    public String fileManage(){ return "background/file/fileManage";}

    @GetMapping("/logout")
    public String logout(){ return "redirect:/login";}

    @GetMapping("/userManage")
    public String userManage(){ return "background/user/userManage";}

    @GetMapping("/operationLog")
    public String operationLog(){ return "background/operation/operationLog";}

    @GetMapping("/not_permit")
    public String notPermission(HttpServletRequest request){
        request.setAttribute("errorTitle","哦哦~您的权限貌似不够哦....");
        request.setAttribute("errorMsg","您的权限不够哦，请您重新尝试一下，或者登陆拥有该权限的账号再试!");
        return "/error/noPermission";
    }

    @GetMapping("/error")
    public String error(){
        int a = 1/0;
        return "";
    }
}
