package com.partycalc.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface ActivePartiesDAO {

    @Insert
    void insert(ActiveParties... item);

    @Update
    void update(ActiveParties... item);

    @Delete
    void delete(ActiveParties... item);

    @Query("SELECT participant.* from ActiveParties " +
            "INNER JOIN participant ON participant.id=ActiveParties.participantId " +
            "where partyId=:partyId")
    LiveData<List<Participant>> getParticipantsForParty(final int partyId);

    @Query("delete from ActiveParties where participantId=:participantId and partyId=:partyId")
    void removeParticipantFromParty(final int partyId, final int participantId);

    @Query("SELECT contrib_comment, contrib_amount from ActiveParties where partyId=:partyId and participantId=:participantId")
    LiveData<List<Contribution>> getContributions(final int partyId, final int participantId);



}
