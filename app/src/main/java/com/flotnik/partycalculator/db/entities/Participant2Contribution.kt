package com.flotnik.partycalculator.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "participant2contribution", primaryKeys = ["participant_id", "contribution_id"])
data class Participant2Contribution(
    @ColumnInfo(name = "participant_id") val participantId: Int,
    @ColumnInfo(name = "contribution_id") val contributionId: Int
)
