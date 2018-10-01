package com.partycalc.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(primaryKeys = {"partyId", "participantId", "contributionId"})
public class ActiveParties {

    @ForeignKey(entity = Party.class, parentColumns = "id", childColumns = "partyId", onDelete = CASCADE)
    int partyId;

    @ForeignKey(entity = Participant.class, parentColumns = "id", childColumns = "participantId", onDelete = CASCADE)
    int participantId;

    @ForeignKey(entity = Contribution.class, parentColumns = "id", childColumns = "contributionId", onDelete = CASCADE)
    int contributionId;

    public ActiveParties(int partyId, int participantId, String contrib_comment, float contrib_amount) {
        this.partyId = partyId;
        this.participantId = participantId;
        this.contrib_comment = contrib_comment;
        this.contrib_amount = contrib_amount;
    }

    String contrib_comment;
    float contrib_amount;

}
