package com.example.projectcompact;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class Calculator extends AppCompatActivity {

    Button back;
    EditText opd1, opd2;
    TextView res;
    ImageButton add, sub, mul, div;
    TextToSpeech tts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        back = findViewById(R.id.button12);
        opd1 = findViewById(R.id.editText3);
        opd2 = findViewById(R.id.editText4);
        res = findViewById(R.id.textView5);
        add = findViewById(R.id.imageButton2);
        sub = findViewById(R.id.imageButton3);
        mul = findViewById(R.id.imageButton4);
        div = findViewById(R.id.imageButton5);
        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if(i!=TextToSpeech.ERROR) {
                    tts.setLanguage(Locale.ENGLISH);
                    tts.setSpeechRate(0.9f);
                }
            }
        });
        add.setOnClickListener(view -> {
            try {
                String s1 = opd1.getText().toString();
                String s2 = opd2.getText().toString();
                Float f = Float.valueOf(s1) + Float.valueOf(s2);
                String rs = Float.toString(f);
                res.setText(rs);
                tts.speak(rs,TextToSpeech.QUEUE_FLUSH,null,null);
            }catch(Exception e)
            {
                Toast.makeText(Calculator.this, "invalid input", Toast.LENGTH_SHORT).show();
                opd1.setText("");
                opd2.setText("");
            }
        });
        sub.setOnClickListener(view -> {
            try {
                String s1 = opd1.getText().toString();
                String s2 = opd2.getText().toString();
                Float f = Float.valueOf(s1) - Float.valueOf(s2);
                String rs = Float.toString(f);
                res.setText(rs);
                tts.speak(rs,TextToSpeech.QUEUE_FLUSH,null,null);
            }catch(Exception e)
            {
                Toast.makeText(Calculator.this, "invalid input", Toast.LENGTH_SHORT).show();
                opd1.setText("");
                opd2.setText("");
            }
        });
        mul.setOnClickListener(view -> {
            try {
                String s1 = opd1.getText().toString();
                String s2 = opd2.getText().toString();
                Float f = Float.valueOf(s1) * Float.valueOf(s2);
                String rs = Float.toString(f);
                res.setText(rs);
                tts.speak(rs,TextToSpeech.QUEUE_FLUSH,null,null);
            }catch(Exception e)
            {
                Toast.makeText(Calculator.this, "invalid input", Toast.LENGTH_SHORT).show();
                opd1.setText("");
                opd2.setText("");
            }
        });
        div.setOnClickListener(view -> {
            try {
                String s1 = opd1.getText().toString();
                String s2 = opd2.getText().toString();
                Float f = Float.valueOf(s1) / Float.valueOf(s2);
                String rs = Float.toString(f);
                res.setText(rs);
                tts.speak(rs,TextToSpeech.QUEUE_FLUSH,null,null);
            }catch(Exception e)
            {
                Toast.makeText(Calculator.this, "invalid input", Toast.LENGTH_SHORT).show();
                opd1.setText("");
                opd2.setText("");
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Calculator.this, Fourth.class);
                startActivity(i); finish();
            }
        });
    }
}