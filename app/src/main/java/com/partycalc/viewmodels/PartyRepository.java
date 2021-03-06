package com.partycalc.viewmodels;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

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
        this.activePartiesDAO = db.activePartiesDAO();
    }

    public LiveData<List<Party>> getAllParties() {
        return partyDAO.getAllParties();
    }

    public void removeParty(Party party) {
        new deletePartyAsyncTask(partyDAO).execute(party);
    }

    public LiveData<List<Participant>> getPartyParticipants(Party party) {
        //Log.e("getPartyParticipants", "party_id = " + party.getName());
        return activePartiesDAO.getParticipantsForParty(party.getId());
        /*getPartyParticipantsAsyncTask t = new getPartyParticipantsAsyncTask(activePartiesDAO);
        t.execute(party);
        return t.getList();*/
    }

    public void removeParticipantFromParty(Party party, Participant participant) {
        activePartiesDAO.removeParticipantFromParty(party.getId(), participant.getId());
    }

    public void addParticipantToParty(Party party, Participant participant) {
         new addParticipantToPartyAsyncTask(activePartiesDAO, participantDAO, party).execute(participant);
    }

    public void addContribToParticipant(Party party, Participant participant, Contribution[] contrib) {
        new addParticipantContributionAsyncTask(activePartiesDAO, party, participant).execute(contrib);
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
            for(Participant p:list.getValue()){

            }
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
        private ParticipantDAO participantDAO;
        private Party party;

        public addParticipantToPartyAsyncTask(ActivePartiesDAO activePartiesDAO,ParticipantDAO participantDAO, Party party) {
            this.activePartiesDAO = activePartiesDAO;
            this.participantDAO = participantDAO;
            this.party = party;
        }

        @Override
        protected Void doInBackground(final Participant... params) {
            long[] ids = participantDAO.insert(params[0]);
            ActiveParties ap = new ActiveParties(party.getId(), (int)ids[0], "empty", 0);
            activePartiesDAO.insert(ap);
            return null;
        }
    }

    private static class addParticipantContributionAsyncTask extends AsyncTask<Contribution, Void, Void> {
        private ActivePartiesDAO activePartiesDAO;
        private Party party;
        private Participant participant;

        public addParticipantContributionAsyncTask(ActivePartiesDAO activePartiesDAO,Party party, Participant participant) {
            this.activePartiesDAO = activePartiesDAO;
            this.participant = participant;
            this.party = party;
        }

        @Override
        protected Void doInBackground(final Contribution... params) {
            for(Contribution c: params){
                activePartiesDAO.insert(
                        new ActiveParties(party.getId(),
                                          participant.getId(),
                                          c.getContrib_comment(),
                                          c.getContrib_amount())
                );
            }
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
