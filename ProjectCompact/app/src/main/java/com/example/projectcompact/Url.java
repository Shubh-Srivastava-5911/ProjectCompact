package com.example.projectcompact;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.net.URL;

public class Url extends AppCompatActivity {

    Button back, go;
    ImageButton google, gmail, youtube, twitter, facebook, instagram;
    EditText userInputUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_url);

        back = findViewById(R.id.button20);
        go = findViewById(R.id.button21);
        google = findViewById(R.id.imageButton11);
        gmail = findViewById(R.id.imageButton15);
        youtube = findViewById(R.id.imageButton12);
        twitter = findViewById(R.id.imageButton13);
        facebook = findViewById(R.id.imageButton16);
        instagram = findViewById(R.id.imageButton14);
        userInputUrl = findViewById(R.id.editText5);

        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://www.google.com/");
            }
        });
        gmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://www.mail.google.com/");
            }
        });
        youtube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://www.youtube.com/");
            }
        });
        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://www.twitter.com/");
            }
        });
        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://www.facebook.com/");
            }
        });
        instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://www.instagram.com/");
            }
        });

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = userInputUrl.getText().toString();
                try{
                    gotoUrl(url);
                }
                catch(Exception e)
                {
                    Toast.makeText(Url.this, "invalid url", Toast.LENGTH_SHORT).show();
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Url.this, Fourth.class);
                startActivity(i); finish();
            }
        });
    }
    private void gotoUrl(String url)
    {
        Uri uri = Uri.parse(url);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }
}