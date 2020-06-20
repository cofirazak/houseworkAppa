package com.roks.houseworkapp.persistence.db.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;

@Entity
public class ScoreEntity {

    @NonNull
    private String userName;

    @NonNull
    private Integer weekScore;

    @NonNull
    private Integer monthScore;

    @NonNull
    private Integer totalScore;

    public ScoreEntity(@NonNull String userName, @NonNull Integer weekScore, @NonNull Integer monthScore, @NonNull Integer totalScore) {
        this.userName = userName;
        this.weekScore = weekScore;
        this.monthScore = monthScore;
        this.totalScore = totalScore;
    }

    @NonNull
    public String getUserName() {
        return this.userName;
    }

    @NonNull
    public Integer getWeekScore() {
        return weekScore;
    }

    @NonNull
    public Integer getMonthScore() {
        return monthScore;
    }

    @NonNull
    public Integer getTotalScore() {
        return totalScore;
    }
}
