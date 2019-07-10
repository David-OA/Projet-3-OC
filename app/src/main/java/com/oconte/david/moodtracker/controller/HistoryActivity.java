package com.oconte.david.moodtracker.controller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.oconte.david.moodtracker.util.HistoryMoodAdapter;
import com.oconte.david.moodtracker.model.Mood;
import com.oconte.david.moodtracker.R;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * The parts for HistoryActivity
 */
public class HistoryActivity extends AppCompatActivity {

    ListView mList_history_mood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

            mList_history_mood = findViewById(R.id.list_history_mood);
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
                Intent activity_statistics = new Intent(this, StatisticsActivity.class );
                startActivity(activity_statistics);
                return true;
            }
            return super.onOptionsItemSelected(item);
        }

        public List<Mood> LoadMood() {
            SharedPreferences setting = getSharedPreferences("Mood", MODE_PRIVATE);
            String moodList = setting.getString("moods", "");
            Type listType = new TypeToken<ArrayList<Mood>>() {}.getType();
            List<Mood> list = new  Gson().fromJson(moodList, listType);

            List<Mood> listsurted = new ArrayList<>();
            int count = 0;
            assert list != null;
            for (int i = list.size()-1; i >=0 && count <=7; i --) {
                count ++;
                listsurted.add(list.get(i));
            }

            return listsurted;
        }

        private void viewListMood(){
            List<Mood> Moods = LoadMood();

            HistoryMoodAdapter adapter = new HistoryMoodAdapter(HistoryActivity.this, Moods);
            mList_history_mood.setAdapter(adapter);
        }
}

