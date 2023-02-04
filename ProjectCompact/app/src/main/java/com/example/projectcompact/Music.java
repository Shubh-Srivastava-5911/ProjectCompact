package com.example.projectcompact;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Music extends AppCompatActivity {

    Button back;
    ImageButton play_pause, stop;
    MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        back = findViewById(R.id.button13);
        play_pause = findViewById(R.id.imageButton6);
        stop = findViewById(R.id.imageButton7);
        play_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mp!=null)
                    if(mp.isPlaying())
                        pause();
                    else
                        play();
                else
                    play();
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stop();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Music.this, Fourth.class);
                startActivity(i); finish();
            }
        });
    }
    void play()
    {
        if(mp==null) mp = MediaPlayer.create(this,R.raw.barood);
        mp.start();
        play_pause.setImageResource(R.drawable.pause1);
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                stop();
            }
        });
    }
    void pause()
    {
        if(mp!=null)
        {
            mp.pause();
            play_pause.setImageResource(R.drawable.play1);
        }
    }
    void stop()
    {
        if(mp!=null)
        {
            mp.stop();
            mp.release();
            mp=null;
            play_pause.setImageResource(R.drawable.play1);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        pause();
    }
}