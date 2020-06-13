package com.roks.houseworkapp.persistence.db.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class WorkEntity {

    @PrimaryKey(autoGenerate = true)
    private Integer id;

    @NonNull
    private String name;

    @NonNull
    private Integer score;

    public WorkEntity(@NonNull String name, @NonNull Integer score) {
        this.name = name;
        this.score = score;
    }

    @NonNull
    public String getName() {
        return this.name;
    }

    @NonNull
    public Integer getScore() {
        return score;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
