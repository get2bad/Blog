package top.tinx.blog.controller.artical;

import ch.qos.logback.core.net.SyslogOutputStream;
import org.apache.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import top.tinx.blog.bean.Artical;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 创建人: Wills
 * 创建时间：2019/8/12
 * 描述:
 */
@Controller
public class ArticalController {

    @PostMapping("/newArtical")
    //@ResponseBody
    public String newArtical(@RequestParam("picIntroduceUploads")MultipartFile picIntroduceUploads, Artical artical,
                             HttpServletRequest request){
        System.out.println(picIntroduceUploads.getOriginalFilename()+"\r\n"+artical);
        if(!picIntroduceUploads.isEmpty()){
            try {
                byte[] bytes = picIntroduceUploads.getBytes();
                Path path = Paths.get((new File("")).getAbsolutePath()+"\\upload\\"+picIntroduceUploads.getOriginalFilename());
                Files.write(path,bytes);
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        return "/background/artical/writeArtical";
    }
}
