package com.flotnik.partycalculator.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "contributions_exclusions", primaryKeys = ["participant_id", "contribution_id"])
data class ContributionExclusions(
    @ColumnInfo(name = "participant_id") val participantId: Int,
    @ColumnInfo(name = "contribution_id") val contributionId: Int
)
