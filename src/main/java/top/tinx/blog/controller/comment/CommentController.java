package top.tinx.blog.controller.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.tinx.blog.bean.Comment;
import top.tinx.blog.bean.JsonData;
import top.tinx.blog.service.CommentService;
import top.tinx.blog.service.UserService;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @PostMapping("/passComment")
    @ResponseBody
    public JsonData passComment(@RequestBody HashMap<String, String> map){
        try{
            commentService.setCommentStatus(map.get("id"),"1");
            return JsonData.buildSuccess("操作成功！",1);
        }catch (Exception ex){
            ex.printStackTrace();
            return JsonData.buildError("对不起，服务器内部出现错误！",-1);
        }
    }

    @PostMapping("/faildComment")
    @ResponseBody
    public JsonData faildComment(@RequestBody HashMap<String, String> map){
        try{
            commentService.setCommentStatus(map.get("id"),"0");
            return JsonData.buildSuccess("操作成功！",1);
        }catch (Exception ex){
            ex.printStackTrace();
            return JsonData.buildError("对不起，服务器内部出现错误！",-1);
        }
    }

    @PostMapping("/deleteComment")
    @ResponseBody
    public JsonData deleteComment(@RequestBody HashMap<String, String> map){
        try{
            commentService.deleteCommentById(map.get("id"));
            return JsonData.buildSuccess("操作成功！",1);
        }catch (Exception ex){
            ex.printStackTrace();
            return JsonData.buildError("对不起，服务器内部出现错误！",-1);
        }
    }

    @PostMapping("/getAllCommentByArticalId")
    @ResponseBody
    public JsonData getAllCommentByArticalId(@RequestBody HashMap<String, String> map){
        try{
            List<Comment> list = commentService.getAllCommentByArticalId(Integer.parseInt(map.get("id")));
            for (Comment comment : list) {
                comment.setUserName(userService.findUserByUserId(comment.getUserId()).getUserName());
            }
            return JsonData.buildSuccess(list,1);
        }catch (Exception ex){
            ex.printStackTrace();
            return JsonData.buildError("对不起，服务器内部出现错误！",-1);
        }
    }
}
