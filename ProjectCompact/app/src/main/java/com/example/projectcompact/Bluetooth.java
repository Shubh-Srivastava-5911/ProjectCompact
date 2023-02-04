package com.example.projectcompact;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class Bluetooth extends AppCompatActivity {
    ImageButton ib;
    Button back;
    BluetoothAdapter ba;
    boolean bluetooth_togg;
    Integer REQUEST_ENABLE_BT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);
        ib = findViewById(R.id.imageButton10);
        back = findViewById(R.id.button16);
        ba = BluetoothAdapter.getDefaultAdapter();

        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!bluetooth_togg) {
                    Intent i = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    if (ActivityCompat.checkSelfPermission(Bluetooth.this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    startActivityForResult(i, REQUEST_ENABLE_BT);
                    bluetooth_togg = true;
                    ib.setImageResource(R.drawable.on);
                }
                else
                {
                    ba.disable();
                    bluetooth_togg = false;
                    ib.setImageResource(R.drawable.off);
                    Toast.makeText(Bluetooth.this, "bluetooth disabled", Toast.LENGTH_SHORT).show();
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Bluetooth.this, Fourth.class);
                startActivity(i); finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_ENABLE_BT)
            if(resultCode==RESULT_OK)
                Toast.makeText(this, "bluetooth enabled", Toast.LENGTH_SHORT).show();
            else if(resultCode==RESULT_CANCELED)
                Toast.makeText(this, "something went wrong", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(ba.isEnabled())
        {
            bluetooth_togg = true;
            ib.setImageResource(R.drawable.on);
        }
        else
        {
            bluetooth_togg = false;
            ib.setImageResource(R.drawable.off);
        }
    }
}