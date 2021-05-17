package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {
    MediaPlayer mMediaPlayer;

    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("one","lutti",R.drawable.number_one, R.raw.number_one));
        words.add(new Word("two","otiiko",R.drawable.number_two,R.raw.number_two));
        words.add(new Word("three","tooolukoso",R.drawable.number_three,R.raw.number_three));
        words.add(new Word("four","oyyi",R.drawable.number_four,R.raw.number_four));
        words.add(new Word("five","mama",R.drawable.number_five,R.raw.number_five));
        words.add(new Word("six","loks",R.drawable.number_six,R.raw.number_six));
        words.add(new Word("seven","kais",R.drawable.number_seven,R.raw.number_seven));
        words.add(new Word("eight","sssde",R.drawable.number_eight,R.raw.number_eight));
        words.add(new Word("nine","woe",R.drawable.number_nine,R.raw.number_nine));
        words.add(new Word("ten","naccha",R.drawable.number_ten,R.raw.number_ten));


//        LinearLayout rootView = (LinearLayout) findViewById(R.id.rootView);
//        for(int i = 0; i< words.size() ; i++){
//            TextView wordView = new TextView(this);
//            wordView.setText(words.get(i));
//            rootView.addView(wordView);
//        }

        WordAdapter ad = new WordAdapter(this , R.layout.list_item , words, R.color.category_numbers);
        ListView rootView = (ListView) findViewById(R.id.rootView);
        rootView.setAdapter(ad);


        rootView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word = words.get(position);

            releaseMediaPlayer();
            mMediaPlayer = MediaPlayer.create(NumbersActivity.this,word.getmAudioResourceId());
            mMediaPlayer.start();
            mMediaPlayer.setOnCompletionListener(mCompletionListener);
            }

        });

    }

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;
        }
    }
}

