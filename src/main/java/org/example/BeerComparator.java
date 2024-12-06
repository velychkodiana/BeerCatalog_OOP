package org.example;

import java.util.Comparator;

public class BeerComparator implements Comparator<Beer> {
    @Override
    public int compare(Beer beer1, Beer beer2) {
        return beer1.getBeerName().compareTo(beer2.getBeerName());
    }
}
