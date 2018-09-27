package com.partycalc.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;


@Database(entities = {Party.class, Participant.class, Contribution.class, AllParties.class}, version = 1)
public abstract class PartiesDatabase extends RoomDatabase {

    public abstract PartyDAO partyDAO();
    public abstract ParticipantDAO participantDAO();
    public abstract ContributionDAO contributionDAO();
    public abstract AllPartiesDAO allPartiesDAO();

}
