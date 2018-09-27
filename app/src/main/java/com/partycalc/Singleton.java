package com.partycalc;


import com.partycalc.database.Party;

import java.util.ArrayList;
import java.util.List;

public class Singleton {

    public static final List<Party> ITEMS = new ArrayList<>();

    static {
        // Add some sample items.
        for (int i = 1; i <= 3; i++) {
            ITEMS.add(new Party("party" + i));
        }
    }
}
