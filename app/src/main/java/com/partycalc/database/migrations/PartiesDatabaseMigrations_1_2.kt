package com.partycalc.database.migrations

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase


class PartiesDatabaseMigrations_1_2
/**
 * Creates a new migration between `startVersion` and `endVersion`.
 *
 * @param startVersion The start version of the database.
 * @param endVersion   The end version of the database after this migration is applied.
 */
(startVersion: Int, endVersion: Int) : Migration(startVersion, endVersion) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("CREATE TABLE `Fruit` (`id` INTEGER, "
                + "`name` TEXT, PRIMARY KEY(`id`))")
    }
}