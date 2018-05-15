package ru.otus.homework06.model;

import java.util.*;

public class ATM {
    private Map<Banknote, Slot> slotMap = new HashMap<>();

    public ATM() {
        for(Banknote banknote : Rubles.banknotes) {
            slotMap.put(banknote, new Slot(banknote));
        }
    }

    public void initialLoadCash(BundleOfBanknotes bundle) {
        cleanCash();
        addBundle(bundle);
    }

    public void addBundle(BundleOfBanknotes bundle) {
        for(Banknote banknote : Rubles.banknotes) {
            Slot slot = slotMap.get(banknote);
            slot.add(bundle.getCount(banknote));
        }
    }

    public BundleOfBanknotes countAllBanknotes() {
        BundleOfBanknotes bundle = new BundleOfBanknotes();
        for(Banknote banknote : Rubles.banknotes) {
            Slot slot = slotMap.get(banknote);
            bundle.add(banknote, slot.getCount());
        }
        return bundle;
    }

    public void cleanCash() {
        for(Banknote banknote : Rubles.banknotes) {
            slotMap.get(banknote).setCount(0);
        }
    }

    public BundleOfBanknotes extract(int amount) throws ATMexception {
        BundleOfBanknotes extractedBundle = new BundleOfBanknotes();
        Rubles rubles = new Rubles();
        Iterator<Banknote> nominalMaxToMin = rubles.iterator();

        int amountNeeded = amount - extractedBundle.getAmount();

        while (nominalMaxToMin.hasNext() && (amountNeeded != 0)) {
            Banknote banknote = nominalMaxToMin.next();
            int exists = slotMap.get(banknote).getCount();
            int needed = amountNeeded / banknote.getNominal();
            int toExtract = Math.min(exists, needed);
            slotMap.get(banknote).extract(toExtract);
            extractedBundle.add(banknote, toExtract);
            amountNeeded = amount - extractedBundle.getAmount();
        }
        if(amountNeeded == 0)
            return extractedBundle;
        else {
            addBundle(extractedBundle);
            throw new CannotExtractSuchAmount();
        }
    }

    public class CannotExtractSuchAmount extends ATMexception {}

    public class Slot {
        private Banknote banknote;
        private int count;

        public Slot(Banknote banknote) {
            this.banknote = banknote;
            count = 0;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public Banknote getBanknote() {
            return banknote;
        }

        public void add(Integer counter) {
            this.count += counter;
        }

        public void extract(Integer counter) throws NotEnoughBanknotesException {
            if (this.count >= counter)
                this.count -= counter;
            else
                throw new NotEnoughBanknotesException();
        }

        public class NotEnoughBanknotesException extends ATMexception {
        }
    }

    public class ATMexception extends Throwable {}
}
