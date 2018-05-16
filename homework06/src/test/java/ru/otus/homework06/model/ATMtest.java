package ru.otus.homework06.model;

import org.junit.Assert;
import org.junit.Test;
import ru.otus.homework07.model.InitialState;

public class ATMtest {

    @Test
    public void testInput() {
        ATM atm = prepareATM();
    }

    @Test
    public void testRemains() {
        ATM atm = prepareATM();
        BundleOfBanknotes bundle = atm.countAllBanknotes();
        Assert.assertEquals(bundle.getCount(Rubles.banknote100), 10);
        Assert.assertEquals(bundle.getCount(Rubles.banknote500), 10);
        Assert.assertEquals(bundle.getCount(Rubles.banknote1000), 10);
        Assert.assertEquals(bundle.getAmount(), 1000*10 + 500*10 + 100*10);
    }

    @Test
    public void testExtract() throws ATM.ATMexception {
        ATM atm = prepareATM();
        BundleOfBanknotes bundle = atm.extract(5700);
        Assert.assertEquals(bundle.getCount(Rubles.banknote1000), 5);
        Assert.assertEquals(bundle.getCount(Rubles.banknote500), 1);
        Assert.assertEquals(bundle.getCount(Rubles.banknote100), 2);
        Assert.assertEquals(atm.countAllBanknotes().getAmount(), 1000*10 + 500*10 + 100*10 - 5700);
    }

    @Test
    public void testExtracEverything() throws ATM.ATMexception {
        ATM atm = prepareATM();
        BundleOfBanknotes bundle = atm.extract(1000*10 + 500*10 + 100*10);
        Assert.assertEquals(bundle.getCount(Rubles.banknote1000), 10);
        Assert.assertEquals(bundle.getCount(Rubles.banknote500), 10);
        Assert.assertEquals(bundle.getCount(Rubles.banknote100), 10);
        Assert.assertEquals(atm.countAllBanknotes().getAmount(), 0);
    }

    @Test(expected = ATM.ATMexception.class)
    public void testExtractException() throws ATM.ATMexception {
        ATM atm = prepareATM();
        BundleOfBanknotes bundle = atm.extract(100000);
    }

    public ATM prepareATM() {
        BundleOfBanknotes bundle = prepareBundle();
        ATM atm = new ATM(new InitialState(bundle));
        return atm;
    }

    public BundleOfBanknotes prepareBundle() {
        BundleOfBanknotes bundle = new BundleOfBanknotes();
        bundle.add(Rubles.banknote100, 10);
        bundle.add(Rubles.banknote500, 10);
        bundle.add(Rubles.banknote1000, 10);
        return bundle;
    }
}
