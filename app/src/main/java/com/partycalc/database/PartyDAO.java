package com.partycalc.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface PartyDAO {

    @Insert
    void insert(Party... item);

    @Update
    void update(Party... item);

    @Delete
    void delete(Party... item);

    @Query("select * from party")
    LiveData<List<Party>> getAllParties();
}
