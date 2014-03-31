package controllers;

import entities.HelloString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by InSeok on 2014-03-26.
 */
@Controller
public class HelloController {
    @Autowired
    private HelloString helloString;

    @RequestMapping(value="hello", method= RequestMethod.GET)
    public String index(@RequestParam(value="name") String name, ModelMap model) {
        model.addAttribute("message", helloString.sayHello(name));
        return "/WEB-INF/view/hello.jsp";
    }
}
