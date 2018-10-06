package com.partycalc.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.partycalc.database.Contribution;
import com.partycalc.database.Participant;
import com.partycalc.database.Party;

import java.util.List;

public class ActivePartyViewModel extends AndroidViewModel {

    private PartyRepository partyRepository;
    private Party party;
    private LiveData<List<Participant>> participantsList;

    public ActivePartyViewModel(@NonNull Application application, Party party) {
        super(application);
        this.party = party;

        partyRepository = new PartyRepository(application);
        participantsList = partyRepository.getPartyParticipants(party);
    }

    public LiveData<List<Participant>> getAllParticipants() {
        return participantsList;
    }

    public void addParticipantToParty(Participant p) {
       partyRepository.addParticipantToParty(party, p);
    }

    public LiveData<List<Contribution>> getContributions(Participant participant){
        return partyRepository.getParticipantContributions(party, participant);
    }
}
