package homework10.database;

import homework10.model.UserDataSet;
import org.springframework.stereotype.Service;

@Service
public interface DBService {

    long save(UserDataSet uds);

    UserDataSet load(long id);

}
