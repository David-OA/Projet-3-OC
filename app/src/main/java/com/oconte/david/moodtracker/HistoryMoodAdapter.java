package com.oconte.david.moodtracker;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


public class HistoryMoodAdapter extends ArrayAdapter<Mood> {

    Context context;

    ImageButton avatar;

    private SharedPreferences mPreferences;

    public HistoryMoodAdapter(Context context, List<Mood> Moods) {
        super(context, 0, Moods);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_mood,parent, false);
        }

        HistoryViewHolder viewHolder = (HistoryViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new HistoryViewHolder();
            //viewHolder.title = (TextView) convertView.findViewById(R.id.title);
            viewHolder.avatar = (ImageButton) convertView.findViewById(R.id.avatar);


            convertView.setTag(viewHolder);

        }

        Mood mood = getItem(position);
        //viewHolder.title.setText(Mood.getTitle());

        viewHolder.avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences mPreferences = context.getSharedPreferences("Mood", Context.MODE_PRIVATE);
                Gson gson = new Gson();
                String json = mPreferences.getString("Mood", "");
                Mood mood = gson.fromJson(json, Mood.class);
                Toast.makeText(context,mood.getComment(), Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }

    private class HistoryViewHolder {
        //public TextView title;
        public ImageButton avatar;
    }
}
