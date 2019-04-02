package com.oconte.david.moodtracker;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


public class history extends AppCompatActivity {

    /*ImageButton one_week;
    ImageButton six_days;
    ImageButton five_days;
    ImageButton four_days;
    ImageButton three_days;
    ImageButton before_day;
    ImageButton yester_day;*/

    ListView mList_history_mood;

    ImageButton avatar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

            mList_history_mood = (ListView) findViewById(R.id.list_history_mood);
            viewListMood();

        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.history_menu_stat, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            int i = item.getItemId();
            if(i == R.id.stat_mood) {
                Intent activity_statistics = new Intent(this,statistics.class );
                startActivity(activity_statistics);
                return true;
            }
            return super.onOptionsItemSelected(item);
        }

        private void viewListName(){

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(history.this, android.R.layout.simple_list_item_1);
            mList_history_mood.setAdapter(adapter);
        }

        private List<Mood> genererMood(){
            List<Mood> Moods = new ArrayList<Mood>();

            Moods.add(new Mood("Hum plus facile à dire qu'à faire", 2));

            Moods.add(new Mood("Pffff tjrs pas çà !!!!!!!!!!!!!!!!!!!",3));

            Moods.add(new Mood("Don't let the noise of others' opinion drown out your own inner voice by Steve jobs",4));

            Moods.add(new Mood("Il y a une semaine",1));
            Moods.add(new Mood("Il y a six jours",3));
            Moods.add(new Mood("Il y a cinq jours",1));
            Moods.add(new Mood("Il y a quatre jours",2));
            Moods.add(new Mood("Il y a trois jours",3));
            Moods.add(new Mood("Avant-hier",2));
            Moods.add(new Mood("Hier",2));

            return Moods;
        }

        private void viewListMood(){
            List<Mood> Moods = genererMood();

            HistoryMoodAdapter adapter = new HistoryMoodAdapter(history.this, Moods);
            mList_history_mood.setAdapter(adapter);
        }




}

