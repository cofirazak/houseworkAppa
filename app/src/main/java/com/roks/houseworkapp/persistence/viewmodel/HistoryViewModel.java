package com.roks.houseworkapp.persistence.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.roks.houseworkapp.persistence.DataRepository;
import com.roks.houseworkapp.persistence.db.entity.HistoryEntity;

import java.util.List;

public class HistoryViewModel extends AndroidViewModel {

    private DataRepository dataRepository;

    private LiveData<List<HistoryEntity>> allHistory;

    public HistoryViewModel(Application application) {
        super(application);
        dataRepository = new DataRepository(application);
        allHistory = dataRepository.getAllHistory();
    }

    public LiveData<List<HistoryEntity>> getAllHistory() {
        return allHistory;
    }

    public void insert(HistoryEntity history) {
        dataRepository.insert(history);
    }
}
