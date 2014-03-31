package configs;

import controllers.HelloController;
import entities.HelloSpring;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by InSeok on 2014-03-28.
 */
public class Spring2WebConfiguration extends WebMvcConfigurerAdapter {
    @Bean
    public HelloSpring helloSpring() {
        return new HelloSpring();
    }

    @Bean(name="/hello")
    public HelloController helloController() {
        HelloController helloController = new HelloController();
        helloController.setHelloSpring(helloSpring());
        return helloController;
    }
}
