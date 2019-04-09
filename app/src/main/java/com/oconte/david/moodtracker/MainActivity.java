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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    ImageButton note_button;
    ImageButton history_button;


    // Variable pour le swipe

    private RelativeLayout mColorSwipe;
    private ImageView mSmileySwipe;

    public int [] smileySwipe = new int [] {
            R.drawable.smiley_super_happy,
            R.drawable.smiley_happy,
            R.drawable.smiley_normal,
            R.drawable.smiley_disappointed,
            R.drawable.smiley_sad
    };

    public int [] colorSwipe = new int[] {
            R.color.banana_yellow,
            R.color.light_sage,
            R.color.cornflower_blue_65,
            R.color.warm_grey,
            R.color.faded_red
    };
    private int moodSwipe = 1;
    ImageView smiley_swipe;


    // variables pour les sauvegardes

    EditText mComment;
    List<Mood> moodList = new ArrayList<>();


    @SuppressLint({"ClickableViewAccessibility", "CommitPrefEdits"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mColorSwipe = findViewById(R.id.smileycolor);
        mSmileySwipe = findViewById(R.id.smiley_swipe);

        mColorSwipe.setBackgroundColor(getResources().getColor(colorSwipe[moodSwipe]));
        mSmileySwipe.setImageResource(smileySwipe[moodSwipe]);

        Calendar calendar = Calendar.getInstance();

        initializeView();
        smiley_swipe.setOnTouchListener(new OnSwipeTouchListener(MainActivity.this) {
            public void onSwipeTop() {
                if (moodSwipe < 4) {
                    moodSwipe++;
                    setMoodsScreen();
                }
            }

            public void onSwipeRight() {
            }
            public void onSwipeLeft() {
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

                final EditText mComment = (EditText) alertDialogView.findViewById(R.id.dialogComment); // je précise ou trouver la zone de saisie et la vue

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                alertDialog.setMessage("Commentaire");

                alertDialog.setView(alertDialogView);

                alertDialog.setPositiveButton("VALIDER", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int arg1) {

                        SharedPreferences mPreferences = getSharedPreferences("Mood",MODE_PRIVATE);
                        SharedPreferences.Editor editor = mPreferences.edit();

                        String comment = mComment.getText().toString(); //
                        mComment.getText();

                        Mood mood = new Mood (comment, moodSwipe, new Date());
                        saveMood(mood);

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


    ////////////////////////////////////////////////////////////////////////////////////////////////
    public void saveMood(Mood mood) {
        moodList.add(mood);
        saveListSharedPreferences();
    }

    private void saveListSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("Mood",MODE_PRIVATE);
        sharedPreferences.edit().putString("moods", new Gson().toJson(moodList)).apply();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    // Methode pour le swipe color et smiley
    private void setMoodsScreen() {
        mColorSwipe.setBackgroundColor(getResources().getColor(colorSwipe[moodSwipe]));
        mSmileySwipe.setImageResource(smileySwipe[moodSwipe]);
    }


    private void initializeView() {
        smiley_swipe =(ImageView) findViewById(R.id.smiley_swipe);
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////
    /*@Override
    protected void onDestroy() {
        super.onDestroy();
        Mood mood = moodList.get(0);
        Date date = Calendar.getInstance().getTime();

        if(mood.date.compareTo(date) = date ) {
            System.out.println("vive Android");

        } else if (mood.date.compareTo(date) != date ) {
            System.out.println("Android un jour, Android toujours");
        }


        String dateString = "20/12/2018";
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date readDate = df.parse(dateString);
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(readDate.getTime());
        Log.d(TAG, "Year: "+cal.get(Calendar.YEAR));
        Log.d(TAG, "Month: "+cal.get(Calendar.MONTH));
        Log.d(TAG, "Day: "+cal.get(Calendar.DAY_OF_MONTH));



    }*/
}
