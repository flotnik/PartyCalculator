package com.partycalc.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Update;

@Dao
public interface ContributionDAO {

    @Insert
    void insert(Contribution... item);

    @Update
    void update(Contribution... item);

    @Delete
    void delete(Contribution... item);
}
