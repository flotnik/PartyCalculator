package com.partycalc.logic;


import java.util.ArrayList;
import java.util.List;

public class AllParties {

    public static final List<Party> ITEMS = new ArrayList<>();

    static {
        // Add some sample items.
        for (int i = 1; i <= 3; i++) {
            ITEMS.add(new Party("party" + i));
        }
    }
}
