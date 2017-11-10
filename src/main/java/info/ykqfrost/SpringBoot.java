package info.ykqfrost;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author YaoKeQi
 * @date 2017/10/24
 */

@MapperScan("info/ykqfrost/dao")
@ComponentScan("info.ykqfrost")
@SpringBootApplication
public class SpringBoot extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(SpringBoot.class,args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // TODO Auto-generated method stub
        return builder.sources(SpringBoot.class);
    }
}
