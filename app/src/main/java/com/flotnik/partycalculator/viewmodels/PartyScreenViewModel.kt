package com.flotnik.partycalculator.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.flotnik.partycalculator.db.AppDatabase
import com.flotnik.partycalculator.db.entities.Participant
import com.flotnik.partycalculator.db.entities.Party
import com.flotnik.partycalculator.db.repos.ParticipantRepository

class PartyScreenViewModel(application: Context, val currentParty: Party): ViewModel() {

    var participantToRemoveFromParty: Participant? = null
    val currentPartyParticipants: LiveData<List<Participant>>

    private val participantRepo: ParticipantRepository

    init {
        val db = AppDatabase.getInstance(application)
        val participantDao = db.participantDao()
        participantRepo = ParticipantRepository(participantDao)

        currentPartyParticipants = participantRepo.getPartyParticipants(currentParty.id)
    }

    fun addParticipant(participant: Participant): Int {
        return participantRepo.insert(participant)
    }

    fun addParticipantToParty(participantId: Int) {
        participantRepo.addParticipantToParty(participantId, currentParty.id)
    }

    fun removeParticipantFromParty(participantId: Int) {
        participantRepo.deleteParticipantFromParty(participantId, currentParty.id)
    }
}