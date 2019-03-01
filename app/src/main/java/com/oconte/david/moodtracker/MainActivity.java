package com.oconte.david.moodtracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
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
                Toast.makeText(MainActivity.this, "ca marche" , Toast.LENGTH_LONG ).show();
            }
        });


        note_button = (ImageButton) findViewById(R.id.note_button);

        note_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "ca marche" , Toast.LENGTH_LONG ).show();
            }
        });

        history_botton = (ImageButton) findViewById(R.id.history_botton);

        history_botton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "ca marche" , Toast.LENGTH_LONG ).show();
            }
        });
    }
}
