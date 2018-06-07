package homework11;

import homework10.database.DBService;
import homework10.database.DBServiceImpl;
import homework10.model.UserDataSet;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        DBService service = new DBServiceImpl();
        CacheEngine cacheService = new CacheEngineImpl(1000, 0, false);

        DBService cachedDBService = new CachedDBServiceImpl(cacheService, service);

        for (int i=0; i<100; i++) {
            cachedDBService.save(new UserDataSet("name" + i, i));
        }

        UserDataSet resultuds = cachedDBService.load(1L);
        System.out.println(resultuds);
        System.out.println("Cache hit: " + cacheService.getHitCount());
        System.out.println("Cache miss: " + cacheService.getMissCount());

        resultuds = null;
        Thread.sleep(1000);
        System.gc();

        UserDataSet timedResult = cachedDBService.load(1L);
        System.out.println(timedResult);
        System.out.println("Cache hit: " + cacheService.getHitCount());
        System.out.println("Cache miss: " + cacheService.getMissCount());
    }
}
