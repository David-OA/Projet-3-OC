package com.oconte.david.moodtracker;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class history extends AppCompatActivity {

    ImageButton one_week;
    ImageButton six_days;
    ImageButton five_days;
    ImageButton four_days;
    ImageButton three_days;
    ImageButton before_day;
    ImageButton yester_day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        /////////////////////////////////////////////////////////
        // Partie sur les boutons commentaire                 //
        ///////////////////////////////////////////////////////
        one_week = (ImageButton) findViewById(R.id.one_week);
        one_week.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(history.this, "Il est vivant !!!!!!!!!!!!!!!!!", Toast.LENGTH_SHORT ).show();
                //String.getString(COMMENT, String null);
            }
        });

        six_days = (ImageButton) findViewById(R.id.six_days);
        six_days.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(history.this, "Il encore vivant !!!!!!!!!!!!!!!!!", Toast.LENGTH_SHORT ).show();
            }
        });

        five_days = (ImageButton) findViewById(R.id.five_days);
        five_days.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(history.this, "Il est peut être vivant !!!!!!!!!!!!!!!!!", Toast.LENGTH_SHORT ).show();
            }
        });

        four_days = (ImageButton) findViewById(R.id.four_days);
        four_days.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(history.this, "Il respire un peu !!!!!!!!!!!!!!!!!", Toast.LENGTH_SHORT ).show();
            }
        });

        three_days = (ImageButton) findViewById(R.id.three_days);
        three_days.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(history.this, "Il respire plus trop  !!!!!!!!!!!!!!!!!", Toast.LENGTH_SHORT ).show();
            }
        });

        before_day = (ImageButton) findViewById(R.id.before_day);
        before_day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(history.this, "Il est pas trop vivant là !!!!!!!!!!!!!!!!!", Toast.LENGTH_SHORT ).show();
            }
        });

        yester_day = (ImageButton) findViewById(R.id.yester_day);
        yester_day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(history.this, " Lance une pierre dessus pour voir !!!!!!!!!!!!!!!!!", Toast.LENGTH_SHORT ).show();
            }
        });
    }
}
