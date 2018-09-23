package com.partycalc.logic;

import java.util.ArrayList;
import java.util.List;

class Participant {

    String name;
    int size;
    List<Contribution> contributionList;

    public Participant(String name, int size) {
        this.name = name;
        this.size = size;
        contributionList = new ArrayList<>();
    }

    public void addContribution(Contribution cont){
        contributionList.add(cont);
    }
}
