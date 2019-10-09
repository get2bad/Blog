package top.tinx.blog.willsblog;

import kamon.sigar.SigarProvisioner;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;
import top.tinx.blog.bean.Artical;
import top.tinx.blog.maaper.ArticalMapper;
import top.tinx.blog.maaper.UserMapper;
import top.tinx.blog.service.ArticalService;
import top.tinx.blog.service.impl.ArticalServiceImpl;
import top.tinx.blog.service.impl.UserServiceImpl;
import top.tinx.blog.utils.SigarUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class WillsBlogApplicationTests {

    @Test
    public void contextLoads() {
        Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory("classpath:shrio.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("wills","123");
        try{
            subject.login(token);
            System.out.println( "=============> User [" + token.getPrincipal() + "] 登陆成功！." );
            System.out.println( "=============> User [" + subject.hasRole("root") + "] 拥有root权限！！." );
            System.out.println( "=============> User [" + subject.hasRole("admin") + "] 拥有admin权限！！." );
        }catch (AuthenticationException ex){
            System.out.println("登陆失败了，你知道么？");
        }
        Assert.assertEquals(true, subject.isAuthenticated()); //断言用户已经登录
        //6、退出
        subject.logout();
    }


    @Test
    public void test1(){
        //去除字符串所有的标点符号测试
        String str = "~,.!，，D_NAME。！；‘’”“《》**dfs  #$%^&()-+1431221中国123漢字かどうかのjavaを決定";
        str = str.replaceAll("[\\pP‘’“”~]", "");
        str = str.replace(" ","");
        System.out.println(str);
    }

    @Test
    public void test2() throws ParseException {
        //获取当前时间
        SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println((sim.parse(sim.format(new Date()))).toString());
    }

    @Test
    public void test3(){
        Properties props = System.getProperties();
        System.out.println("Java的运行环境版本：" + props.getProperty("java.version"));
        System.out.println("Java的运行环境供应商：" + props.getProperty("java.vendor"));
        System.out.println("Java供应商的URL：" + props.getProperty("java.vendor.url"));
        System.out.println("Java的安装路径：" + props.getProperty("java.home"));
        System.out.println("Java的虚拟机规范版本：" + props.getProperty("java.vm.specification.version"));
        System.out.println("Java的虚拟机规范供应商：" + props.getProperty("java.vm.specification.vendor"));
        System.out.println("Java的虚拟机规范名称：" + props.getProperty("java.vm.specification.name"));
        System.out.println("Java的虚拟机实现版本：" + props.getProperty("java.vm.version"));
        System.out.println("Java的虚拟机实现供应商：" + props.getProperty("java.vm.vendor"));
        System.out.println("Java的虚拟机实现名称：" + props.getProperty("java.vm.name"));
        System.out.println("Java运行时环境规范版本：" + props.getProperty("java.specification.version"));
        System.out.println("Java运行时环境规范供应商：" + props.getProperty("java.specification.vender"));
        System.out.println("Java运行时环境规范名称：" + props.getProperty("java.specification.name"));
        System.out.println("Java的类格式版本号：" + props.getProperty("java.class.version"));
        System.out.println("Java的类路径：" + props.getProperty("java.class.path"));
        System.out.println("加载库时搜索的路径列表：" + props.getProperty("java.library.path"));
        System.out.println("默认的临时文件路径：" + props.getProperty("java.io.tmpdir"));
        System.out.println("一个或多个扩展目录的路径：" + props.getProperty("java.ext.dirs"));
        System.out.println("操作系统的名称：" + props.getProperty("os.name"));
        System.out.println("操作系统的构架：" + props.getProperty("os.arch"));
        System.out.println("操作系统的版本：" + props.getProperty("os.version"));
        System.out.println("文件分隔符：" + props.getProperty("file.separator"));
        //在 unix 系统中是＂／＂
        System.out.println("路径分隔符：" + props.getProperty("path.separator"));
        //在 unix 系统中是＂:＂
        System.out.println("行分隔符：" + props.getProperty("line.separator"));
        //在 unix 系统中是＂/n＂
        System.out.println("用户的账户名称：" + props.getProperty("user.name"));
        System.out.println("用户的主目录：" + props.getProperty("user.home"));
        System.out.println("用户的当前工作目录：" + props.getProperty("user.dir"));
    }
    //使用自建sigar创建Sigar类
    @Test
    public void test4(){
        try {
            Sigar sigar = SigarUtil.getInstance();
            CpuPerc cpu = sigar.getCpuPerc();
            System.out.println(String.valueOf(cpu.getCombined()*100)+"%");
        } catch (SigarException e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用sigar工具类的快捷方法
     */
    @Test
    public void test5() {
        try {
            SigarProvisioner.provision();
            Sigar sigar = new Sigar();
            CpuPerc cpu = sigar.getCpuPerc();
            System.out.println(String.valueOf(cpu.getCombined()*100)+"%");
        } catch (SigarException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
