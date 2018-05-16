package ru.otus.homework07.model;

import ru.otus.homework06.model.ATM;
import ru.otus.homework06.model.BundleOfBanknotes;
import ru.otus.homework07.service.CountATMmoney;
import ru.otus.homework07.service.RestoreATMtoInitialState;
import ru.otus.homework07.service.Service;

import java.util.*;

public class ATMDepartment {

    private Set<ATM> atmSet = new HashSet<>();

    public void addATM(ATM atm) {
        atmSet.add(atm);
    }

    public BundleOfBanknotes collectAllRemains() {
        CountATMmoney countATMmoney = new CountATMmoney();
        serviceATMs(countATMmoney);
        return countATMmoney.getCountBundle();
    }

    public Set<ATM> getATMSet() {
        return atmSet;
    }

    public void restoreInitialATMstate() {
        Service restoreInitialState = new RestoreATMtoInitialState();
        serviceATMs(restoreInitialState);
    }

    private void serviceATMs(Service service) {
        for(ATM atm : atmSet) {
            atm.accept(service);
        }
    }
}
