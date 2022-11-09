package com.flotnik.partycalculator.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "participant2party", primaryKeys = ["party_id", "participant_id"])
data class Participant2Party(
    @ColumnInfo(name = "party_id") val partyId: Int,
    @ColumnInfo(name = "participant_id") val participantId: Int
)
