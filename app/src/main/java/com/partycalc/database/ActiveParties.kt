package com.partycalc.database

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(foreignKeys = [
            ForeignKey(entity = Party::class,
                       parentColumns = ["id"],
                       childColumns = ["partyId"],
                       onDelete = ForeignKey.CASCADE),
            ForeignKey(entity = Participant::class,
                       parentColumns = ["id"],
                       childColumns = ["participantId"],
                       onDelete = ForeignKey.CASCADE)
        ],
        indices = [
            Index(name = "party_id_index", value = ["partyId"]),
            Index(name = "participant_id_index", value = ["participantId"])
        ]
)
class ActiveParties(var partyId: Int, var participantId: Int, var contrib_comment: String, var contrib_amount: Float) {
    @PrimaryKey(autoGenerate = true)
    var id = 0
}