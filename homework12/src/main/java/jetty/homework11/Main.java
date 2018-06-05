package jetty.homework11;

import jetty.homework10.database.DBService;
import jetty.homework10.database.DBServiceImpl;
import jetty.homework10.model.UserDataSet;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        DBService service = new DBServiceImpl();
        CacheEngine cachedService = new CacheEngineImpl(service, 1000, 0, false);

        for (int i=0; i<100; i++) {
            cachedService.save(new UserDataSet("name" + i, i));
        }

        UserDataSet resultuds = cachedService.load(1L);
        System.out.println(resultuds);
        System.out.println("Cache hit: " + cachedService.getHitCount());
        System.out.println("Cache miss: " + cachedService.getMissCount());

        resultuds = null;
        Thread.sleep(1000);
        System.gc();

        UserDataSet timedResult = cachedService.load(1L);
        System.out.println(timedResult);
        System.out.println("Cache hit: " + cachedService.getHitCount());
        System.out.println("Cache miss: " + cachedService.getMissCount());
    }
}
