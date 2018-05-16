package ru.otus.homework07.service;

import ru.otus.homework06.model.ATM;
import ru.otus.homework07.service.Service;

public class RestoreATMtoInitialState implements Service {
    @Override
    public void visit(ATM atm) {
        atm.restoreInitialState();
    }
}
