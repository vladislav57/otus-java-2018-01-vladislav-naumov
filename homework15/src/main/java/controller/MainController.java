package controller;

import homework10.model.UserDataSet;
import homework11.CacheEngine;
import homework11.CachedDBServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/controller")
public class MainController {

    @Autowired
    private CachedDBServiceImpl cachedDBService;

    @Autowired
    private CacheEngine cacheEngine;

    //@Autowired
    //private FrontendService frontendService;

    @RequestMapping("/index")
    public ModelAndView index() {
        for (int i=0; i<100; i++) {
            cachedDBService.save(new UserDataSet("name" + i, i));
        }

        UserDataSet resultuds = cachedDBService.load(1L);

        resultuds = null;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.gc();

        UserDataSet timedResult = cachedDBService.load(1L);

        ModelAndView model = new ModelAndView();
        model.addObject("hitCount", cacheEngine.getHitCount());
        model.addObject("missCount", cacheEngine.getMissCount());
        model.setViewName("index");
        return model;
    }
}
