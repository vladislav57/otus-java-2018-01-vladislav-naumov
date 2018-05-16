package ru.otus.homework07.model;

import ru.otus.homework06.model.BundleOfBanknotes;

public class InitialState {

    private BundleOfBanknotes initialBundle;

    public void setInitialBundle(BundleOfBanknotes initialBundle) {
        this.initialBundle = initialBundle;
    }

    public InitialState(BundleOfBanknotes initialBundle) {
        this.initialBundle = initialBundle;
    }

    public BundleOfBanknotes getInitialBundle() {
        return initialBundle;
    }
}
