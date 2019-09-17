package top.tinx.blog.willsblog;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;
import top.tinx.blog.maaper.UserMapper;
import top.tinx.blog.service.impl.UserServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
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

}
