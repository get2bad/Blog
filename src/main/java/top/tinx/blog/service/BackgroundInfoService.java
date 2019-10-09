package top.tinx.blog.service;

import top.tinx.blog.bean.ArticalPieInfo;
import top.tinx.blog.bean.BackgroundInfo;

import java.util.List;

public interface BackgroundInfoService {

    public BackgroundInfo getAllInfos() throws Exception;

    public String getCpuPercent() throws Exception;

    public String getMemPercent() throws Exception;

    public String getDiskPpercent() throws Exception;

    public List<ArticalPieInfo> getAllArticalPieInfo();
}
