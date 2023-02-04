package com.example.projectcompact;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.Locale;

public class TextSpeech extends AppCompatActivity {

    Button back;
    ImageButton speak;
    EditText et;
    TextToSpeech tts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_speech);
        back = findViewById(R.id.button11);
        speak = findViewById(R.id.imageButton);
        et = findViewById(R.id.editText2);
        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if(i!=TextToSpeech.ERROR) {
                    tts.setLanguage(Locale.ENGLISH);
                    tts.setSpeechRate(0.9f);
                }
            }
        });
        speak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tts.speak(et.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(TextSpeech.this, Fourth.class);
                startActivity(i); finish();
            }
        });
    }
}