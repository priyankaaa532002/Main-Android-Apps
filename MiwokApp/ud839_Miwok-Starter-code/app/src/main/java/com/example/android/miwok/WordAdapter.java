package com.example.android.miwok;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class WordAdapter extends ArrayAdapter<Word> {

    private int mColorResourceId;
    ArrayList<Word> words = new ArrayList<>();
    public WordAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Word> objects,int colorResourceId) {
        super(context, resource, objects);
        mColorResourceId = colorResourceId;
        words = objects;
    }

    @Nullable
    @Override
    public Word getItem(int position) {
        return words.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        TextView textView = (TextView) convertView.findViewById(R.id.text1);
        textView.setText(getItem(position).getmDefaultTranslation());
        TextView textView1 = (TextView) convertView.findViewById(R.id.text2);
        textView1.setText(getItem(position).getmMiwokTranslation());

        ImageView imageView = (ImageView) convertView.findViewById(R.id.image);
        imageView.setImageResource(getItem(position).getmImageResourceId());

        int color = ContextCompat.getColor((getContext()), mColorResourceId);
        LinearLayout view = (LinearLayout) convertView.findViewById(R.id.textContainer);
        view.setBackgroundColor(color);

        return convertView;
    }
}
