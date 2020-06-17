package com.partycalc.viewmodels

import android.app.Application
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

    init {
        parties = partyRepository.partyDAO.allParties
    }
}