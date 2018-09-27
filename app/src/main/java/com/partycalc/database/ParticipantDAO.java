package com.partycalc.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Update;

@Dao
public interface ParticipantDAO {

    @Insert
    void insert(Participant... user);

    @Update
    void update(Participant... user);

    @Delete
    void delete(Participant... user);

}
