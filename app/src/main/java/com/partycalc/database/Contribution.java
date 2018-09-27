package com.partycalc.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
class Contribution {

    @PrimaryKey(autoGenerate = true)
    int id;
    @ColumnInfo
    String comment;
    @ColumnInfo()
    Float price;

    public Contribution(String comment, Float price) {
        this.comment = comment;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
