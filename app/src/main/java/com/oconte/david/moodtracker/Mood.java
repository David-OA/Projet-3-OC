package com.oconte.david.moodtracker;

import android.widget.EditText;

import java.util.Date;

public class Mood {


    private String comment;
    private int mood;
    Date date;

    protected Mood(String comment, int mood, Date date) {
        this.comment = comment;
        this.mood = mood;
        this.date = date;
    }

    protected String getComment() {
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
