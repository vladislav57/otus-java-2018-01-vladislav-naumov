package jetty.homework11;

import jetty.homework10.model.UserDataSet;

public interface CacheEngine {

    void save(UserDataSet uds);

    UserDataSet load(long id);

    int getHitCount();

    int getMissCount();
}
