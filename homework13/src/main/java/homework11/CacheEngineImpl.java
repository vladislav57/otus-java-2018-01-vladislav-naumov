package homework11;

import homework10.model.UserDataSet;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

public class CacheEngineImpl implements CacheEngine {

    private int hitCount = 0;
    private int missCount = 0;
    private Map<Long, WeakReference<UserDataSet>> elements = new HashMap<>();

    public CacheEngineImpl() {
    }

    @Override
    public void save(UserDataSet uds) {
        putWeak(uds);
    }

    @Override
    public UserDataSet load(long id) {
        WeakReference<UserDataSet> weakReference = elements.get(id);
        UserDataSet uds = null;
        if(weakReference != null) {
            uds = elements.get(id).get();
        }
        if(uds != null) {
            hitCount++;
            return uds;
        } else {
            missCount++;
            return null;
        }
    }

    @Override
    public int getHitCount() {
        return hitCount;
    }

    @Override
    public int getMissCount() {
        return missCount;
    }

    private void putWeak(UserDataSet uds) {
        WeakReference<UserDataSet> weakReference = new WeakReference<>(uds);
        elements.put(uds.getUserId(), weakReference);
    }

}
