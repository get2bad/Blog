package top.tinx.blog.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.tinx.blog.bean.JsonData;
import top.tinx.blog.bean.User;
import top.tinx.blog.service.UserService;

/**
 * 创建人: Wills
 * 创建时间：2019/8/19 17:44
 * 描述:
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

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
}
