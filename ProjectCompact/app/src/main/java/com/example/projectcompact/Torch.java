package com.example.projectcompact;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class Torch extends AppCompatActivity {
    ImageButton ib;
    Button back;
    CameraManager cm;
    boolean torch_togg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_torch);
        back = findViewById(R.id.button14);
        ib = findViewById(R.id.imageButton8);
        cm = (CameraManager) getSystemService(CAMERA_SERVICE);
        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String str = cm.getCameraIdList()[0];
                    if(!torch_togg)
                    {
                        cm.setTorchMode(str,true);
                        torch_togg = true;
                        ib.setImageResource(R.drawable.on);
                        Toast.makeText(Torch.this, "torch on", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        cm.setTorchMode(str,false);
                        torch_togg = false;
                        ib.setImageResource(R.drawable.off);
                        Toast.makeText(Torch.this, "torch off", Toast.LENGTH_SHORT).show();
                    }
                }
                catch (CameraAccessException cae)
                {
                    Toast.makeText(Torch.this, "something went wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Torch.this, Fourth.class);
                startActivity(i); finish();
            }
        });
    }
}