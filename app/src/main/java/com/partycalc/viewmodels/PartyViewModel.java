package com.partycalc.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.partycalc.database.Participant;
import com.partycalc.database.Party;

import java.util.List;

public class PartyViewModel extends AndroidViewModel {

    private PartyRepository partyRepository;

    private LiveData<List<Party>> partiesList;
    private LiveData<List<Participant>> partyParticipantsList;


    public PartyViewModel(@NonNull Application application) {
        super(application);

        partyRepository = new PartyRepository(application);
        partiesList = partyRepository.getAllParties();
    }


    public LiveData<List<Party>> getParties() {
        return partiesList;
    }

    public void deleteParty(Party party) {
        partyRepository.removeParty(party);
    }

    public void addParty(Party party) {
        partyRepository.addParty(party);
    }

    public Party getPartyById(int id) throws Exception {
        for(Party p:partiesList.getValue()){
            if(p.getId() == id)return  p;
        }
        throw new Exception("Unknown party id " + id);
    }

    public LiveData<List<Participant>> getPartyParticipants(Party party) {
        this.partyParticipantsList = partyRepository.getPartyParticipants(party);
        return partyParticipantsList;
    }
}
