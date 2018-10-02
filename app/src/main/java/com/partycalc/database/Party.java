package com.partycalc.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

@Entity
public class Party implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    int id;
    String name;
    @TypeConverters(DateConverter.class)
    Date date;

    public static final Parcelable.Creator<Party> CREATOR = new Parcelable.Creator<Party>() {
        public Party createFromParcel(Parcel in) {
            return new Party(in);
        }

        @Override
        public Party[] newArray(int size) {
            return new Party[size];
        }
    };

    public Party(String name, Date date) {
        this.name = name;
        this.date = date;
    }

    public Party(Parcel in){
        this.id = in.readInt();
        this.name = in.readString();
        this.date = (Date) in.readValue(ClassLoader.getSystemClassLoader());
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(getId());
        dest.writeString(getName());
        dest.writeValue(getDate());
    }
}
