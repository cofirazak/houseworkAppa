package com.roks.houseworkapp.persistence.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.roks.houseworkapp.persistence.db.entity.HistoryEntity;
import com.roks.houseworkapp.persistence.db.entity.ScoreEntity;

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

    @Query("SELECT " +
            "'Kate' AS userName, " +
            "SUM(CASE WHEN strftime('%W', datetime(date/1000,'unixepoch')) = strftime('%W', 'now') THEN score ELSE 0 END) AS weekScore, " +
            "SUM(CASE WHEN strftime('%m', datetime(date/1000,'unixepoch')) = strftime('%m', 'now') THEN score ELSE 0 END) AS monthScore, " +
            "SUM(score) AS totalScore " +
            "FROM HistoryEntity")
    LiveData<List<ScoreEntity>> getHistoryStat();
}
