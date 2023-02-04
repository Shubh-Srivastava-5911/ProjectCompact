package com.example.projectcompact;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Fourth extends AppCompatActivity {

    Button logout, about;
    Button toast_input, text_to_speech, calculator, music, torch, wifi, bluetooth, proximity, accelerometer, url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);

        logout = findViewById(R.id.button6);
        about = findViewById(R.id.button7);
        toast_input = findViewById(R.id.materialButton);
        text_to_speech = findViewById(R.id.materialButton2);
        calculator = findViewById(R.id.materialButton3);
        music = findViewById(R.id.materialButton4);
        torch = findViewById(R.id.materialButton5);
        wifi = findViewById(R.id.materialButton6);
//        bluetooth = findViewById(R.id.materialButton7);
        proximity = findViewById(R.id.materialButton8);
        accelerometer = findViewById(R.id.materialButton9);
        url = findViewById(R.id.materialButton10);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.fba.signOut();
                Intent i = new Intent(Fourth.this, MainActivity.class);
                startActivity(i); finish();
            }
        });
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Fourth.this, Third.class);
                startActivity(i); finish();
            }
        });

        // app functional work intents
        toast_input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Fourth.this, ToastInput.class);
                startActivity(i); finish();
            }
        });
        text_to_speech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Fourth.this, TextSpeech.class);
                startActivity(i); finish();
            }
        });
        calculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Fourth.this, Calculator.class);
                startActivity(i); finish();
            }
        });
        music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Fourth.this, Music.class);
                startActivity(i); finish();
            }
        });
        torch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Fourth.this, Torch.class);
                startActivity(i); finish();
            }
        });
        wifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Fourth.this, Wifi.class);
                startActivity(i); finish();
            }
        });
//        bluetooth.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(Fourth.this, Bluetooth.class);
//                startActivity(i); finish();
//            }
//        });
        proximity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Fourth.this, Proximity.class);
                startActivity(i); finish();
            }
        });
        accelerometer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Fourth.this, Accelerometer.class);
                startActivity(i); finish();
            }
        });
        url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Fourth.this, Url.class);
                startActivity(i); finish();
            }
        });
    }
}