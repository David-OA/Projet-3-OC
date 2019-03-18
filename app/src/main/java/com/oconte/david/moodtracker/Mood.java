package com.oconte.david.moodtracker;

import android.content.SharedPreferences;

import java.util.Date;

public class Mood {

    private String comment;
    private int mood;
    Date date;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getMood() {
        return mood;
    }

    public void setMood(int mood) {
        this.mood = mood;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
