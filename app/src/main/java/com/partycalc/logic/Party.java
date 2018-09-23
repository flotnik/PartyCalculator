package com.partycalc.logic;

import java.util.ArrayList;
import java.util.List;

public class Party {

    String name;
    List<Participant> participantList;

    public Party(String name) {
        this.name = name;
        participantList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Participant> getParticipantList() {
        return participantList;
    }

    public void addParticipant(Participant p){
        participantList.add(p);
    }
}
