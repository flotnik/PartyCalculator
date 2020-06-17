package com.partycalc.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PartyDAO {
    @Insert
    fun insert(vararg item: Party)

    @Update
    fun update(vararg item: Party)

    @Delete
    fun delete(vararg item: Party)

    @get:Query("select * from party")
    val allParties: LiveData<List<Party>>
}