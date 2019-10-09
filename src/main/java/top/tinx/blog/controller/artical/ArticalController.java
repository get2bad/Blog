package top.tinx.blog.controller.artical;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.tinx.blog.bean.*;
import top.tinx.blog.service.*;
import top.tinx.blog.utils.FileUtils;
import top.tinx.blog.utils.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 创建人: Wills
 * 创建时间：2019/8/12
 * 描述:
 */
@Controller
@PropertySource("classpath:otherConfig.properties")
public class ArticalController {

    @Value("${wills.upload.location}")
    private String uploadLocation;

    @Value("${wills.preFileLocation}")
    private String preFileLocation;

    @Autowired
    private ArticalService articalService;

    @Autowired
    private UserService userService;

    @Autowired
    private FileService fileService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CommentService commentService;

    boolean isAll = false;
    int allArticalCount = 0;

    @PostMapping("/newArtical")
    //@ResponseBody
    public String newArtical(@RequestParam("picIntroduceUploads")MultipartFile picIntroduceUploads, Artical artical){
        String preFileLocationn = preFileLocation;
        preFileLocationn += "articalHeader/";
        if(!picIntroduceUploads.isEmpty()){
            try {
                byte[] bytes = picIntroduceUploads.getBytes();
                String title = artical.getArticalTitle();
                //处理title中的相关标点符号
                title = StringUtils.replaceOtherBadThing(title);
                String realLocation = uploadLocation + "/articalHeader/"+ title + "/";
                //判断文件夹是否存在，不存在就创建
                File file = new File(realLocation);
                if(!file.exists()){
                    file.mkdirs();
                }
                String originalFileName = picIntroduceUploads.getOriginalFilename();
                Path path = Paths.get(realLocation+originalFileName);
                Files.write(path,bytes);
                //将artical写入数据库中
                artical.setPicIntroduceUpload(realLocation+originalFileName);
                artical.setPicIntroduceUploadUrl(preFileLocationn+title+"/"+originalFileName);
                //将上传文件的基本信息上传到文件汇总表中
                fileService.insertFile(new top.tinx.blog.bean.File(preFileLocationn+title+"/"+originalFileName, realLocation+originalFileName,FileUtils.getFileType(picIntroduceUploads),"文章介绍图片"));
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                artical.setPostTime(df.format(new Date()));
                articalService.insertArtical(artical);
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        return "background/artical/writeArtical";
    }

    @PostMapping("/artical/upload")
    @ResponseBody
    public FileUpload uploadArticalPic(@RequestParam("articalContentPic")MultipartFile articalContentPic,HttpServletRequest request){
        List<String> data = new ArrayList<String>();
        String preFileLocationn = preFileLocation;
        preFileLocationn += "articalContent/";
        if(!articalContentPic.isEmpty()){
            try {
                byte[] bytes = articalContentPic.getBytes();
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
                String nowTime = df.format(new Date());
                String realLocation = uploadLocation + "/articalContent/"+ nowTime + "/";
                //判断文件夹是否存在，不存在就创建
                File file = new File(realLocation);
                if(!file.exists()){
                    file.mkdirs();
                }
                //(new File("")).getAbsolutePath()+"\\upload\\articalContent\\";
                String originalFileName = articalContentPic.getOriginalFilename();
                Path path = Paths.get(realLocation+originalFileName);
                Files.write(path,bytes);
                data.add(preFileLocationn+nowTime+"/"+originalFileName);
                //将上传文件的基本信息上传到文件汇总表中
                fileService.insertFile(new top.tinx.blog.bean.File(preFileLocationn+nowTime+"/"+originalFileName, realLocation+originalFileName,FileUtils.getFileType(articalContentPic),"文章内部图片"));
                //System.out.println(preFileLocationn+nowTime+"/"+originalFileName);
            }catch (Exception ex){
                ex.printStackTrace();
                return FileUpload.buildError();
            }
        }
        return FileUpload.buildSuccesss(data);
    }

    @PostMapping("/artical/getAllJudgeArtical")
    @ResponseBody
    public JsonData getAllJudgeArtical(){
        List<Artical> list = articalService.getAllJudgeArtical();
        for (Artical artical: list){
            artical.setUserName(userService.findUserByUserId(artical.getUserId()).getUserName());
        }
        if(!list.isEmpty()){
            return JsonData.buildSuccess(list,1);
        }else{
            return JsonData.buildError("恭喜老板，您的站点所有文章已经审核完毕！",-1);
        }
    }

    @PostMapping("/artical/pass")
    @ResponseBody
    public JsonData passArtical(@RequestBody HashMap<String, String> map){
        try{
            articalService.passArtical(map.get("id"));
            return JsonData.buildSuccess("此篇文章通过操作成功！",1);
        }catch (Exception ex){
            ex.printStackTrace();
            return JsonData.buildError("对不起，内部出现了一些小错误，等待解决",-1);
        }
    }

    @PostMapping("/artical/view")
    @ResponseBody
    public JsonData viewArtical(@RequestBody HashMap<String, String> map ){
        return JsonData.buildSuccess("后台已经收到您的查看请求",1);
    }

    @PostMapping("/artical/faild")
    @ResponseBody
    public JsonData faildArtical(@RequestBody HashMap<String, String> map){
        return JsonData.buildSuccess("此篇文章不通过操作成功！",1);
    }

    @RequestMapping(value = "/artical/getAllPass",method = RequestMethod.POST)
    @ResponseBody
    public JsonData getAllPassArtical(@RequestBody HashMap<String, String> map){
        try{
            String jsonString = map.get("option");

            List<Artical> list = null;
            int start = Integer.parseInt(map.get("start"));
            allArticalCount = articalService.getAllArticalCount();
            if(jsonString !=null &&jsonString.equals("all")){
                List<Artical> all = articalService.getAllPassArtical(0, allArticalCount);
                for (Artical artical : all) {
                    artical.setUserName(userService.findUserByUserId(artical.getUserId()).getUserName());
                    artical.setCategoryName(categoryService.getCategoryById(artical.getCategoryId()).getCategoryName());
                    artical.setArticalCommentCount(commentService.getCommentCountByArticalId(Integer.toString(artical.getArticalId())));
                }
                return JsonData.buildSuccess(all,1);
            }
            if(start+7>allArticalCount){
                if(allArticalCount-start<0){
                    return null;
                }
                list = articalService.getAllPassArtical(start,allArticalCount-start);
                isAll = true;
            }else{
                list = articalService.getAllPassArtical(start,start+7);
            }
            for (Artical artical : list){
                artical.setUserName(userService.findUserByUserId(artical.getUserId()).getUserName());
                artical.setCategoryName(categoryService.getCategoryById(artical.getCategoryId()).getCategoryName());
                artical.setArticalCommentCount(commentService.getCommentCountByArticalId(Integer.toString(artical.getArticalId())));
                if(isAll){
                    artical.setIsAll(1);
                }else{
                    artical.setIsAll(0);
                }
            }
            return JsonData.buildSuccess(list,1);
        }catch (Exception ex){
            ex.printStackTrace();
            return JsonData.buildError("对不起，内部出现了一些小错误，等待解决",-1);
        }
    }

    @PostMapping("/artical/backJudgeArtical")
    @ResponseBody
    public JsonData backJudgeArtical(@RequestBody HashMap<String, String> map){
        try{
            articalService.backJudgeArtical(map.get("id"));
            return JsonData.buildSuccess("退回成功！",1);
        }catch (Exception ex){
            ex.printStackTrace();
            return JsonData.buildError("对不起，内部出现了一些小错误，等待解决",-1);
        }
    }

    @GetMapping("/content/{id}.html")
    public String getContent(@PathVariable String id,HttpServletRequest request){
        Artical artical = articalService.findArticalById(Integer.parseInt(id));
        artical.setCategoryName(categoryService.getCategoryById(artical.getCategoryId()).getCategoryName());
        commentService.setViewCountNum(artical.getArticalId()+"");
        request.setAttribute("artical",artical);
        return "foreground/content/content";
    }

    @PostMapping("/artical/submitComment")
    @ResponseBody
    public JsonData comment(@RequestBody HashMap<String, String> map ,HttpServletRequest request){
        try{
            String articalId = map.get("articalId");
            String comment = map.get("comment");
            Artical artical = articalService.findArticalById(Integer.parseInt(articalId));
            artical.setCategoryName(categoryService.getCategoryById(artical.getCategoryId()).getCategoryName());
            request.setAttribute("artical",artical);
            String usrId = map.get("UserId");
            Comment comment1 = new Comment();
            if(!org.springframework.util.StringUtils.isEmpty(usrId)){
                comment1.setUserId(Integer.parseInt(usrId));
            }else{
                comment1.setUserId(0);
            }
            comment1.setArticalId(Integer.parseInt(articalId));
            comment1.setCommentContent(comment);
            comment1.setCommentStatus(0);
            comment1.setCommentPostTime(StringUtils.getNowTime());
            commentService.insertComment(comment1);
            return JsonData.buildSuccess("提交评论成功,等待审核中·····",1);
        }catch (Exception ex){
            return JsonData.buildError("服务器内部出现错误！",-1);
        }

    }

    @PostMapping("/artical/getAllComment")
    @ResponseBody
    public JsonData getAllComment(){
        try{
            List<Comment> allComment = commentService.getAllComment();
            for (Comment comment : allComment) {
                comment.setUserName(userService.findUserByUserId(comment.getUserId()).getUserName());
                comment.setArticalName(articalService.findArticalById(comment.getArticalId()).getArticalTitle());
            }
            return JsonData.buildSuccess(allComment,1);
        }catch (Exception ex){
            return JsonData.buildError("服务器内部出现错误！",-1);
        }
    }

    @PostMapping("/artical/findArticalByConditions")
    @ResponseBody
    public JsonData findArticalByConditions(@RequestBody HashMap<String, String> map){
        String category = map.get("category");
        String articalAuth = map.get("articalAuth");
        String judgeStatus = map.get("judgeStatus");
        String articalContent = map.get("articalContent");
        /*
        String sqlStr = "where 1=1 AND category_id = "+category+" AND status = "+judgeStatus;
        if(articalContent !="null"){
            sqlStr += " AND articalTitle like %"+articalContent+"%";
        }*/
        try{
            List<Artical> list = articalService.getArticalByConditions(category,judgeStatus,articalContent);
            for (Artical artical : list) {
                artical.setUserName(userService.findUserByUserId(artical.getUserId()).getUserName());
                artical.setArticalCommentCount(commentService.getCommentCountByArticalId(Integer.toString(artical.getArticalId())));
            }
            return JsonData.buildSuccess(list,1);
        }catch (Exception e){
            e.printStackTrace();
            return JsonData.buildError("服务器内部出现错误，已经通知管理员！",-1);
        }
    }
}
