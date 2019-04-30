package com.oconte.david.moodtracker;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;

public class statistics extends AppCompatActivity {

    Context context;

    PieChartView pieChartView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        pieChartView = findViewById(R.id.chart);

        List pieData = new ArrayList<>();

        //SliceValue : Float the size of pie chart and Color  wich you can set for that particular pie chart

        pieData.add(new SliceValue(15, getColor(R.color.banana_yellow)).setLabel("super_happy"));
        pieData.add(new SliceValue(25, getColor(R.color.light_sage)).setLabel("happy"));
        pieData.add(new SliceValue(10, getColor(R.color.cornflower_blue_65)).setLabel("normal"));
        pieData.add(new SliceValue(30, getColor(R.color.warm_grey)).setLabel("disappointed"));
        pieData.add(new SliceValue(30, getColor(R.color.faded_red)).setLabel("sad"));

        PieChartData pieChartData = new PieChartData(pieData);
        pieChartData.setHasLabels(true).setValueLabelTextSize(14);
        pieChartView.setPieChartData(pieChartData);
    }
}
