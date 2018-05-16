package ru.otus.homework07.model;

import org.junit.Assert;
import org.junit.Test;
import ru.otus.homework06.model.ATM;
import ru.otus.homework06.model.BundleOfBanknotes;
import ru.otus.homework06.model.Rubles;

import java.util.Set;

public class ATMDepartmentTest {

    @Test
    public void testCanContainSeveralATM() {
        ATMDepartment department = prepareATMDepartment();
    }

    @Test
    public void testCollectAllRemains() {
        ATMDepartment department = prepareATMDepartment();
        BundleOfBanknotes remains = department.collectAllRemains();
        Assert.assertEquals(remains.getCount(Rubles.banknote100), 60);
        Assert.assertEquals(remains.getCount(Rubles.banknote500), 60);
        Assert.assertEquals(remains.getCount(Rubles.banknote1000), 60);
    }

    @Test
    public void testRestoreInitialState() throws ATM.ATMexception {
        ATMDepartment department = prepareATMDepartment();

        Set<ATM> atmList = department.getATMSet();
        for(ATM atm : atmList) {
            atm.extract(5000);
        }

        department.restoreInitialATMstate();

        BundleOfBanknotes remains = department.collectAllRemains();
        Assert.assertEquals(remains.getCount(Rubles.banknote100), 60);
        Assert.assertEquals(remains.getCount(Rubles.banknote500), 60);
        Assert.assertEquals(remains.getCount(Rubles.banknote1000), 60);
    }

    public ATMDepartment prepareATMDepartment() {
        ATMDepartment department = new ATMDepartment();

        BundleOfBanknotes initialBundle1 = new BundleOfBanknotes();
        initialBundle1.add(Rubles.banknote100, 10);
        initialBundle1.add(Rubles.banknote500, 10);
        initialBundle1.add(Rubles.banknote1000, 10);

        BundleOfBanknotes initialBundle2 = new BundleOfBanknotes();
        initialBundle2.add(Rubles.banknote100, 20);
        initialBundle2.add(Rubles.banknote500, 20);
        initialBundle2.add(Rubles.banknote1000, 20);

        InitialState state1 = new InitialState(initialBundle1);
        InitialState state2 = new InitialState(initialBundle2);

        department.addATM(new ATM(state1));
        department.addATM(new ATM(state1));
        department.addATM(new ATM(state2));
        department.addATM(new ATM(state2));

        return department;
    }
}
