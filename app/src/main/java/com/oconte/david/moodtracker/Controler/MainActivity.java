package com.oconte.david.moodtracker.Controler;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.oconte.david.moodtracker.History;
import com.oconte.david.moodtracker.Model.Mood;
import com.oconte.david.moodtracker.Model.OnSwipeTouchListener;
import com.oconte.david.moodtracker.R;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    ImageButton note_button;
    ImageButton history_button;

    //ImageButton date_button;
    //Date testdate = Calendar.getInstance().getTime();

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

    List<Mood> moodList = new ArrayList<>();


    /**
     * This is the Main page for the MoodTracker application.
     */
    @SuppressLint({"ClickableViewAccessibility", "CommitPrefEdits"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        moodList = getList();

        this.mood = new Mood("", 1, new Date());
        //testdate

        restartApplication();


        mColorSwipe = findViewById(R.id.smileycolor);
        mSmileySwipe = findViewById(R.id.smiley_swipe);

        mColorSwipe.setBackgroundColor(getResources().getColor(colorSwipe[mood.getMood()]));
        mSmileySwipe.setImageResource(smileySwipe[mood.getMood()]);


        noUseApplication();


        initializeView();


        /*
         * This is the part for the swipeGesture.
         */
        smiley_swipe.setOnTouchListener(new OnSwipeTouchListener(MainActivity.this) {
            public void onSwipeTop() {
                if (moodSwipe < 4) {
                    moodSwipe++;
                    mood.setMood(moodSwipe);
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
                    mood.setMood(moodSwipe);
                    setMoodsScreen();
                }
            }
        });


        /*
         * it's the comment button for add a comment.
         */
        note_button = findViewById(R.id.note_button);
        note_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LayoutInflater factory = LayoutInflater.from(MainActivity.this);
                @SuppressLint("InflateParams") final View alertDialogView = factory.inflate(R.layout.dialog_comment, null);

                final EditText mComment = alertDialogView.findViewById(R.id.dialogComment);

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

                        mood.setComment(comment);
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


        /*
         * It's the History button for start History activity
         */
        history_button = findViewById(R.id.history_button);
        history_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, History.class);
                startActivity(intent);
            }
        });

        /*
         * It's for test
         */
        /*date_button = findViewById(R.id.date_button);
        date_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar cal = Calendar.getInstance();
                cal.setTime(testdate);
                cal.add(Calendar.DATE, - 1);

                testdate = cal.getTime();

                mood.setDate(testdate);

            }
        });*/

    }

    private void restartApplication() {
        if (moodList != null && moodList.size() > 0 && this.mood != null) {
            Mood mood = moodList.get(moodList.size() - 1);

            Calendar cal = Calendar.getInstance();
            cal.setTime(this.mood.getDate());
            int day = cal.get(Calendar.DAY_OF_YEAR);

            Calendar startCal = Calendar.getInstance();
            startCal.setTime(mood.getDate());
            int startDay = startCal.get(Calendar.DAY_OF_YEAR);

            if (day == startDay) {

                this.mood = mood;
            }
        }
    }

    public void noUseApplication() {
        if (moodList != null && moodList.size() > 0 && this.mood != null) {
            Mood mood = moodList.get(moodList.size() - 1);

            Calendar cal = Calendar.getInstance();
            int day = cal.get(Calendar.DAY_OF_YEAR);

            Calendar moreCal = Calendar.getInstance();
            moreCal.setTime(mood.getDate());
            int moreDay = moreCal.get(Calendar.DAY_OF_YEAR);

            int diff = day - moreDay;

            for (int i = diff; i > 0; i--) {
                Calendar newCal = Calendar.getInstance();
                newCal.add(Calendar.DATE, -i);

                mood = new Mood("", 1, newCal.getTime());

                saveMood(mood);
            }
        }
    }

    /*
     * In this parts
     * @return
     */
    public List<Mood> getList() {
        SharedPreferences setting = getSharedPreferences("Mood", MODE_PRIVATE);
        String moodList = setting.getString("moods", "");
        Type listType = new TypeToken<ArrayList<Mood>>() {}.getType();

        return new  Gson().fromJson(moodList, listType);
    }


    /*
     * It's for save thee mood
     * @param mood
     */
    public void saveMood(Mood mood) {
        moodList.add(mood);
        saveListSharedPreferences();
    }

    /*
     * It's for save the list on the sharedPreferences
     */
    private void saveListSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("Mood",MODE_PRIVATE);
        sharedPreferences.edit().putString("moods", new Gson().toJson(moodList)).apply();
    }

    /*
     * It's for the view swipe.
     */
    private void setMoodsScreen() {
        mColorSwipe.setBackgroundColor(getResources().getColor(colorSwipe[moodSwipe]));
        mSmileySwipe.setImageResource(smileySwipe[moodSwipe]);
    }


    /*
     * I initialize the view for the swipe
     */
    private void initializeView() {
        smiley_swipe = findViewById(R.id.smiley_swipe);
    }


    /*
     * This parts it's for compare the date of mood and save it or change it before to save it.
     */
    @Override
    protected void onPause() {

        if (moodList != null && moodList.size() > 0 && this.mood != null) {
            Mood mood = moodList.get(moodList.size() - 1);

            Calendar cal = Calendar.getInstance();
            cal.setTime(this.mood.getDate());
            int day = cal.get(Calendar.DAY_OF_YEAR);

            Calendar moodCal = Calendar.getInstance();
            moodCal.setTime(mood.getDate());
            int moodDay = moodCal.get(Calendar.DAY_OF_YEAR);

            if (day == moodDay) {

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
