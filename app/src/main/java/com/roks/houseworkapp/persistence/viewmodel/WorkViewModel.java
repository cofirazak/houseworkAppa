package com.roks.houseworkapp.persistence.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.roks.houseworkapp.persistence.DataRepository;
import com.roks.houseworkapp.persistence.db.entity.WorkEntity;

import java.util.List;

public class WorkViewModel extends AndroidViewModel {

    private DataRepository dataRepository;

    private LiveData<List<WorkEntity>> allWork;

    public WorkViewModel(Application application) {
        super(application);
        dataRepository = new DataRepository(application);
        allWork = dataRepository.getAllWork();
    }

    public LiveData<List<WorkEntity>> getAllWork() {
        return allWork;
    }

    public void insert(WorkEntity work) {
        dataRepository.insert(work);
    }
}
