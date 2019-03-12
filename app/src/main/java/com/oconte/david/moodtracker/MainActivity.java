package com.oconte.david.moodtracker;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static com.oconte.david.moodtracker.Mood.PREF_KEY_COMMENT0;

public class MainActivity extends AppCompatActivity {

    ImageButton note_button;
    ImageButton history_button;

    ImageView smiley_swipe;
    //TextView tvSwipDescription;

    SharedPreferences mPreferences;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeView();
        smiley_swipe.setOnTouchListener(new OnSwipeTouchListener(MainActivity.this) {
            public void onSwipeTop() {
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.toastTop), Toast.LENGTH_SHORT).show();
            }
            public void onSwipeRight() {
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.toastRight), Toast.LENGTH_SHORT).show();
            }
            public void onSwipeLeft() {
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.toastLeft), Toast.LENGTH_SHORT).show();
            }
            public void onSwipeBottom() {
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.toastBottom), Toast.LENGTH_SHORT).show();
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

                        mPreferences = getBaseContext().getSharedPreferences(PREF_KEY_COMMENT0, MODE_PRIVATE);
                        //sauvegarder le commentaire
                        mPreferences
                                .edit()
                                .putString(PREF_KEY_COMMENT0, "")
                                .apply();

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

    //smiley_swipe = (ImageView) findViewById(R.id.smiley_swipe);
    private void initializeView() {
        smiley_swipe =(ImageView) findViewById(R.id.smiley_swipe);
    }
}
