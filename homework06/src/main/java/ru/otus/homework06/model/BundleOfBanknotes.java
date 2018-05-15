package ru.otus.homework06.model;

import java.util.HashMap;
import java.util.Map;

public class BundleOfBanknotes{
    private Map<Banknote, Integer> count = new HashMap<Banknote, Integer>();

    public BundleOfBanknotes() {
        count = new HashMap<Banknote, Integer>();
        for(Banknote banknote : Rubles.banknotes) {
            count.put(banknote, 0);
        }
    }

    public int getAmount() {
        int count = 0;
        for(Map.Entry<Banknote, Integer> entry: this.count.entrySet()) {
            count += entry.getKey().getNominal() * entry.getValue();
        }
        return count;
    }

    public BundleOfBanknotes add(Banknote banknote, Integer counter) {
        if(this.count.containsKey(banknote)) {
            this.count.put(banknote, this.count.get(banknote) + counter);
        } else {
            this.count.put(banknote, counter);
        }
        return this;
    }

    public int getCount(Banknote banknote) {
        return count.get(banknote);
    }
}
