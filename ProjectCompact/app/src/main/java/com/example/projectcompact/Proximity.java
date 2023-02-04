package com.example.projectcompact;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Proximity extends AppCompatActivity implements SensorEventListener {
    Button back;
    SensorManager sm;
    Sensor s;
    MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proximity);
        back = findViewById(R.id.button17);
        sm = (SensorManager)getSystemService(SENSOR_SERVICE);
        s = sm.getDefaultSensor(Sensor.TYPE_LIGHT);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Proximity.this, Fourth.class);
                startActivity(i); finish();
            }
        });
    }

    void play()
    {
        if(mp==null) mp = MediaPlayer.create(this,R.raw.athrastyle);
        mp.start();
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
        }
    }
    void stop()
    {
        if(mp!=null)
        {
            mp.stop();
            mp.release();
            mp=null;
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(sensorEvent.values[0]>1)
            pause();
        else
            play();
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onPause() {
        super.onPause();
        pause();
        sm.unregisterListener(this);
    }
    @Override
    protected void onResume() {
        super.onResume();
        sm.registerListener(this, s, SensorManager.SENSOR_DELAY_NORMAL);
    }
}