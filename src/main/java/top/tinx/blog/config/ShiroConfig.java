package top.tinx.blog.config;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 创建人: Wills
 * 创建时间：2019/8/19 18:08
 * 描述:
 */
@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        //设置SecurityManager
        shiroFilter.setSecurityManager(securityManager);


        //需要登陆的接口，如果没有登陆，则会跳转到这个URL（如果不是前后端分离，则会用这个URL进行跳转）
        shiroFilter.setLoginUrl("/login");
        //如果登陆成功，则会跳转到这个页面(仅限非前后端分离)
        shiroFilter.setSuccessUrl("/");
        //如果没有权限，则会跳转到这个URL（仅限非前后端分离）
        shiroFilter.setUnauthorizedUrl("/not_permit");

        //拦截器拦截路径
        //坑1!如果使用HashMap的话，会拦截时有时无。因为HashMap是无序的,使用LinkedHashMap有序的可以拦截
        //一定要导入servlet下的Filter
        Map<String, Filter> roleMap = new LinkedHashMap<String,Filter>();
        //将自定Filter放入roleMap中
        roleMap.put("roleOrFilter",new CustomRolesOrAuthorizationFilter());
        //设置Filter
        shiroFilter.setFilters(roleMap);
        Map<String,String> map = new LinkedHashMap<String,String>();

        //静态资源防止拦截
        map.put("/articalPic/**","anon");
        map.put("/css/**","anon");
        map.put("/js/**","anon");
        map.put("/fonts/**","anon");
        map.put("/pic/**","anon");
        map.put("/templates/**","anon");

        //退出过滤器
        map.put("/logout","logout");
        //匿名可以访问
        //map.put("/*","anon");
        //需要有video_play权限才能访问本URL
        //map.put("/authc/video/play_record","perms[video_play]");
        //登录用户可以访问的
        //map.put("/user/**","authc");
        //管理员角色才可以访问
        map.put("/background/**","roles[admin,root]");
        //有编辑权限才可以访问的
        //map.put("/video/update","perms[video_update]");

        //坑2 因为拦截是顺序执行，所以要将/**拦截放到最下面
        map.put("/**","anon");
        shiroFilter.setFilterChainDefinitionMap(map);

        return shiroFilter;
    }

    /**
     * DefaultSecurityManager 用于配置一些shiro框架属性
     * @return
     */
    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //设置自定义Realm
        securityManager.setRealm(getCustomRealm());
        //设置SessionManager
        securityManager.setSessionManager(getSessionManager());
        //配置RedisCacheManager
        securityManager.setCacheManager(getCacheManager());
        return securityManager;
    }

    /**
     * 自定shiroRealm
     * @return
     */
    @Bean
    public CustomRealm getCustomRealm(){
        CustomRealm customRealm = new CustomRealm();
        //设置加密器
        customRealm.setCredentialsMatcher(getHashedCredentialsMatcher());
        return customRealm;
    }

    /**
     * 密码加密，双重MD5加密
     * @return
     */
    @Bean
    public HashedCredentialsMatcher getHashedCredentialsMatcher(){
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName("md5");
        //散列次数，双重md5加密
        matcher.setHashIterations(2);
        return matcher;
    }

    /**
     * 配置RedisManager
     * @return
     */
    @Bean
    public RedisManager getRedisManager(){
        RedisManager redisManager = new RedisManager();
        redisManager.setHost("localhost");
        redisManager.setPort(6379);
        return redisManager;
    }

    /**
     * 设置CacheManager
     * @return
     */
    @Bean
    public RedisCacheManager getCacheManager(){
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(getRedisManager());
        //设置过期时间
        redisCacheManager.setExpire(30);
        return redisCacheManager;
    }

    /**
     * SessionManager 注册，使Session更灵活，具有生命周期
     * @return
     */
    @Bean
    public SessionManager getSessionManager(){
        //DefaultSessionManager： 默认实现，常用于javase
        //ServletContainerSessionManager: web环境
        //DefaultWebSessionManager：常用于自定义实现
        CustomSessionManager customSessionManager = new CustomSessionManager();
        //设置持久化SessionDao 用户登陆Session
        customSessionManager.setSessionDAO(getRedisSessionDao());
        //30分钟不操作Session过期 默认30分钟自动销毁Session
        customSessionManager.setGlobalSessionTimeout(30*60*1000);
        return customSessionManager;
    }

    /**
     * 设置RedisSessionDao
     * 为啥session也要持久化？
     * 重启应用，用户无感知，可以继续以原先的状态继续访问
     * @return
     */
    @Bean
    public RedisSessionDAO getRedisSessionDao(){
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(getRedisManager());
        return redisSessionDAO;
    }

    /**
     * 作用：管理shiro一些bean的生命周期 即bean初始化 与销毁
     * @return
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 作用：加入注解的使用，不加入这个AOP注解不生效(shiro的注解 例如
     * @RequiresGuest 游客可以访问的注解
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor()
    {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new
                AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * 作用: 用来扫描上下文寻找所有的Advistor(通知器), 将符合条件的Advisor应用到切入点的Bean中，需
     * 要在LifecycleBeanPostProcessor创建后才可以创建
     * @return
     */
    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator=new
                DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setUsePrefix(true);
        return defaultAdvisorAutoProxyCreator;
    }
}
