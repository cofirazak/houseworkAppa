package com.roks.houseworkapp.persistence.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.roks.houseworkapp.persistence.db.entity.HistoryEntity;

import java.util.List;

@Dao
public interface HistoryDao {

    @Insert
    void insert(HistoryEntity historyEntity);

    @Update
    void update(HistoryEntity historyEntity);

    @Delete
    void delete(HistoryEntity historyEntity);

    @Query("DELETE FROM HistoryEntity")
    void deleteAllHistory();

    @Query("SELECT * from HistoryEntity ORDER BY date DESC")
    LiveData<List<HistoryEntity>> getHistoryByDateDesc();
}
