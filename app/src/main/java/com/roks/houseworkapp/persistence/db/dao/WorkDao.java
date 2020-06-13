package com.roks.houseworkapp.persistence.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.roks.houseworkapp.persistence.db.entity.WorkEntity;

import java.util.List;

@Dao
public interface WorkDao {

    @Insert
    void insert(WorkEntity workEntity);

    @Update
    void update(WorkEntity workEntity);

    @Delete
    void delete(WorkEntity workEntity);

    @Query("DELETE FROM WorkEntity")
    void deleteAllWork();

    @Query("SELECT * from WorkEntity ORDER BY score ASC")
    LiveData<List<WorkEntity>> getWorkByScoreAsc();
}
