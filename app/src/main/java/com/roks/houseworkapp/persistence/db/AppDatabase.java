package com.roks.houseworkapp.persistence.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.roks.houseworkapp.persistence.db.converter.Converter;
import com.roks.houseworkapp.persistence.db.dao.HistoryDao;
import com.roks.houseworkapp.persistence.db.dao.WorkDao;
import com.roks.houseworkapp.persistence.db.entity.HistoryEntity;
import com.roks.houseworkapp.persistence.db.entity.WorkEntity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {WorkEntity.class, HistoryEntity.class}, version = 2, exportSchema = false)
@TypeConverters({Converter.class})
public abstract class AppDatabase extends RoomDatabase {

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("INSERT INTO `WorkEntity` (`id`, `name`, `score`) VALUES (10, 'Протелеть плиту', 1)");
            database.execSQL("INSERT INTO `WorkEntity` (`id`, `name`, `score`) VALUES (11, 'Вынести мусор', 1)");
        }
    };

    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    private static volatile AppDatabase INSTANCE;
    private static RoomDatabase.Callback roomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
        }
    };

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "houseworkapp")
                            .addCallback(roomDatabaseCallback)
                            .addMigrations(MIGRATION_1_2)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract WorkDao workDao();

    public abstract HistoryDao historyDao();
}
