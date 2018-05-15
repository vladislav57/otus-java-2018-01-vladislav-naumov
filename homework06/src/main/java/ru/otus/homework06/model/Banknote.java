package ru.otus.homework06.model;

public class Banknote {
    private int nominal;

    public int getAmount() {
        return nominal;
    }

    public BundleOfBanknotes getBundle() {
        BundleOfBanknotes bundle = new BundleOfBanknotes();
        bundle.add(this, 1);
        return bundle;
    }

    public int getNominal() {
        return nominal;
    }

    public Banknote(int nominal) {
        this.nominal = nominal;
    }
}
