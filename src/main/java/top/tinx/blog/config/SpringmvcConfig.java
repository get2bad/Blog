package top.tinx.blog.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.tinx.blog.interceptor.LoginInterceptor;

import javax.annotation.Resource;
import java.io.File;

@Configuration
@PropertySource("classpath:otherConfig.properties")
public class SpringmvcConfig implements WebMvcConfigurer {

    @Value("${wills.upload.location}")
    private String articalHeader;

    @Value("${wills.upload.location}")
    private String articalContent;

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
        //第一个方法设置访"问路径前缀，第二个方法设置资源路径
        articalHeader += "/articalHeader/";
        articalContent +="/articalContent/";
        //registry.addResourceHandler("/**","/**/**").addResourceLocations("classpath:/");
        registry.addResourceHandler("/js/**").addResourceLocations("classpath:/js/");
        registry.addResourceHandler("/css/**").addResourceLocations("classpath:/css/");
        registry.addResourceHandler("/articalPic/**").addResourceLocations("classpath:/articalPic/");
        registry.addResourceHandler("/fonts/**").addResourceLocations("classpath:/fonts/");
        registry.addResourceHandler("/pic/**").addResourceLocations("classpath:/pic/");
        //windows上传到D盘下的upload文件下下
        registry.addResourceHandler("/articalContent/**").addResourceLocations("file:"+articalContent);
        registry.addResourceHandler("/articalHeader/**").addResourceLocations("file:"+articalHeader);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

    }
}
