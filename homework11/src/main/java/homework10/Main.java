package homework10;

import homework10.model.PhoneDataSet;
import homework10.model.UserDataSet;
import homework10.database.DBService;
import homework10.database.DBServiceImpl;
import homework10.model.AddressDataSet;

import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        DBService service = new DBServiceImpl();
        AddressDataSet ads = new AddressDataSet("some street");
        PhoneDataSet phone1 = new PhoneDataSet("123");
        PhoneDataSet phone2 = new PhoneDataSet("456");
        Set<PhoneDataSet> phoneSet = new HashSet<>();
        phoneSet.add(phone1);
        phoneSet.add(phone2);
        UserDataSet uds = new UserDataSet( "testname", 22, phoneSet, ads);
        ads.setUserDataSet(uds);
        phone1.setUserDataSet(uds);
        phone2.setUserDataSet(uds);
        service.save(uds);
        UserDataSet resultuds = service.load(1L);
        System.out.println(uds);
        System.out.println(resultuds);
    }
}
