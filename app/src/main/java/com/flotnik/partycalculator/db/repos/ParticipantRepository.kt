package com.flotnik.partycalculator.db.repos

import androidx.lifecycle.LiveData
import com.flotnik.partycalculator.db.dao.ParticipantDao
import com.flotnik.partycalculator.db.dao.PartyDao
import com.flotnik.partycalculator.db.entities.Participant
import com.flotnik.partycalculator.db.entities.Party
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ParticipantRepository(private val dao: ParticipantDao) {

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun insert(party: Participant): Int {
        return dao.insert(party).toInt()
    }

    fun delete(party: Participant) {
        coroutineScope.launch(Dispatchers.IO) {
            dao.delete(party)
        }
    }

    fun deleteParticipantFromParty(participantId: Int, partyId: Int) {
        dao.deleteParticipantFromParty(participantId, partyId)
    }

    fun addParticipantToParty(participantId: Int, partyId: Int) {
        dao.addParticipantToParty(participantId, partyId)
    }

    fun getPartyParticipants(partyId: Int): LiveData<List<Participant>> {
        return dao.getPartyParticipants(partyId)
    }
}