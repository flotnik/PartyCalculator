package com.flotnik.partycalculator.viewmodels


import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.flotnik.partycalculator.db.AppDatabase
import com.flotnik.partycalculator.db.entities.Participant
import com.flotnik.partycalculator.db.entities.Party
import com.flotnik.partycalculator.db.repos.ParticipantRepository
import com.flotnik.partycalculator.db.repos.PartyRepository

class MainViewModel(application: Context) : ViewModel() {

    var currentParty: Party? = null

    val allParties: LiveData<List<Party>>
    private val partyRepo: PartyRepository

    init {
        val db = AppDatabase.getInstance(application)
        val partyDao = db.partyDao()
        partyRepo = PartyRepository(partyDao)

        allParties = partyRepo.allParties
    }

    fun insertParty(party: Party) {
        partyRepo.insert(party)
    }

    fun deleteParty(party: Party) {
        partyRepo.delete(party)
    }
}