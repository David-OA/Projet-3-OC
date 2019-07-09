package com.oconte.david.moodtracker.Model;

import java.util.Date;

/**
 * This is mi Mood
 */
public class Mood {


    private String comment;
    private int mood;
    private Date date;

    public Mood(String comment, int mood, Date date) {
        this.comment = comment;
        this.mood = mood;
        this.date = date;
    }

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

}
