package jetty.homework11;

import jetty.homework10.database.DBService;
import jetty.homework10.model.UserDataSet;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class CacheEngineImpl implements CacheEngine {

    private static final long TIME_THRESHOLD_MS = 5L;
    private int hitCount = 0;
    private int missCount = 0;
    private Map<Long, WeakReference<UserDataSet>> elements = new HashMap<>();
    private DBService dbService;

    private final Timer timer = new Timer();
    private final long lifeTimeMs;
    private final long idleTimeMs;
    private final boolean isEternal;

    public CacheEngineImpl(DBService service, long lifeTimeMs, long idleTimeMs, boolean isEternal) {
        this.dbService = service;
        this.lifeTimeMs = lifeTimeMs > 0 ? lifeTimeMs : 0;
        this.idleTimeMs = idleTimeMs > 0 ? idleTimeMs : 0;
        this.isEternal = lifeTimeMs == 0 && idleTimeMs == 0 || isEternal;
    }

    @Override
    public void save(UserDataSet uds) {
        long id = dbService.save(uds);
        uds.setUserId(id);
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
            uds = dbService.load(id);
            uds.setUserId(id);
            putWeak(uds);
            return uds;
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

        Long currentTime = System.currentTimeMillis();
        if (!isEternal) {
            if (lifeTimeMs != 0) {
                TimerTask lifeTimerTask = getTimerTask(uds.getUserId(), currentTime + lifeTimeMs);
                timer.schedule(lifeTimerTask, lifeTimeMs);
            }
            if (idleTimeMs != 0) {
                TimerTask idleTimerTask = getTimerTask(uds.getUserId(), currentTime + idleTimeMs);
                timer.schedule(idleTimerTask, idleTimeMs, idleTimeMs);
            }
        }
    }

    private TimerTask getTimerTask(final Long key, Long time) {
        return new TimerTask() {
            @Override
            public void run() {
                UserDataSet element = elements.get(key).get();
                if (element == null || isT1BeforeT2(time, System.currentTimeMillis())) {
                    elements.remove(key);
                    this.cancel();
                }
            }
        };
    }

    private boolean isT1BeforeT2(long t1, long t2) {
        return t1 < t2 + TIME_THRESHOLD_MS;
    }
}
