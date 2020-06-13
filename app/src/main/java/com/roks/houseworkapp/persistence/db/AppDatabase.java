package com.roks.houseworkapp.persistence.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.roks.houseworkapp.persistence.db.dao.HistoryDao;
import com.roks.houseworkapp.persistence.db.dao.WorkDao;
import com.roks.houseworkapp.persistence.db.entity.WorkEntity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {WorkEntity.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    private static volatile AppDatabase INSTANCE;
    private static RoomDatabase.Callback roomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);

            // If you want to keep data through app restarts,
            // comment out the following block
            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more work, just add it.
                WorkDao dao = INSTANCE.workDao();
                dao.deleteAllWork();

                WorkEntity work = new WorkEntity("Протелеть плиту", 1);
                dao.insert(work);
                work = new WorkEntity("Вынести мусор", 2);
                dao.insert(work);
            });
        }
    };

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "houseworkapp")
                            .addCallback(roomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract WorkDao workDao();

    public abstract HistoryDao historyDao();
}
