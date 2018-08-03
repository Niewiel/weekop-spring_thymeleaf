package pl.niewiel.weekopspring_thymeleaf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;
import org.thymeleaf.spring5.SpringTemplateEngine;

import java.util.Collections;

@SpringBootApplication
public class WeekopSpringThymeleafApplication extends WebMvcConfigurerAdapter {

    private final SpringTemplateEngine springTemplateEngine;

    private final
    SpringSecurityDialect springSecurityDialect;

    @Autowired
    public WeekopSpringThymeleafApplication(SpringTemplateEngine springTemplateEngine, SpringSecurityDialect springSecurityDialect) {
        this.springTemplateEngine = springTemplateEngine;
        this.springSecurityDialect = springSecurityDialect;
        setDialect();
    }

    public static void main(String[] args) {
        SpringApplication.run(WeekopSpringThymeleafApplication.class, args);
    }


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private void setDialect(){
        springTemplateEngine.setAdditionalDialects(Collections.singleton(springSecurityDialect));
    }

}
