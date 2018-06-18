package homework11;

import homework10.database.DBService;
import homework10.model.UserDataSet;
import homework15.app.MessageSystemContext;
import homework15.messageSystem.Address;
import homework15.messageSystem.MessageSystem;
import homework15.model.CacheStats;
import homework15.model.CachedDBService;

public class CachedDBServiceImpl implements CachedDBService {

    private final Address address;
    private final MessageSystemContext context;
    private CacheEngine cacheEngine;
    private DBService dbService;

    public CachedDBServiceImpl(Address address, MessageSystemContext context, CacheEngine cacheEngine, DBService dbService) {
        this.address = address;
        this.context = context;
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

    @Override
    public Address getAddress() {
        return address;
    }

    @Override
    public MessageSystem getMS() {
        return context.getMessageSystem();
    }

    @Override
    public CacheStats getCacheStats() {
        return new CacheStats(cacheEngine.getHitCount(), cacheEngine.getMissCount());
    }

    public void init() {
        context.getMessageSystem().addAddressee(this);
    }
}
