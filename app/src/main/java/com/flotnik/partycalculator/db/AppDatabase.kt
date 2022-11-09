package com.flotnik.partycalculator.db

import android.content.Context
import androidx.room.*
import com.flotnik.partycalculator.db.dao.ParticipantDao
import com.flotnik.partycalculator.db.dao.PartyDao
import com.flotnik.partycalculator.db.entities.*
import java.util.*

@Database(
    entities = [
        Party::class,
        Participant::class,
        Contribution::class,
        Participant2Party::class,
        Participant2Contribution::class,
        ContributionExclusions::class
    ],
    version = 1
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun partyDao(): PartyDao
    abstract fun participantDao(): ParticipantDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        AppDatabase::class.java, "party_calc_db").allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE!!
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }

}

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}