package com.partycalc.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Party::class, Participant::class, ActiveParties::class], version = 1, exportSchema = false)
abstract class PartiesDatabase : RoomDatabase() {
    abstract fun partyDAO(): PartyDAO
    abstract fun participantDAO(): ParticipantDAO
    abstract fun activePartiesDAO(): ActivePartiesDAO

    companion object {

        private const val dbName = "partyCalcDatabase.db"

        @Volatile private var INSTANCE: PartiesDatabase? = null

        fun getInstance(context: Context): PartiesDatabase =
                INSTANCE ?: synchronized(this) {
                    INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
                }

        private fun buildDatabase(context: Context): PartiesDatabase {
            return Room.databaseBuilder(context.applicationContext, PartiesDatabase::class.java, dbName)
                    //.addMigrations(MIGRATION_1_2)
                    //.allowMainThreadQueries()
                    .build()
        }

        /*    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE `party` add column date INTEGER");
        }
    };*/
    }
}