package jetty.homework12.server;

import jetty.homework11.CacheEngine;

public class CacheEngineBean {
    private static CacheEngineBean ourInstance = new CacheEngineBean();

    public static CacheEngineBean getInstance() {
        return ourInstance;
    }

    private CacheEngine cacheEngine;

    private CacheEngineBean() {
    }

    public CacheEngine getCacheEngine() {
        return cacheEngine;
    }

    public void setCacheEngine(CacheEngine cacheEngine) {
        this.cacheEngine = cacheEngine;
    }
}
