package top.tinx.blog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.tinx.blog.interceptor.LoginInterceptor;

import javax.annotation.Resource;

@Configuration
public class SpringmvcConfig implements WebMvcConfigurer {

    @Resource
    private LoginInterceptor loginInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /*registry.addInterceptor(loginInterceptor).addPathPatterns("/login","/main/login")
                .excludePathPatterns("/js/**","/css/**","/js/**","/pic/**","/fonts/**", "/articalPic/**");*/
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //需要配置1：----------- 需要告知系统，这是要被当成静态文件的！
        //第一个方法设置访问路径前缀，第二个方法设置资源路径
        //registry.addResourceHandler("/**","/**/**").addResourceLocations("classpath:/");
        registry.addResourceHandler("/js/**").addResourceLocations("classpath:/js/");
        registry.addResourceHandler("/css/**").addResourceLocations("classpath:/css/");
        registry.addResourceHandler("/articalPic/**").addResourceLocations("classpath:/articalPic/");
        registry.addResourceHandler("/fonts/**").addResourceLocations("classpath:/fonts/");
        registry.addResourceHandler("/pic/**").addResourceLocations("classpath:/pic/");
        registry.addResourceHandler("/upload/**").addResourceLocations("classpath:/upload/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

    }
}
