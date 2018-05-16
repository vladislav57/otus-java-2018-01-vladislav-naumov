package ru.otus.homework07.service;

import ru.otus.homework06.model.ATM;
import ru.otus.homework06.model.BundleOfBanknotes;
import ru.otus.homework07.service.Service;

public class CountATMmoney implements Service {

    private BundleOfBanknotes countBundle = new BundleOfBanknotes();

    @Override
    public void visit(ATM atm) {
        atm.countAllBanknotes(countBundle);
    }

    public BundleOfBanknotes getCountBundle() {
        return countBundle;
    }
}
