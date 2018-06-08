package controller;

import homework10.model.UserDataSet;
import homework11.CacheEngine;
import homework11.CachedDBServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    @Autowired
    private CachedDBServiceImpl cachedDBService;

    @Autowired
    private CacheEngine cacheEngine;

    @RequestMapping
    public ModelAndView index() {
        for (int i=0; i<100; i++) {
            cachedDBService.save(new UserDataSet("name" + i, i));
        }

        UserDataSet resultuds = cachedDBService.load(1L);
        System.out.println(resultuds);
        System.out.println("Cache hit: " + cacheEngine.getHitCount());
        System.out.println("Cache miss: " + cacheEngine.getMissCount());

        resultuds = null;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.gc();

        UserDataSet timedResult = cachedDBService.load(1L);
        System.out.println(timedResult);
        System.out.println("Cache hit: " + cacheEngine.getHitCount());
        System.out.println("Cache miss: " + cacheEngine.getMissCount());

        ModelAndView model = new ModelAndView();
        model.addObject("hitCount", cacheEngine.getHitCount());
        model.addObject("missCount", cacheEngine.getMissCount());
        model.setViewName("index");
        return model;
    }
}
