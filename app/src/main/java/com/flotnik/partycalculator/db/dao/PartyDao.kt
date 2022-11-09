package com.flotnik.partycalculator.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.flotnik.partycalculator.db.entities.Party

@Dao
interface PartyDao {
    @Query("SELECT * FROM party")
    fun getAll(): LiveData<List<Party>>

    @Query("SELECT * FROM party WHERE id IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<Party>

    @Query("SELECT * FROM party WHERE name LIKE :name")
    fun findByName(name: String): Party

    @Insert
    fun insert(party: Party)

    @Delete
    fun delete(party: Party)
}