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


    /*@Override
    public boolean onCreateOptionMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.history_menu_stat, menu);
        return true;
    }

    @Override
    public boolean onOptionItemSelected(MenuItem item) {
        int i = item.getItemId();
        if(i == R.id.stat_mood) {
                Intent activity_statistics = new Intent(this,statistics.class );
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    rowLayout = (RelativeLayout)customView.findViewById(R.id.Row_layout);
        moodPos = singleMood.getMood();
        height = deviceHeight/7;
        width = deviceWidth/5;
        switch (moodPos){
            case 0: color = Color.parseColor("#ffde3c50");
                rowWidth = width * 1;
                break;
            case 1: color = Color.parseColor("#ff9b9b9b");
                rowWidth = width * 2;
                break;
            case 2: color = Color.parseColor("#a5468ad9");
                rowWidth = width * 3;
                break;
            case 3: color = Color.parseColor("#ffb8e986");
                rowWidth = width * 4;
                break;
            case 4: color = Color.parseColor("#fff9ec4f");
                rowWidth = width * 5;
                break;
            default: color = Color.BLACK;
                rowWidth = 0;
                break;
        }
        rowLayout.setBackgroundColor(color);
        resizeView(customView, rowWidth, height-10);

    return customView;*/
}

