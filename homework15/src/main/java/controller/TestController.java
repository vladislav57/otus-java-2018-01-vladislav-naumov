package controller;

import homework15.app.FrontendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/controller")
public class TestController {

    @Autowired
    FrontendService frontendService;

    @RequestMapping("/test")
    public String test() {
        frontendService.requestCacheStats();
        return "index";
    }

    @RequestMapping("/cachecountersjson")
    public ModelAndView testtext() {
        ModelAndView model = new ModelAndView();
        model.addObject("hitCount", 200);
        model.addObject("missCount", 300);
        model.setViewName("pseudo_json");
        return model;
    }
}
