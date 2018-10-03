package com.partycalc.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity
public class ActiveParties {

    @PrimaryKey(autoGenerate = true)
    int id;

    @ForeignKey(entity = Party.class, parentColumns = "id", childColumns = "partyId", onDelete = CASCADE)
    int partyId;

    @ForeignKey(entity = Participant.class, parentColumns = "id", childColumns = "participantId", onDelete = CASCADE)
    int participantId;

    String contrib_comment;
    float contrib_amount;

    public ActiveParties(int partyId, int participantId, String contrib_comment, float contrib_amount) {
        this.partyId = partyId;
        this.participantId = participantId;
        this.contrib_comment = contrib_comment;
        this.contrib_amount = contrib_amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
