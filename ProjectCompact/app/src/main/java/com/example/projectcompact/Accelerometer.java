package com.example.projectcompact;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Accelerometer extends AppCompatActivity implements SensorEventListener {
    TextView x,y,z;
    Button acc,back;
    SensorManager sm;
    Sensor s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer);
        x = findViewById(R.id.textView12);
        y = findViewById(R.id.textView13);
        z = findViewById(R.id.textView14);
        back = findViewById(R.id.button18);
        acc = findViewById(R.id.button19);
        sm = (SensorManager)getSystemService(SENSOR_SERVICE);
        s = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Accelerometer.this, Fourth.class);
                startActivity(i); finish();
            }
        });
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent)
    {
        int xi = (int)sensorEvent.values[0];
        int yi = (int)sensorEvent.values[1];
        int zi = (int)sensorEvent.values[2];
        x.setText("x : "+sensorEvent.values[0]);
        y.setText("y : "+sensorEvent.values[0]);
        z.setText("z : "+sensorEvent.values[0]);
        if(xi==0 && yi==0 && zi==9)
            acc.setBackgroundResource(R.color.acct);
        else
            acc.setBackgroundResource(R.color.accf);
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onPause() {
        super.onPause();
        sm.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sm.registerListener(this, s, SensorManager.SENSOR_DELAY_NORMAL);
    }
}