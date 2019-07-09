package com.oconte.david.moodtracker;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.oconte.david.moodtracker.Model.Mood;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;


/**
 * This is for the Statistics parts.
 */
public class Statistics extends AppCompatActivity {

    List<Mood> moodList = new ArrayList<>();

    PieChartView pieChartView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        moodList = getList();

        pieChartView = findViewById(R.id.chart);

        List<SliceValue> pieData = new ArrayList<>();

        //SliceValue : Float the size of pie chart and Color  wich you can set for that particular pie chart

        int superHappy = pourCentSuperHappy(0);
        int happy = pourCentSuperHappy(1);
        int normal = pourCentSuperHappy(2);
        int disappointed = pourCentSuperHappy(3);
        int sad = pourCentSuperHappy(4);


        pieData.add(new SliceValue(superHappy, getResources().getColor(R.color.banana_yellow)).setLabel("super_happy"));
        pieData.add(new SliceValue(happy, getResources().getColor(R.color.light_sage)).setLabel("happy"));
        pieData.add(new SliceValue(normal, getResources().getColor(R.color.cornflower_blue_65)).setLabel("normal"));
        pieData.add(new SliceValue(disappointed, getResources().getColor(R.color.warm_grey)).setLabel("disappointed"));
        pieData.add(new SliceValue(sad, getResources().getColor(R.color.faded_red)).setLabel("sad"));

        PieChartData pieChartData = new PieChartData(pieData);
        pieChartData.setHasLabels(true).setValueLabelTextSize(14);
        pieChartView.setPieChartData(pieChartData);
    }

    /*
     * Take information
     * @return
     */
    public List<Mood> getList() {
        SharedPreferences setting = getSharedPreferences("Mood", MODE_PRIVATE);
        String moodList = setting.getString("moods", "");
        Type listType = new TypeToken<ArrayList<Mood>>() {}.getType();

        return new Gson().fromJson(moodList, listType);
    }

    /*
     * Calcul pourcentage
     * @param mood
     * @return
     */
    public int pourCentSuperHappy (int mood) {
        return (int)(100*((float)nbreSupperHappy(mood) / (float) moodList.size()));
    }

    /*
     * In this parts is for nomber of evry mood
     * @param mood
     * @return
     */
    public int nbreSupperHappy(int mood) {
        int count = 0;
        for (int i = 0; i < moodList.size(); i ++) {
            if (moodList.get(i).getMood() == mood) {
                count ++;
            }
        } return count;
    }
}
