package homework10.database;

import homework10.model.UserDataSet;

public interface DBService {

    long save(UserDataSet uds);

    UserDataSet load(long id);

}
