package jetty.homework12;

import jetty.homework10.database.DBService;
import jetty.homework10.database.DBServiceImpl;
import jetty.homework10.model.UserDataSet;
import jetty.homework11.CacheEngine;
import jetty.homework11.CacheEngineImpl;
import jetty.homework12.server.JettyServer;

public class Main {
    public static void main(String[] args) throws Exception {
        DBService service = new DBServiceImpl();
        CacheEngine cachedService = new CacheEngineImpl(service, 0, 0, true);

        for (int i=0; i<100; i++) {
            cachedService.save(new UserDataSet("name" + i, i));
        }

        UserDataSet resultuds = null;
        for (long j=1L; j<51L; j++) {
            resultuds = cachedService.load(j);
        }

        resultuds = null;
        Thread.sleep(1000);
        System.gc();

        for (long j=51L; j<101L; j++) {
            resultuds = cachedService.load(j);
        }

        JettyServer jettyServer = new JettyServer(cachedService);
        jettyServer.start();
    }
}
