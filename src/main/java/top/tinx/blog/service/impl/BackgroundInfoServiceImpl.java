package top.tinx.blog.service.impl;

import kamon.sigar.SigarProvisioner;
import org.hyperic.sigar.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.tinx.blog.bean.ArticalPieInfo;
import top.tinx.blog.bean.BackgroundInfo;
import top.tinx.blog.maaper.ArticalMapper;
import top.tinx.blog.service.*;
import top.tinx.blog.utils.SigarUtil;

import java.text.DecimalFormat;
import java.util.List;

@Service
public class BackgroundInfoServiceImpl implements BackgroundInfoService {

    @Autowired
    private ArticalService articalService;
    @Autowired
    private ArticalMapper articalMapper;
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;
    @Autowired
    private NoteService noteService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private FileService fileService;

    @Override
    public BackgroundInfo getAllInfos() throws Exception {
        //SigarProvisioner.provision();
        Sigar sigar = SigarUtil.getInstance();
        FileSystem[] fileSystemList = sigar.getFileSystemList();
        FileSystemUsage usage = sigar.getFileSystemUsage(fileSystemList[0].getDirName());
        Mem mem = sigar.getMem();
        CpuInfo[] cpuInfoList = sigar.getCpuInfoList();
        NetInterfaceConfig netInterfaceConfig = sigar.getNetInterfaceConfig();
        BackgroundInfo b = new BackgroundInfo();
        b.setArticalCount(articalService.getAllArticalCount());
        b.setCommentCount(commentService.getAllCommentCount());
        b.setUserCount(userService.getAllUserInfo().size());
        b.setNoteCount(noteService.getNoteCount());
        b.setCategoryCount(categoryService.getAllCategorys().size());
        b.setFileCount(fileService.getAllFiles().size());
        b.setViewCount(articalService.getAllViewCount());
        //获取JDK版本
        b.setJdkVersion(System.getProperty("java.version"));
        b.setSystemInfo(System.getProperty("os.name"));
        b.setServerIpAddress(netInterfaceConfig.getAddress());
        b.setSystemArch(System.getProperty("os.arch"));
        b.setCpuType(cpuInfoList[0].getModel());
        b.setCpuSpeed(cpuInfoList[0].getMhz()+"Mhz");
        DecimalFormat df = new DecimalFormat("#.00");
        b.setMemory(df.format(Double.parseDouble(mem.getTotal()+"")/1024/1024/1024)+"GB");
        b.setDisk(usage.getTotal()/1024/1024+"GB");
        return b;
    }

    @Override
    public String getCpuPercent() throws Exception {
        Sigar sigar = SigarUtil.getInstance();
        CpuPerc cpuPerc = sigar.getCpuPerc();
        String s = CpuPerc.format(cpuPerc.getCombined()).toString();
        return s.split("%")[0];
    }

    @Override
    public String getMemPercent() throws Exception {
        Sigar sigar = SigarUtil.getInstance();
        Mem mem = sigar.getMem();
        DecimalFormat df = new DecimalFormat("#.00");
        String value = df.format((Double.parseDouble(mem.getUsed()+"")/mem.getTotal())*100);
        return value;
    }

    @Override
    public String getDiskPpercent() throws Exception {
        Sigar sigar = SigarUtil.getInstance();
        FileSystem[] fileSystemList = sigar.getFileSystemList();
        FileSystemUsage usage = sigar.getFileSystemUsage(fileSystemList[0].getDirName());
        DecimalFormat df = new DecimalFormat("#.00");
        String value = df.format((Double.parseDouble(usage.getUsed()+"")/usage.getTotal())*100);
        return value;
    }

    @Override
    public List<ArticalPieInfo> getAllArticalPieInfo() {
        List<ArticalPieInfo> allInfos = articalMapper.getAllInfos();
        for (ArticalPieInfo allInfo : allInfos) {
            allInfo.setCategoryName(categoryService.getCategoryById(allInfo.getCategoryId()).getCategoryName());
            System.out.println(allInfo);
        }
        return allInfos;
    }
}
