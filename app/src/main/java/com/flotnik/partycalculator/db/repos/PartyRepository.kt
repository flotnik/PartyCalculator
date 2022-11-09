package com.flotnik.partycalculator.db.repos

import androidx.lifecycle.LiveData
import com.flotnik.partycalculator.db.dao.PartyDao
import com.flotnik.partycalculator.db.entities.Party
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PartyRepository(private val dao: PartyDao) {
    val allParties: LiveData<List<Party>> = dao.getAll()

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun insert(party: Party) {
        coroutineScope.launch(Dispatchers.IO) {
            dao.insert(party)
        }
    }

    fun delete(party: Party) {
        coroutineScope.launch(Dispatchers.IO) {
            dao.delete(party)
        }
    }
}