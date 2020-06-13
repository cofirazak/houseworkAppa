package com.roks.houseworkapp.persistence.db.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class HistoryEntity {

    @PrimaryKey(autoGenerate = true)
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private Integer score;

    @NonNull
    private Date date;

    public HistoryEntity(@NonNull String name, @NonNull Integer score, @NonNull Date date) {
        this.name = name;
        this.score = score;
        this.date = date;
    }

    @NonNull
    public String getName() {
        return this.name;
    }

    @NonNull
    public Integer getScore() {
        return score;
    }

    @NonNull
    public Date getDate() {
        return date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
