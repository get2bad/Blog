package top.tinx.blog.controller.baseInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import top.tinx.blog.bean.JsonData;
import top.tinx.blog.service.BackgroundInfoService;

@Controller
@RequestMapping("base")
public class BaseInfoControlller {

    @Autowired
    BackgroundInfoService backgroundInfoService;

    @PostMapping("getAllBaseInfos")
    @ResponseBody
    public JsonData getAllInfos(){
        try{
            return JsonData.buildSuccess(backgroundInfoService.getAllInfos(),1);
        }catch (Exception ex){
            ex.printStackTrace();
            return JsonData.buildError("服务器内部出现错误，已经通知管理员",-1);
        }
    }

    @RequestMapping(value = "getCpuInfo",method = RequestMethod.POST)
    @ResponseBody
    public JsonData getCpuInfo(){
        try{
            return JsonData.buildSuccess(backgroundInfoService.getCpuPercent(),1);
        }catch (Exception ex){
            ex.printStackTrace();
            return JsonData.buildError("服务器内部出现错误，已经通知管理员",-1);
        }
    }

    @PostMapping("getMemInfo")
    @ResponseBody
    public JsonData getMemInfo(){
        try{
            return JsonData.buildSuccess(backgroundInfoService.getMemPercent(),1);
        }catch (Exception ex){
            ex.printStackTrace();
            return JsonData.buildError("服务器内部出现错误，已经通知管理员",-1);
        }
    }

    @PostMapping("getDiskInfo")
    @ResponseBody
    public JsonData getDiskInfo(){
        try{
            return JsonData.buildSuccess(backgroundInfoService.getDiskPpercent(),1);
        }catch (Exception ex){
            ex.printStackTrace();
            return JsonData.buildError("服务器内部出现错误，已经通知管理员",-1);
        }
    }

    @PostMapping("getArticalPie")
    @ResponseBody
    public JsonData getArticalPie(){
        try{
            return JsonData.buildSuccess(backgroundInfoService.getAllArticalPieInfo(),1);
        }catch (Exception ex){
            ex.printStackTrace();
            return JsonData.buildError("服务器内部出现错误，已经通知管理员",-1);
        }
    }
}
