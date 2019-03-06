package com.oconte.david.moodtracker;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageButton note_button;
    ImageButton history_botton;

    ImageView smiley_swipe;

    private static final String COMMENT = "COMMENT";
    SharedPreferences sharedPreferences;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //////////////////////////////////////////////////////////////////////
        ///        Partie sur le swipe                                    ///
        ////////////////////////////////////////////////////////////////////

        initializeView();
        smiley_swipe.setOnTouchListener(new OnSwipeTouchListener(MainActivity.this) {
            public void onSwipeTop() {
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.toastTop), Toast.LENGTH_SHORT).show();
                //smiley_swipe.setImageResource(R.drawable.smiley_normal);
                //smiley_swipe.setBackgroundResource(R.color.cornflower_blue_65);

                //smiley_swipe.setImageResource(R.drawable.smiley_disappointed);
                //smiley_swipe.setBackgroundResource(R.color.warm_grey);

                //smiley_swipe.setImageResource(R.drawable.smiley_sad);
                //smiley_swipe.setBackgroundResource(R.color.faded_red);
            }
            public void onSwipeRight() {
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.toastRight), Toast.LENGTH_SHORT).show();
            }
            public void onSwipeLeft() {
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.toastLeft), Toast.LENGTH_SHORT).show();
            }
            public void onSwipeBottom() {
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.toastBottom), Toast.LENGTH_SHORT).show();
                //smiley_swipe.setImageResource(R.drawable.smiley_super_happy);
                //smiley_swipe.setBackgroundResource(R.color.banana_yellow);
            }
        });


        /////////////////////////////////////////////////////////////////////
        // Partie sur le boutton commentaire                           /////
        ///////////////////////////////////////////////////////////////////
        note_button = (ImageButton) findViewById(R.id.note_button);
        note_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this, "youpi dou " , Toast.LENGTH_LONG ).show();

                LayoutInflater factory = LayoutInflater.from(MainActivity.this);
                final View alertDialogView = factory.inflate(R.layout.dialog_comment, null);

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                alertDialog.setMessage("Commentaire");

                alertDialog.setView(alertDialogView);

                alertDialog.setPositiveButton("VALIDER", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int arg1) {

                        sharedPreferences = getBaseContext().getSharedPreferences(COMMENT, MODE_PRIVATE);
                        //sauvegarder le commentaire
                        sharedPreferences
                                .edit()
                                .putString(COMMENT, "")
                                .apply();

                        // String COMMENT = preferences.getString(COMMENT, "")
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
        history_botton = (ImageButton) findViewById(R.id.history_botton);
        history_botton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "tutu" , Toast.LENGTH_LONG ).show();
                Intent intent = new Intent(MainActivity.this, history.class);
                startActivity(intent);
            }
        });
    }

    private void initializeView() {
        smiley_swipe = (ImageView) findViewById(R.id.smiley_swipe);
    }
}
