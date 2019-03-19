package com.oconte.david.moodtracker;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    ImageButton note_button;
    ImageButton history_button;


    // Variable pour le swipe

    private RelativeLayout mColorSwipe;
    private ImageView mSmileySwipe;

    public int [] smileySwipe = new int [] {
            R.drawable.smiley_happy,
            R.drawable.smiley_super_happy,
            R.drawable.smiley_normal,
            R.drawable.smiley_disappointed,
            R.drawable.smiley_sad
    };

    public int [] colorSwipe = new int[] {
            R.color.light_sage,
            R.color.banana_yellow,
            R.color.cornflower_blue_65,
            R.color.warm_grey,
            R.color.faded_red
    };
    private int moodSwipe = 3;
    ImageView smiley_swipe;




    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mColorSwipe = findViewById(R.id.smileycolor);
        mSmileySwipe = findViewById(R.id.smiley_swipe);

        mColorSwipe.setBackgroundColor(getResources().getColor(colorSwipe[moodSwipe]));
        mSmileySwipe.setImageResource(smileySwipe[moodSwipe]);

        initializeView();
        smiley_swipe.setOnTouchListener(new OnSwipeTouchListener(MainActivity.this) {
            public void onSwipeTop() {
                if (moodSwipe < 4) {
                    moodSwipe++;
                    setMoodsScreen();
                }
            }
            public void onSwipeRight() {
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.toastRight), Toast.LENGTH_SHORT).show();
            }
            public void onSwipeLeft() {
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.toastLeft), Toast.LENGTH_SHORT).show();
            }
            public void onSwipeBottom() {
                if (moodSwipe > 0) {
                    moodSwipe--;
                    setMoodsScreen();
                }
            }
        });


        /////////////////////////////////////////////////////////////////////
        // Partie sur le boutton commentaire                           /////
        ///////////////////////////////////////////////////////////////////
        note_button = (ImageButton) findViewById(R.id.note_button);
        note_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LayoutInflater factory = LayoutInflater.from(MainActivity.this);
                final View alertDialogView = factory.inflate(R.layout.dialog_comment, null);

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                alertDialog.setMessage("Commentaire");

                alertDialog.setView(alertDialogView);

                alertDialog.setPositiveButton("VALIDER", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int arg1) {

                        SharedPreferences mPreferences = getPreferences(MODE_PRIVATE);
                        SharedPreferences.Editor editor = mPreferences.edit();
                        Mood mood = new Mood();
                        Gson gson = new Gson();
                        String json = gson.toJson(mood);
                        editor.putString("Mood", json);
                        editor.apply();

                    }
                });
                alertDialog.setNegativeButton("ANNULER", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int arg1) {
                        Toast.makeText(MainActivity.this, "Pas d'avis", Toast.LENGTH_LONG).show();
                    }
                });
                alertDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {

                    }
                });
                alertDialog.show();
            }
        });


        /////////////////////////////////////////////////////////////////////
        // Partie sur le boutton history                               /////
        ///////////////////////////////////////////////////////////////////
        history_button = (ImageButton) findViewById(R.id.history_button);
        history_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, history.class);
                startActivity(intent);
            }
        });
    }

    private void setMoodsScreen() {
        mColorSwipe.setBackgroundColor(getResources().getColor(colorSwipe[moodSwipe]));
        mSmileySwipe.setImageResource(smileySwipe[moodSwipe]);
    }

    /*Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.HOUR_OF_DAY, 19);
        calendar.set(Calendar.MINUTE, 55);
        calendar.set(Calendar.SECOND, 50);

    Intent intent = new Intent(getApplicationContext(), NotificationReceiver.class);
    PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 100, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        assert alarmManager != null;
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
    }*/



    private void initializeView() {
        smiley_swipe =(ImageView) findViewById(R.id.smiley_swipe);
    }
}
