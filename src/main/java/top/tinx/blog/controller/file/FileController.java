package top.tinx.blog.controller.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.tinx.blog.bean.File;
import top.tinx.blog.bean.JsonData;
import top.tinx.blog.service.FileService;

import java.util.List;

@Controller
@RequestMapping("file")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("/getAllFiles")
    @ResponseBody
    public JsonData getAllFiles(){
        try{
            List<File> allFiles = fileService.getAllFiles();
            allFiles.stream().forEach(file -> System.out.println("==========文件路径是:"+file.getFilePath()));
            return JsonData.buildSuccess(allFiles,1);
        }catch (Exception ex){
            ex.printStackTrace();
            return JsonData.buildError("服务器内部错误，已经通知管理员！",-1);
        }
    }

}
