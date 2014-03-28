package controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.HelloSpring;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by InSeok on 2014-03-26.
 */
public class HelloController implements Controller {
    private HelloSpring helloSpring;

    public void setHelloSpring(HelloSpring helloSpring) {
        this.helloSpring = helloSpring;
    }

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String name = request.getParameter("name");
        String message = helloSpring.sayHello(name);
        Map<String,Object> model = new HashMap<String, Object>();
        model.put("message", message);
        return new ModelAndView("/WEB-INF/view/hello.jsp", model);
    }
}
