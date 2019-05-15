package com.oconte.david.moodtracker;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;

public class statistics extends AppCompatActivity {

    Context context;

    List<Mood> moodList = new ArrayList<>();

    int nbreSupperHappy;

    PieChartView pieChartView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        moodList = getList();

        pieChartView = findViewById(R.id.chart);

        List pieData = new ArrayList<>();

        //SliceValue : Float the size of pie chart and Color  wich you can set for that particular pie chart

        pieData.add(new SliceValue(15, getResources().getColor(R.color.banana_yellow)).setLabel("super_happy"));
        pieData.add(new SliceValue(25, getResources().getColor(R.color.light_sage)).setLabel("happy"));
        pieData.add(new SliceValue(10, getResources().getColor(R.color.cornflower_blue_65)).setLabel("normal"));
        pieData.add(new SliceValue(30, getResources().getColor(R.color.warm_grey)).setLabel("disappointed"));
        pieData.add(new SliceValue(30, getResources().getColor(R.color.faded_red)).setLabel("sad"));

        PieChartData pieChartData = new PieChartData(pieData);
        pieChartData.setHasLabels(true).setValueLabelTextSize(14);
        pieChartView.setPieChartData(pieChartData);
    }

    public List<Mood> getList() {
        SharedPreferences setting = getSharedPreferences("Mood", MODE_PRIVATE);
        String moodList = setting.getString("moods", "");
        Type listType = new TypeToken<ArrayList<Mood>>() {}.getType();
        List<Mood> list = new Gson().fromJson(moodList, listType);

        return list;
    }

    public void pourCentSuperHappy () {
        100*(nbreSupperHappy() / moodList.size());
    }

    public int nbreSupperHappy() {
        for (int i = 0; i >= 0; i ++) {
            if (moodList.get(0)) {
                nbreSupperHappy ++;
            }
        }
    }

    public void pourCentHappy () {
        //reponse = 100* (nbreHappy / moodList.size());

    }

    public void pourCentNormal () {
        //reponse = 100* (nbreNormal / moodList.size());

    }

    public void pourCentDisappointed () {
        //reponse = 100* (nbreDisappointed / moodList.size());

    }

    public void pourCentSad () {
        //reponse = 100* (nbreSad / moodList.size());

    }
}
