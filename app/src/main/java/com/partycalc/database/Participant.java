package com.partycalc.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
class Participant {

    @PrimaryKey(autoGenerate = true)
    int id;
    @ColumnInfo
    String name;
    @ColumnInfo
    int size;
    //List<Contribution> contributionList;

    public Participant(String name, int size) {
        this.name = name;
        this.size = size;
        //contributionList = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    /*    public void addContribution(Contribution cont){
        contributionList.add(cont);
    }*/
}
