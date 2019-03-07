package com.oconte.david.moodtracker;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

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
            viewHolder.pseudo = (TextView) convertView.findViewById(R.id.pseudo);
            viewHolder.text = (TextView) convertView.findViewById(R.id.text);
            viewHolder.avatar = (ImageView) convertView.findViewById(R.id.avatar);
            convertView.setTag(viewHolder);
        }

        ModelMood modelMood = getItem(position);
        viewHolder.pseudo.setText(modelMood.getPseudo());
        viewHolder.text.setText(modelMood.getText());
        viewHolder.avatar.setImageDrawable(new ColorDrawable(modelMood.getColor()));

        return convertView;
    }
    private class HistoryViewHolder {
        public TextView pseudo;
        public TextView text;
        public ImageView avatar;
    }
}
