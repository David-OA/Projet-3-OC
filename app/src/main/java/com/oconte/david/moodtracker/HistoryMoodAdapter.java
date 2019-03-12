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

import java.util.List;


public class HistoryMoodAdapter extends ArrayAdapter<ModelMood> {

    public HistoryMoodAdapter(Context context, List<ModelMood> ModelMoods) {
        super(context, 0, ModelMoods);
    }

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

            convertView.setTag(viewHolder);
        }

        ModelMood modelMood = getItem(position);
        viewHolder.title.setText(modelMood.getTitle());


        return convertView;
    }

    private class HistoryViewHolder {
        public TextView title;
        public ImageButton avatar;
    }

    /*ImageButton avatar = (ImageButton) ImageButton.findViewById(R.id.avatar);
    avatar.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {
            Toast.makeText(history.this, "miam", Toast.LENGTH_SHORT).show();
        }
    });*/
}
