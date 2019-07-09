package com.oconte.david.moodtracker.Model;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.oconte.david.moodtracker.Model.Mood;
import com.oconte.david.moodtracker.R;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * It's the Adapter for History
 */
public class HistoryMoodAdapter extends ArrayAdapter<Mood> {

    private Context context;

    private int [] moodSwipe = new int[] {
            R.color.banana_yellow,
            R.color.light_sage,
            R.color.cornflower_blue_65,
            R.color.warm_grey,
            R.color.faded_red
    };

    private float [] moodWeight = new float[] {
            0f,
            0.2f,
            0.4f,
            0.6f,
            0.8f,
    };

    private List<Mood> moods;

    /*
     * It's the adapter for listView.
     * @param context
     * @param moods
     */
    public HistoryMoodAdapter(Context context, List<Mood> moods) {
        super(context, 0, moods);
        this.context = context;
        this.moods = moods;
    }


    /*
     * It's the different elements of view.
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_mood,parent, false);

        }

        HistoryViewHolder viewHolder = (HistoryViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new HistoryViewHolder();

            viewHolder.title = convertView.findViewById(R.id.title);

            viewHolder.avatar = convertView.findViewById(R.id.avatar);

            viewHolder.week = convertView.findViewById(R.id.week);

            viewHolder.weight = convertView.findViewById(R.id.weight);

            convertView.setTag(viewHolder);

        }

        final Mood mood = getItem(position);

        if (mood.getComment() == null || mood.getComment().isEmpty())
            viewHolder.avatar.setVisibility(View.GONE);

        final Date date = Calendar.getInstance().getTime();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int Day = cal.get(Calendar.DAY_OF_YEAR);

        Calendar moodCal = Calendar.getInstance();
        moodCal.setTime(mood.getDate());
        int MoodDay = moodCal.get(Calendar.DAY_OF_YEAR);

        int diff = Day - MoodDay;
        if (diff < 7) {
            viewHolder.title.setText(getDay(diff));
        }

        viewHolder.week.setBackgroundResource(moodSwipe[mood.getMood()]);

        viewHolder.week.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, moodWeight[mood.getMood()]));

        viewHolder.weight.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1 -moodWeight[mood.getMood()]));

        viewHolder.avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,mood.getComment(), Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }

    /*
     * It's for see the date in String
     * @param day the string for differents date
     * @return
     */
    private String getDay(int day) {
        String[] textDate = new String[]{
                "Aujourd'hui",
                "Hier",
                "Avant-hier",
                "Il y a trois jours",
                "Il y a quatre jours",
                "Il y a cinq jours",
                "Il y a six jours",
                "Il y a une semaine"
        };

        return textDate [day];
    }

    /*
     * It's for see just 7 items.
     * @return
     */
    public int getCount() {
        if (moods != null) {
            if (moods.size() <= 7) {

                return moods.size();

            } return 7;

        } return 0;
    }


    /*
     * It's for view
     */
    private class HistoryViewHolder {
        TextView title;
        ImageButton avatar;
        RelativeLayout week;
        View weight;
    }
}
