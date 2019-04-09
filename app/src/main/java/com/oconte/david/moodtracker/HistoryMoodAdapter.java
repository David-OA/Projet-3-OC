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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


public class HistoryMoodAdapter extends ArrayAdapter<Mood> {

    Context context;

    ImageButton avatar;

    RelativeLayout week;

    //public int moodSwipe;

    public int [] moodSwipe = new int[] {
            R.color.banana_yellow,
            R.color.light_sage,
            R.color.cornflower_blue_65,
            R.color.warm_grey,
            R.color.faded_red
    };

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

            viewHolder.week = (RelativeLayout) convertView.findViewById(R.id.week);

            convertView.setTag(viewHolder);

        }

        ////////////////////////////////////////////////////////////////////////////////////////////

        final Mood mood = getItem(position);
        //viewHolder.title.setText(Mood.getTitle());

        //viewHolder.week.getResources(setBanner()); test1

        ////////////////////////////////////////////////////////////////////////////////////////////
        //test2

        if(Moods.get(position).get(moodSwipe) == 0) {
            viewHolder.week.setBackgroundResource(R.color.banana_yellow);
        }
        else if (Moods.get(position).get(moodSwipe) == 1) {
            viewHolder.week.setBackgroundResource(R.color.light_sage);
        }
        else if (Moods.get(position).get(moodSwipe) == 2) {
            viewHolder.week.setBackgroundResource(R.color.cornflower_blue_65);
        }
        else if (Moods.get(position).get(moodSwipe) == 3) {
            viewHolder.week.setBackgroundResource(R.color.warm_grey);
        }
        else if (Moods.get(position).get(moodSwipe) == 4) {
            viewHolder.week.setBackgroundResource(R.color.faded_red);
        }

        ////////////////////////////////////////////////////////////////////////////////////////////

        viewHolder.avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,mood.getComment(), Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //test1
    /*public void setBanner() {
        if (moodSwipe == 0) {
            //convertView.findViewById(R.id.week);
            week.setBackgroundResource(R.color.banana_yellow);
        } else if (moodSwipe == 1) {
            //convertView.findViewById(R.id.week);
            week.setBackgroundResource(R.color.light_sage);
        } else if (moodSwipe == 2) {
            //convertView.findViewById(R.id.week);
            week.setBackgroundResource(R.color.cornflower_blue_65);
        } else if (moodSwipe == 3) {
            //convertView.findViewById(R.id.week);
            week.setBackgroundResource(R.color.warm_grey);
        } else if (moodSwipe == 4) {
            //convertView.findViewById(R.id.week);
            week.setBackgroundResource(R.color.faded_red);
        }
    }*/

    ////////////////////////////////////////////////////////////////////////////////////////////////




    ////////////////////////////////////////////////////////////////////////////////////////////////
    private class HistoryViewHolder {
        //public TextView title;
        public ImageButton avatar;
        public RelativeLayout week;
    }
}
