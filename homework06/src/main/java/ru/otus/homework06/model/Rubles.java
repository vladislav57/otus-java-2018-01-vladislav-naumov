package ru.otus.homework06.model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Rubles implements Iterable{
    public static final Banknote banknote100 = new Banknote(100);
    public static final Banknote banknote500 = new Banknote(500);
    public static final Banknote banknote1000 = new Banknote(1000);
    public static final Set<Banknote> banknotes = new HashSet<Banknote>();
    static {
        banknotes.add(banknote100);
        banknotes.add(banknote500);
        banknotes.add(banknote1000);
    }

    public Iterator<Banknote> iterator() {
        Iterator iterator = new Iterator() {
            int counter = 0;
            int max = 3;
            @Override
            public boolean hasNext() {
                if(counter < max) {
                    return true;
                }
                return false;
            }

            @Override
            public Object next() {
                counter++;
                switch (counter) {
                    case 1: return banknote1000;
                    case 2: return banknote500;
                    case 3: return banknote100;
                    default: throw new RuntimeException("Banknote iterator problem");
                }
            }
        };
        return iterator;
    }

    public Banknote getBanknote100() {
        return banknote100;
    }

    public Banknote getBanknote500() {
        return banknote500;
    }

    public Banknote getBanknote1000() {
        return banknote1000;
    }
}
