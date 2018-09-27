package com.partycalc.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Party {

    @PrimaryKey(autoGenerate = true)
    int id;
    @ColumnInfo(name = "name")
    String name;

    //List<Participant> participantList;

    public Party(String name) {
        this.name = name;
        //participantList = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

 /*   public List<Participant> getParticipantList() {
        return participantList;
    }*/

/*    public void addParticipant(Participant p){
        participantList.add(p);
    }*/


}
