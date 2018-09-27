package com.partycalc.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Update;

@Dao
public interface PartyDAO {

    @Insert
    void insert(Party... user);

    @Update
    void update(Party... user);

    @Delete
    void delete(Party... user);

}
