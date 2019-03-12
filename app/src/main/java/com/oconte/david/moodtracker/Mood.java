package com.oconte.david.moodtracker;

import android.content.SharedPreferences;

public class Mood {

    //sauvegarde de la date et de l'humeur et commentaires

    SharedPreferences mPreferences;

    public static final String PREF_KEY_COMMENT0 = "PREF_KEY_COMMENT0";
    public static final String PREF_KEY_COMMENT1 = "PREF_KEY_COMMENT1";
    public static final String PREF_KEY_COMMENT2 = "PREF_KEY_COMMENT2";
    public static final String PREF_KEY_COMMENT3 = "PREF_KEY_COMMENT3";
    public static final String PREF_KEY_COMMENT4 = "PREF_KEY_COMMENT4";
    public static final String PREF_KEY_COMMENT5 = "PREF_KEY_COMMENT5";
    public static final String PREF_KEY_COMMENT6 = "PREF_KEY_COMMENT6";

    public static final String PREF_KEY_COLOR0 = "PREF_KEY_COLOR0";
    public static final String PREF_KEY_COLOR1 = "PREF_KEY_COLOR1";
    public static final String PREF_KEY_COLOR2 = "PREF_KEY_COLOR2";
    public static final String PREF_KEY_COLOR3 = "PREF_KEY_COLOR3";
    public static final String PREF_KEY_COLOR4 = "PREF_KEY_COLOR4";
    public static final String PREF_KEY_COLOR5 = "PREF_KEY_COLOR5";
    public static final String PREF_KEY_COLOR6 = "PREF_KEY_COLOR6";

    public SharedPreferences getmPreferences() {
        return mPreferences;
    }

}
