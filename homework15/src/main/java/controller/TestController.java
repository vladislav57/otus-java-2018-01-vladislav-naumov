package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/controller")
public class TestController {

    @RequestMapping("/test")
    public ModelAndView test() {
        ModelAndView model = new ModelAndView();
        model.addObject("hitCount", 100500);
        model.addObject("missCount", 100500);
        model.setViewName("index");
        return model;
    }

    @RequestMapping("/cachecountersjson")
    public String testtext() {
        return "cachecountersjson";
    }
}
