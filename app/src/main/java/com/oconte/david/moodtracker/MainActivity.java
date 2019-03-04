package com.oconte.david.moodtracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageButton smiley_botton;
    ImageButton note_button;
    ImageButton history_botton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        smiley_botton = (ImageButton) findViewById(R.id.smiley_botton);
        smiley_botton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                smiley_botton.setImageResource(R.drawable.smiley_sad);
            }
        });

        note_button = (ImageButton) findViewById(R.id.note_button);
        note_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "youpi dou " , Toast.LENGTH_LONG ).show();
            }
        });

        //Bouton de de l'activity history
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
}
