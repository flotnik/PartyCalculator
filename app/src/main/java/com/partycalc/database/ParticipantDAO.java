package com.partycalc.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface ParticipantDAO {

    @Insert
    long[] insert(Participant... item);

    @Update
    void update(Participant... item);

    @Delete
    void delete(Participant... item);

    @Query("select * from participant")
    List<Participant> getAllParticipants();
}
