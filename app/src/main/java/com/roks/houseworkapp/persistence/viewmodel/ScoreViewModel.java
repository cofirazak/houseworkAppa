package com.roks.houseworkapp.persistence.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.roks.houseworkapp.persistence.DataRepository;
import com.roks.houseworkapp.persistence.db.entity.ScoreEntity;

import java.util.List;

public class ScoreViewModel extends AndroidViewModel {

    private LiveData<List<ScoreEntity>> historyStat;

    public ScoreViewModel(Application application) {
        super(application);
        DataRepository dataRepository = new DataRepository(application);
        historyStat = dataRepository.getHistoryStat();
    }

    public LiveData<List<ScoreEntity>> getHistoryStat() {
        return historyStat;
    }
}
