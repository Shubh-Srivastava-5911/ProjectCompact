package com.example.projectcompact;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class Wifi extends AppCompatActivity {
    ImageButton ib;
    Button back;
    WifiManager wm;
    boolean wifi_togg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi);
        ib = findViewById(R.id.imageButton9);
        back = findViewById(R.id.button15);
        wm = (WifiManager)getApplicationContext().getSystemService(WIFI_SERVICE);
        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!wifi_togg)
                {
                    wm.setWifiEnabled(true);
                    wifi_togg = true;
                    ib.setImageResource(R.drawable.on);
                    Toast.makeText(Wifi.this, "wifi enabled", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    wm.setWifiEnabled(false);
                    wifi_togg = false;
                    ib.setImageResource(R.drawable.off);
                    Toast.makeText(Wifi.this, "wifi disabled", Toast.LENGTH_SHORT).show();
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Wifi.this, Fourth.class);
                startActivity(i); finish();
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        if(wm.isWifiEnabled())
        {
            wifi_togg = true;
            ib.setImageResource(R.drawable.on);
        }
        else
        {
            wifi_togg = false;
            ib.setImageResource(R.drawable.off);
        }
    }
}