package com.partycalc.database.migrations;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.migration.Migration;
import android.support.annotation.NonNull;

public class PartiesDatabaseMigrations_1_2 extends Migration {
    /**
     * Creates a new migration between {@code startVersion} and {@code endVersion}.
     *
     * @param startVersion The start version of the database.
     * @param endVersion   The end version of the database after this migration is applied.
     */
    public PartiesDatabaseMigrations_1_2(int startVersion, int endVersion) {
        super(startVersion, endVersion);
    }

    @Override
    public void migrate(@NonNull SupportSQLiteDatabase database) {
        database.execSQL("CREATE TABLE `Fruit` (`id` INTEGER, "
                + "`name` TEXT, PRIMARY KEY(`id`))");
    }
}
