package com.partycalc.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Update;

@Dao
public interface AllPartiesDAO {

    @Insert
    void insert(AllParties... item);

    @Update
    void update(AllParties... item);

    @Delete
    void delete(AllParties... item);



}
