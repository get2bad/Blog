package top.tinx.blog.bean;

import java.util.ArrayList;
import java.util.List;
/**
 * sigar 工具类，自动引用，但是有问题！使用过期注解标注，不能使用！
 */
@Deprecated
public class Infos {

    private long pid;
    private String fqdn;
    private String hostname;
    private double uptime;
    private Cpu cpu = new Cpu();
    private Mem mem = new Mem();
    private Swap swap = new Swap();
    private List<Interface> interfaces = new ArrayList<>(2);
    //private Limits limits = new Limits();


    public static class Cpu {
        private long sys;
        private long total;
        private long user;
    }

    public static class Mem {
        private long total;
        private long used;
        private long free;
    }

    public static class Swap {
        private long total;
        private long used;
        private long free;
    }

    public static class Interface {
        private String name;
        private String type;
        private String address;
    }
}