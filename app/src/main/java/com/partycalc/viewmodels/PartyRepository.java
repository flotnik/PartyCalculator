package com.partycalc.viewmodels;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.util.Log;

import com.partycalc.database.ActiveParties;
import com.partycalc.database.ActivePartiesDAO;
import com.partycalc.database.Contribution;
import com.partycalc.database.Participant;
import com.partycalc.database.ParticipantDAO;
import com.partycalc.database.PartiesDatabase;
import com.partycalc.database.Party;
import com.partycalc.database.PartyDAO;

import java.util.List;

public class PartyRepository {

    PartyDAO partyDAO;
    ParticipantDAO participantDAO;
    ActivePartiesDAO activePartiesDAO;

    public PartyRepository(Application application) {
        PartiesDatabase db = PartiesDatabase.getInstance(application);
        this.partyDAO = db.partyDAO();
        this.participantDAO = db.participantDAO();
        this.activePartiesDAO = db.allPartiesDAO();
    }

    public LiveData<List<Party>> getAllParties() {
        return partyDAO.getAllParties();
    }

    public void removeParty(Party party) {
        new deletePartyAsyncTask(partyDAO).execute(party);
    }

    public LiveData<List<Participant>> getPartyParticipants(Party party) {
        return activePartiesDAO.getParticipantsForParty(party.getId());
        /*getPartyParticipantsAsyncTask t = new getPartyParticipantsAsyncTask(activePartiesDAO);
        t.execute(party);
        return t.getList();*/
    }

    public void removeParticipantFromParty(Party party, Participant participant) {
        activePartiesDAO.removeParticipantFromParty(party.getId(), participant.getId());
    }

    public void addParticipantToParty(Party party, Participant participant) {
         new addParticipantToPartyAsyncTask(activePartiesDAO, party).execute(participant);
    }

    public void addContribToParticipant(Party party, Participant participant, Contribution contrib) {
        ActiveParties rr = new ActiveParties(party.getId(), participant.getId(), contrib.getContrib_comment(), contrib.getContrib_amount());
        activePartiesDAO.insert(rr);
    }

    public LiveData<List<Contribution>> getParticipantContributions(Party party, Participant participant) {
        return null;
    }

    public void addParty(Party party) {
        new addPartyAsyncTask(partyDAO).execute(party);
    }

    //=============ASYNC  TASKS=============================
    private static class getPartyParticipantsAsyncTask extends android.os.AsyncTask<Party, Void, Void> {

        private ActivePartiesDAO activePartiesDAO;
        LiveData<List<Participant>> list;

        public LiveData<List<Participant>> getList() {
            return list;
        }

        getPartyParticipantsAsyncTask(ActivePartiesDAO activePartiesDAO) {
            this.activePartiesDAO = activePartiesDAO;
        }

        @Override
        protected Void doInBackground(final Party... params) {
            list = activePartiesDAO.getParticipantsForParty(params[0].getId());
            return null;
        }
    }

    private static class addPartyAsyncTask extends AsyncTask<Party, Void, Void> {
        private PartyDAO partyDAO;

        addPartyAsyncTask(PartyDAO partyDAO) {
            this.partyDAO = partyDAO;
        }

        @Override
        protected Void doInBackground(final Party... params) {
            partyDAO.insert(params);
            return null;
        }
    }

    private static class deletePartyAsyncTask extends AsyncTask<Party, Void, Void> {

        private PartyDAO partyDAO;

        deletePartyAsyncTask(PartyDAO partyDAO) {
            this.partyDAO = partyDAO;
        }

        @Override
        protected Void doInBackground(final Party... params) {
            partyDAO.delete(params);
            return null;
        }
    }

    private static class addParticipantAsyncTask extends AsyncTask<Participant, Void, Void> {
        private ParticipantDAO participantDAO;

        addParticipantAsyncTask(ParticipantDAO participantDAO) {
            this.participantDAO = participantDAO;
        }

        @Override
        protected Void doInBackground(final Participant... params) {
            participantDAO.insert(params[0]);
            return null;
        }
    }

    private static class addParticipantToPartyAsyncTask extends AsyncTask<Participant, Void, Void> {
        private ActivePartiesDAO activePartiesDAO;
        private Party party;

        public addParticipantToPartyAsyncTask(ActivePartiesDAO activePartiesDAO, Party party) {
            this.activePartiesDAO = activePartiesDAO;
            this.party = party;
        }

        @Override
        protected Void doInBackground(final Participant... params) {
            ActiveParties ap = new ActiveParties(party.getId(), params[0].getId(), "empty", 0);
            long[] ids= activePartiesDAO.insert(ap);
            Log.e("PartyRepository", "inserted object id = " + ids[0]);
            return null;
        }
    }

    private static class getParticipantContributionsAsyncTask extends AsyncTask<Participant, Void, Void> {
        private ActivePartiesDAO activePartiesDAO;
        private Party party;

        public getParticipantContributionsAsyncTask(ActivePartiesDAO activePartiesDAO, Party party) {
            this.activePartiesDAO = activePartiesDAO;
            this.party = party;
        }

        @Override
        protected Void doInBackground(final Participant... params) {
            activePartiesDAO.getContributions(party.getId(), params[0].getId());
            return null;
        }
    }

}
