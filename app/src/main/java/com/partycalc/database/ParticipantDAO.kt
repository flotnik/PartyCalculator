package com.partycalc.database

import androidx.room.*

@Dao
interface ParticipantDAO {
    @Insert
    fun insert(vararg item: Participant?): LongArray?

    @Update
    fun update(vararg item: Participant?)

    @Delete
    fun delete(vararg item: Participant?)

    @get:Query("select * from participant")
    val allParticipants: List<Participant?>?
}