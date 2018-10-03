package com.partycalc.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;


@Database(entities = {Party.class, Participant.class, ActiveParties.class},
          version = 1,
          exportSchema = false)
public abstract class PartiesDatabase extends RoomDatabase {

    public static String getDbName() {
        return DB_NAME;
    }

    public abstract PartyDAO partyDAO();
    public abstract ParticipantDAO participantDAO();
    public abstract ActivePartiesDAO activePartiesDAO();

    private static final String DB_NAME = "partyCalcDatabase.db";
    private static volatile PartiesDatabase instance;

    public static synchronized PartiesDatabase getInstance(Context context) {
        if (instance == null) {
            instance = create(context);
        }
        return instance;
    }

    private static PartiesDatabase create(final Context context) {
        return Room.databaseBuilder(context, PartiesDatabase.class, DB_NAME)
                //.addMigrations(MIGRATION_1_2)
                //.allowMainThreadQueries()
                .build();
    }

    public static void destroyInstance() {
        instance = null;
    }

/*    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE `party` add column date INTEGER");
        }
    };*/

}