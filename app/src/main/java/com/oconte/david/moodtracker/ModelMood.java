package com.oconte.david.moodtracker;

public class ModelMood {
        private String pseudo;


    public ModelMood(int color, String pseudo) {
            //this.color = color;
            this.pseudo = pseudo;
    }

    public String getPseudo() {
            return pseudo;
    }

    public void setPseudo(String pseudo) {
            this.pseudo = pseudo;
    }
}
