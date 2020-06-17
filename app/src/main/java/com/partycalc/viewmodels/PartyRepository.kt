package com.partycalc.viewmodels

import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.partycalc.database.*

class PartyRepository(db: PartiesDatabase) {
    var partyDAO: PartyDAO = db.partyDAO()
    var participantDAO: ParticipantDAO = db.participantDAO()
    var activePartiesDAO: ActivePartiesDAO = db.activePartiesDAO()
    //val allParties: LiveData<List<Party>>
    //get() = partyDAO.allParties

    fun removeParty(party: Party) {
        DeletePartyAsyncTask(partyDAO).execute(party)
    }

    fun getPartyParticipants(party: Party): LiveData<List<Participant>> {
        //Log.e("getPartyParticipants", "party_id = " + party.getName());
        return activePartiesDAO.getParticipantsForParty(party.id)
        /*getPartyParticipantsAsyncTask t = new getPartyParticipantsAsyncTask(activePartiesDAO);
        t.execute(party);
        return t.getList();*/
    }

    fun removeParticipantFromParty(party: Party, participant: Participant) {
        activePartiesDAO.removeParticipantFromParty(party.id, participant.id)
    }

    fun addParticipantToParty(party: Party, participant: Participant?) {
        AddParticipantToPartyAsyncTask(activePartiesDAO, participantDAO, party).execute(participant)
    }

    fun addContribToParticipant(party: Party, participant: Participant, contrib: Array<Contribution?>) {
        AddParticipantContributionAsyncTask(activePartiesDAO, party, participant).execute(*contrib)
    }

    fun getParticipantContributions(party: Party?, participant: Participant?): LiveData<List<Contribution>>? {
        return null
    }

    fun addParty(party: Party) {
        AddPartyAsyncTask(partyDAO).execute(party)
    }

    //=============ASYNC  TASKS=============================
 /*   private class GetPartyParticipantsAsyncTask internal constructor(private val activePartiesDAO: ActivePartiesDAO) : AsyncTask<Party, Void, Void>() {
        lateinit var list: LiveData<List<Participant>>

        override fun doInBackground(vararg params: Party): Void {
            list = params[0].id.let { activePartiesDAO.getParticipantsForParty(it) }
            for (p in list.value!!) {
            }
            return null
        }
    }*/

    private class AddPartyAsyncTask internal constructor(private val partyDAO: PartyDAO) : AsyncTask<Party, Void, Void>() {
        override fun doInBackground(vararg params: Party): Void? {
            partyDAO.insert(*params)
            return null
        }
    }

    private class DeletePartyAsyncTask internal constructor(private val partyDAO: PartyDAO) : AsyncTask<Party, Void, Void>() {
        override fun doInBackground(vararg params: Party): Void? {
            partyDAO.delete(*params)
            return null
        }
    }

    private class AddParticipantAsyncTask internal constructor(private val participantDAO: ParticipantDAO) : AsyncTask<Participant?, Void?, Void?>() {
        override fun doInBackground(vararg params: Participant?): Void? {
            participantDAO.insert(params[0])
            return null
        }
    }

    private class AddParticipantToPartyAsyncTask(private val activePartiesDAO: ActivePartiesDAO, private val participantDAO: ParticipantDAO, private val party: Party) : AsyncTask<Participant?, Void?, Void?>() {
        override fun doInBackground(vararg params: Participant?): Void? {
            val ids = participantDAO.insert(params[0])
            val ap = ActiveParties(party.id, ids!![0].toInt(), "empty", 0F)
            activePartiesDAO.insert(ap)
            return null
        }
    }

    private class AddParticipantContributionAsyncTask(private val activePartiesDAO: ActivePartiesDAO, private val party: Party, private val participant: Participant) : AsyncTask<Contribution?, Void?, Void?>() {
        override fun doInBackground(vararg params: Contribution?): Void? {
            for (c in params) {
                if (c != null) {
                    activePartiesDAO.insert(
                            ActiveParties(party.id,
                                    participant.id,
                                    c.contrib_comment,
                                    c.contrib_amount!!)
                    )
                }
            }
            return null
        }
    }

/*    private class GetParticipantContributionsAsyncTask(private val activePartiesDAO: ActivePartiesDAO, private val party: Party) : AsyncTask<Participant, Void, Void>() {
        override fun doInBackground(vararg params: Participant): Void? {
            params[0].id.let { activePartiesDAO.getContributions(party.id, it) }
            return null
        }
    }*/

}