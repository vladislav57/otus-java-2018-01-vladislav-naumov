package homework10.database;

import homework10.model.UserDataSet;
import homework15.messageSystem.Addressee;
import org.springframework.stereotype.Service;

@Service
public interface DBService extends Addressee {

    long save(UserDataSet uds);

    UserDataSet load(long id);

}
