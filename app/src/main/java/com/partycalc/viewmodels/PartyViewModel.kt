package com.partycalc.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.partycalc.database.PartiesDatabase
import com.partycalc.database.Party

class PartyViewModel(application: Application) : AndroidViewModel(application) {
    private val partyRepository: PartyRepository = PartyRepository(PartiesDatabase.getInstance(application))
    val parties: LiveData<List<Party>>

    fun deleteParty(party: Party) {
        partyRepository.removeParty(party)
    }

    fun addParty(party: Party) {
        partyRepository.addParty(party)
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("PartyViewModel", "PartyViewModel destroyed!")
    }

    init {
        parties = partyRepository.getAllParties()
    }
}