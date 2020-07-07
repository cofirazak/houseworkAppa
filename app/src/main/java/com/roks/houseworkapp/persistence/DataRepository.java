package com.roks.houseworkapp.persistence;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.roks.houseworkapp.persistence.db.AppDatabase;
import com.roks.houseworkapp.persistence.db.dao.HistoryDao;
import com.roks.houseworkapp.persistence.db.dao.WorkDao;
import com.roks.houseworkapp.persistence.db.entity.HistoryEntity;
import com.roks.houseworkapp.persistence.db.entity.ScoreEntity;
import com.roks.houseworkapp.persistence.db.entity.WorkEntity;

import java.util.List;

public class DataRepository {

    private WorkDao workDao;
    private HistoryDao historyDao;
    private LiveData<List<WorkEntity>> allWork;
    private LiveData<List<HistoryEntity>> allHistory;
    private LiveData<List<ScoreEntity>> historyStat;

    // Note that in order to unit test the DataRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    public DataRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        workDao = db.workDao();
        historyDao = db.historyDao();
        allWork = workDao.getWorkByScoreAsc();
        allHistory = historyDao.getHistoryByDateDesc();
        historyStat = historyDao.getHistoryStat();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<WorkEntity>> getAllWork() {
        return allWork;
    }

    public LiveData<List<HistoryEntity>> getAllHistory() {
        return allHistory;
    }

    public LiveData<List<ScoreEntity>> getHistoryStat() {
        return historyStat;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    public void insert(WorkEntity workEntity) {
        AppDatabase.databaseWriteExecutor.execute(() -> workDao.insert(workEntity));
    }

    public void insert(HistoryEntity historyEntity) {
        AppDatabase.databaseWriteExecutor.execute(() -> historyDao.insert(historyEntity));
    }

    public void delete(WorkEntity workEntity) {
        new DeleteWorkAsyncTask(workDao).execute(workEntity);
    }
    private static class DeleteWorkAsyncTask extends AsyncTask<WorkEntity, Void, Void> {

        private WorkDao workDao;
        private DeleteWorkAsyncTask(WorkDao workDao) {
            this.workDao = workDao;
        }
        @Override
        protected Void doInBackground(WorkEntity... work) {
            workDao.delete(work[0]);
            return null;
        }

    }
}
