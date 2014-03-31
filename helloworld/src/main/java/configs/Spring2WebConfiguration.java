package configs;

import controllers.HelloController;
import entities.HelloString;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * Created by InSeok on 2014-03-28.
 */
@Configuration
public class Spring2WebConfiguration extends WebMvcConfigurerAdapter {
    @Bean
    public HelloString helloString() {
        return new HelloString();
    }

    @Bean
    public HelloController helloController() {
        HelloController helloController = new HelloController();
        helloController.setHelloString(helloString());
        return helloController;
    }
}
