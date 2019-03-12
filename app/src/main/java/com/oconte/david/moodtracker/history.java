package com.oconte.david.moodtracker;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


public class history extends AppCompatActivity {

    ImageButton one_week;
    ImageButton six_days;
    ImageButton five_days;
    ImageButton four_days;
    ImageButton three_days;
    ImageButton before_day;
    ImageButton yester_day;

    ListView mList_history_mood;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

            mList_history_mood = (ListView) findViewById(R.id.list_history_mood);
            viewListModelMood();

        }

        private void viewListName(){

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(history.this, android.R.layout.simple_list_item_1);
            mList_history_mood.setAdapter(adapter);
        }

        private List<ModelMood> genererModelMood(){
            List<ModelMood> ModelMoods = new ArrayList<ModelMood>();
            ModelMoods.add(new ModelMood("Il y a une semaine"));
            ModelMoods.add(new ModelMood("Il y a six jours"));
            ModelMoods.add(new ModelMood("Il y a cinq jours"));
            ModelMoods.add(new ModelMood("Il y a quatre jours"));
            ModelMoods.add(new ModelMood("Il y a trois jours"));
            ModelMoods.add(new ModelMood("Avant-hier"));
            ModelMoods.add(new ModelMood("Hier"));

            return ModelMoods;
        }

        private void viewListModelMood(){
            List<ModelMood> ModelMoods = genererModelMood();

            HistoryMoodAdapter adapter = new HistoryMoodAdapter(history.this, ModelMoods);
            mList_history_mood.setAdapter(adapter);
        }


}

