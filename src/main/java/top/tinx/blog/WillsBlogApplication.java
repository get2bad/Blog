package top.tinx.blog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//配置mapper扫描
@MapperScan("top.tinx.blog.maaper")
public class WillsBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(WillsBlogApplication.class, args);
    }

}
