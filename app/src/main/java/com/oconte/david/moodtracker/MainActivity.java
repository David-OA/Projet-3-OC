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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;


public class MainActivity extends AppCompatActivity {

    ImageButton note_button;
    ImageButton history_button;

    // Variables for the swipe.

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

    Mood mood;

    // Variables for save.

    EditText mComment;
    List<Mood> moodList = new ArrayList<>();


    /**
     * This is the Main page for the MoodTracker application.
     * @param savedInstanceState
     */
    @SuppressLint({"ClickableViewAccessibility", "CommitPrefEdits"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        moodList = getList();

        this.mood = new Mood("", 1, new Date());

        mColorSwipe = findViewById(R.id.smileycolor);
        mSmileySwipe = findViewById(R.id.smiley_swipe);

        mColorSwipe.setBackgroundColor(getResources().getColor(colorSwipe[moodSwipe]));
        mSmileySwipe.setImageResource(smileySwipe[moodSwipe]);

        Calendar calendar = Calendar.getInstance();

        initializeView();

        /**
         * This is the part for the swipeGesture.
         */
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


        /**
         * it's the comment button for add a comment.
         */
        note_button = (ImageButton) findViewById(R.id.note_button);
        note_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LayoutInflater factory = LayoutInflater.from(MainActivity.this);
                final View alertDialogView = factory.inflate(R.layout.dialog_comment, null);

                final EditText mComment = (EditText) alertDialogView.findViewById(R.id.dialogComment);

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

                        mood = new Mood (comment, moodSwipe, new Date());
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


        /**
         * It's the history button for start history activity
         */
        history_button = (ImageButton) findViewById(R.id.history_button);
        history_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, history.class);
                startActivity(intent);
            }
        });
    }

    /**
     *
     * @return
     */
    public List<Mood> getList() {
        SharedPreferences setting = getSharedPreferences("Mood", MODE_PRIVATE);
        String moodList = setting.getString("moods", "");
        Type listType = new TypeToken<ArrayList<Mood>>() {}.getType();
        List<Mood> list = new  Gson().fromJson(moodList, listType);

        return list;
    }


    /**
     *
     * @param mood
     */
    public void saveMood(Mood mood) {
        moodList.add(mood);
        saveListSharedPreferences();
    }

    /**
     *
     */
    private void saveListSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("Mood",MODE_PRIVATE);
        sharedPreferences.edit().putString("moods", new Gson().toJson(moodList)).apply();
    }


    // Methode pour le swipe color et smiley

    /**
     * It's for the view swipe.
     *
     */
    private void setMoodsScreen() {
        mColorSwipe.setBackgroundColor(getResources().getColor(colorSwipe[moodSwipe]));
        mSmileySwipe.setImageResource(smileySwipe[moodSwipe]);
    }


    /**
     * I initialize the view for the swipe
     */
    private void initializeView() {
        smiley_swipe =(ImageView) findViewById(R.id.smiley_swipe);
    }


    /**
     * This parts it's for compare the date of mood and save it or change it before to save it.
     */
    @Override
    protected void onPause() {

        // finir la comparaison des dates pour savoir si je remplace ou is j'ajoute un mood
        if (moodList != null && moodList.size() > 0 && this.mood != null) {
            Mood mood = moodList.get(moodList.size() - 1);

            final Date date = Calendar.getInstance().getTime();

            Calendar cal = Calendar.getInstance();
            cal.setTime(this.mood.date);
            int day = cal.get(Calendar.DAY_OF_YEAR);

            Calendar moodCal = Calendar.getInstance();
            moodCal.setTime(mood.date);
            int moodDay = cal.get(Calendar.DAY_OF_YEAR);

            if (day == moodDay) {
                //remplacer  dans la list actuelle et ensuite enregistrer dans sharedpreference
                moodList.set(moodList.size() - 1, this.mood);
                saveListSharedPreferences();

            } else {
                saveMood(this.mood);
            }
        } else if (moodList != null && moodList.size() == 0) {
            saveMood(this.mood);
        } else if (moodList == null) {
            moodList = new ArrayList<>();
            saveMood(this.mood);
        }

        super.onPause();
    }
}
