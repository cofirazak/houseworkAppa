package com.roks.houseworkapp.persistence.db.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class WorkEntity {

    @PrimaryKey(autoGenerate = true)
    private Long id;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
