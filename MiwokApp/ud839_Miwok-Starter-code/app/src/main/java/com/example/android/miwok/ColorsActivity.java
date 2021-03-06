package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_colors);

        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("red","weteti",R.drawable.color_red, R.raw.color_red));
        words.add(new Word("mustard","chichi",R.drawable.color_mustard_yellow,R.raw.color_mustard_yellow));
        words.add(new Word("yellow","tooolu",R.drawable.color_dusty_yellow,R.raw.color_dusty_yellow));
        words.add(new Word("green","oyyi",R.drawable.color_green,R.raw.color_green));
        words.add(new Word("brown","mama",R.drawable.color_brown,R.raw.color_brown));
        words.add(new Word("gray","loks",R.drawable.color_gray,R.raw.color_gray));
        words.add(new Word("black","kais",R.drawable.color_black,R.raw.color_black));
        words.add(new Word("white","sssde",R.drawable.color_white,R.raw.color_white));


        WordAdapter ad = new WordAdapter(this , R.layout.list_item , words, R.color.category_colors);
        ListView rootView = (ListView) findViewById(R.id.rootView);
        rootView.setAdapter(ad);

        rootView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word = words.get(position);

                releaseMediaPlayer();


                mMediaPlayer = MediaPlayer.create(ColorsActivity.this,word.getmAudioResourceId());
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