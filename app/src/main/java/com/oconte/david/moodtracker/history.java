package com.oconte.david.moodtracker;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class history extends AppCompatActivity {

    ImageButton one_week;
    ImageButton six_days;
    ImageButton five_days;
    ImageButton four_days;
    ImageButton three_days;
    ImageButton before_day;
    ImageButton yester_day;

    ListView mList_history_mood;
    String[] prenoms = new String[]{
            "Antoine", "Benoit", "Cyril", "David", "Eloise", "Florent",
            "Gerard", "Hugo", "Ingrid", "Jonathan", "Kevin", "Logan",
            "Mathieu", "Noemie", "Olivia", "Philippe", "Quentin", "Romain",
            "Sophie", "Tristan", "Ulric", "Vincent", "Willy", "Xavier", "Yann", "Zoé"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

            mList_history_mood = (ListView) findViewById(R.id.list_history_mood);
            viewListModelMood();
        }

        private void viewListName(){
                //android.R.layout.simple_list_item_1 est une vue disponible de base dans le SDK android,
                //Contenant une TextView avec comme identifiant "@android:id/text1"

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(history.this, android.R.layout.simple_list_item_1, prenoms);
            mList_history_mood.setAdapter(adapter);
        }

        private List<ModelMood> genererModelMood(){
                List<ModelMood> tweets = new ArrayList<ModelMood>();
                tweets.add(new ModelMood(Color.BLACK, "Florent", "Mon premier tweet !"));
                tweets.add(new ModelMood(Color.BLUE, "Kevin", "C'est ici que ça se passe !"));
                tweets.add(new ModelMood(Color.GREEN, "Logan", "Que c'est beau..."));
                tweets.add(new ModelMood(Color.RED, "Mathieu", "Il est quelle heure ??"));
                tweets.add(new ModelMood(Color.GRAY, "Willy", "On y est presque"));
                return tweets;
        }

        private void viewListModelMood(){
                List<ModelMood> tweets = genererModelMood();

            HistoryMoodAdapter adapter = new HistoryMoodAdapter(history.this, tweets);
            mList_history_mood.setAdapter(adapter);
        }
        /*

        List<history> histories = genererhistories();
        HistoryMoodAdapter adapter = new HistoryMoodAdapter(history.this, histories);
        mList_history_mood.setAdapter(adapter);*/


        /////////////////////////////////////////////////////////
        // Partie sur les boutons commentaire                 //
        ///////////////////////////////////////////////////////
        /*one_week = (ImageButton) findViewById(R.id.one_week);
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
        });*/
}

