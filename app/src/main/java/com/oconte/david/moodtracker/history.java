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

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class history extends AppCompatActivity {

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

        public List<Mood> LoadMood() {
            SharedPreferences setting = getSharedPreferences("Mood", MODE_PRIVATE);
            String moodList = setting.getString("moods", "");
            Type listType = new TypeToken<ArrayList<Mood>>() {}.getType();
            List<Mood> list = new  Gson().fromJson(moodList, listType);

            return list;
        }

        private void viewListMood(){
            List<Mood> Moods = LoadMood();

            HistoryMoodAdapter adapter = new HistoryMoodAdapter(history.this, Moods);
            mList_history_mood.setAdapter(adapter);
        }




}

