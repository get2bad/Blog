package top.tinx.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BaseController {

    @GetMapping("/")
    public String getIndex(){
        return "foreground/index";
    }

    @GetMapping("/login")
    public String login(){
        return "user/login";
    }

    @GetMapping("/content")
    public String getContent(){
        return "foreground/content/content";
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
    public String manageArtical(){ return "background/artical/manageArtical";}

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
}
