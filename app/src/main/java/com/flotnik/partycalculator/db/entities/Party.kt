package com.flotnik.partycalculator.db.entities


import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavType
import androidx.room.*
import com.google.gson.Gson
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "party")
data class Party (
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "place") var place: String,
    @ColumnInfo(name = "date_start") var dateStart: Date = Date(),
    @ColumnInfo(name = "date_finished") var dateFinished: Date = Date(),
    @ColumnInfo(name = "finished") var finished: Boolean = false
) : Parcelable {
    companion object NavigationType : NavType<Party>(isNullableAllowed = false) {
        override fun get(bundle: Bundle, key: String): Party? {
            return bundle.getParcelable(key)
        }

        override fun parseValue(value: String): Party {
            return Gson().fromJson(value, Party::class.java)
        }

        override fun put(bundle: Bundle, key: String, value: Party) {
            bundle.putParcelable(key, value)
        }
    }
}

