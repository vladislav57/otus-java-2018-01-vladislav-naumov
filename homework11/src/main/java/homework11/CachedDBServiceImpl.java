package homework11;

import homework10.database.DBService;
import homework10.model.UserDataSet;

public class CachedDBServiceImpl implements DBService {

    private CacheEngine cacheEngine;
    private DBService dbService;

    public CachedDBServiceImpl(CacheEngine cacheEngine, DBService dbService) {
        this.cacheEngine = cacheEngine;
        this.dbService = dbService;
    }

    @Override
    public long save(UserDataSet uds) {
        long id = dbService.save(uds);
        uds.setUserId(id);
        cacheEngine.save(uds);
        return id;
    }

    @Override
    public UserDataSet load(long id) {
        UserDataSet uds = cacheEngine.load(id);
        if(uds == null) {
            uds = dbService.load(id);
            uds.setUserId(id);
            cacheEngine.save(uds);
        }
        return uds;
    }
}
