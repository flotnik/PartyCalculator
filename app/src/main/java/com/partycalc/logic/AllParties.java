package com.partycalc.logic;


import com.partycalc.database.PartiesDatabase;
import com.partycalc.database.Party;

import java.util.ArrayList;
import java.util.List;

public class AllParties {

    public static PartiesDatabase db;

    public static final List<Party> ITEMS = new ArrayList<>();

    static {
        // Add some sample items.
        for (int i = 1; i <= 3; i++) {
            ITEMS.add(new Party("party" + i));
        }
    }
}
