package com.oconte.david.moodtracker;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class HistoryMoodAdapter extends ArrayAdapter<Mood> {

    Context context;

    ImageButton avatar;

    RelativeLayout week;

    private String mtextDate;

    public int [] moodSwipe = new int[] {
            R.color.banana_yellow,
            R.color.light_sage,
            R.color.cornflower_blue_65,
            R.color.warm_grey,
            R.color.faded_red
    };

    private SharedPreferences mPreferences;

    public float [] moodWeight = new float[] {
            0f,
            0.2f,
            0.4f,
            0.6f,
            0.8f,
    };

    List<Mood> moods;

    /**
     *
     * @param context
     * @param moods
     */
    public HistoryMoodAdapter(Context context, List<Mood> moods) {
        super(context, 0, moods);
        this.context = context;
        this.moods = moods;
    }


    /**
     *
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_mood,parent, false);

        }

        HistoryViewHolder viewHolder = (HistoryViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new HistoryViewHolder();

            viewHolder.title = (TextView) convertView.findViewById(R.id.title);

            viewHolder.avatar = (ImageButton) convertView.findViewById(R.id.avatar);

            viewHolder.week = (RelativeLayout) convertView.findViewById(R.id.week);

            viewHolder.weight = convertView.findViewById(R.id.weight);

            convertView.setTag(viewHolder);

        }

        ////////////////////////////////////////////////////////////////////////////////////////////
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) viewHolder.week.getLayoutParams();
        params.height = 120;
        viewHolder.week.setLayoutParams(params);
        ///////////////////////////////////////////////////////////////////////////////////////////

        final Mood mood = getItem(position);
        if (mood.getComment() == null || mood.getComment().isEmpty())
            viewHolder.avatar.setVisibility(View.GONE);

        final Date date = Calendar.getInstance().getTime();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int Day = cal.get(Calendar.DAY_OF_YEAR);

        Calendar moodCal = Calendar.getInstance();
        moodCal.setTime(mood.date);
        int MoodDay = cal.get(Calendar.DAY_OF_YEAR);

        viewHolder.title.setText(getDay(Day - MoodDay));

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

    /**
     *
     * @param day
     * @return
     */
    public String getDay(int day) {
        String[] textDate = new String[]{
                "Il y a une semaine",
                "Il y a six jours",
                "Il y a cinq jours",
                "Il y a quatre jours",
                "Il y a trois jours",
                "Avant-hier",
                "Hier"
        };

        return textDate [day];
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     *
     * @return
     */
    public int getCount() {
        if (moods != null) {
            if (moods.size() <= 7) {

                return moods.size();

            } return 7;

        } return 0;
    }

    private class HistoryViewHolder {
        TextView title;
        ImageButton avatar;
        RelativeLayout week;
        View weight;
    }
}
