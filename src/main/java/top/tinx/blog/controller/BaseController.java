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
}
