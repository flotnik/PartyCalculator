package com.partycalc.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface AllPartiesDAO {

    @Insert
    void insert(AllParties... item);

    @Update
    void update(AllParties... item);

    @Delete
    void delete(AllParties... item);

    @Query("SELECT participant.* from allparties " +
            "INNER JOIN participant ON participant.id=allparties.participantId " +
            "where partyId=:partyId")
    List<Participant> getParticipantsForParty(final int partyId);

    @Query("SELECT contribution.* from allparties " +
            "INNER JOIN contribution ON contribution.id=allparties.contributionId " +
            "where partyId=:partyId and participantId=:participantId")
    List<Contribution> getContributions(final int partyId, final int participantId);

}
