package com.example.projectcompact;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    Button login, create;
    TextInputEditText tiet1, tiet2;
    ProgressBar pb;
    public static FirebaseAuth fba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login = findViewById(R.id.button);
        create = findViewById(R.id.button2);
        tiet1 = findViewById(R.id.textInputEditText);
        tiet2 = findViewById(R.id.textInputEditText2);
        pb = findViewById(R.id.progressBar);
        fba = FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s1 = tiet1.getText().toString(), s2 = tiet2.getText().toString();
                if(s1.isEmpty())
                {
                    tiet1.setError("fill email");
                    return;
                }
                if(s2.isEmpty())
                {
                    tiet2.setError("fill password");
                    return;
                }
                //processing authentication
                pb.setVisibility(View.VISIBLE);
                fba.signInWithEmailAndPassword(s1,s2).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            pb.setVisibility(View.INVISIBLE);
                            Toast.makeText(MainActivity.this, "logged in", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(MainActivity.this, Third.class);
                            startActivity(i); finish();
                        }
                        else
                        {
                            pb.setVisibility(View.INVISIBLE);
                            Toast.makeText(MainActivity.this, "something went wrong", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Second.class);
                startActivity(i); finish();
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        if(fba.getCurrentUser()!=null && !fba.getCurrentUser().isEmailVerified())
        {
            MainActivity.fba.getCurrentUser().delete();
            Toast.makeText(this, "login requires", Toast.LENGTH_SHORT).show();
        }
        else if(fba.getCurrentUser()!=null && fba.getCurrentUser().isEmailVerified())
        {
            Intent i = new Intent(MainActivity.this, Third.class);
            startActivity(i); finish();
        }
        else
        {
            Toast.makeText(this, "login requires", Toast.LENGTH_SHORT).show();
        }
    }
}