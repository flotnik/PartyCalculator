package com.flotnik.partycalculator.db.entities

import androidx.room.*


@Entity(tableName = "participant")
data class Participant(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "name") val name: String
)