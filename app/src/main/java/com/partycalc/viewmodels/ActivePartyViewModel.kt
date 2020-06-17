package com.partycalc.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.partycalc.database.Contribution
import com.partycalc.database.Participant
import com.partycalc.database.PartiesDatabase
import com.partycalc.database.Party

class ActivePartyViewModel(application: Application, private val party: Party) : AndroidViewModel(application) {
    private val partyRepository: PartyRepository = PartyRepository(PartiesDatabase.getInstance(application))
    val allParticipants: LiveData<List<Participant>>

    fun addParticipantToParty(p: Participant) {
        partyRepository.addParticipantToParty(party, p)
    }

    fun getContributions(participant: Participant): LiveData<List<Contribution>>? {
        return partyRepository.getParticipantContributions(party, participant)
    }

    init {
        allParticipants = partyRepository.getPartyParticipants(party)
    }
}