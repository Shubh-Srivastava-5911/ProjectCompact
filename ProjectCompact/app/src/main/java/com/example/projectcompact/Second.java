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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class Second extends AppCompatActivity {

    Button verify, register, back;
    TextInputEditText tiet1, tiet2, tiet3;
    ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        verify = findViewById(R.id.button3);
        register = findViewById(R.id.button20);
        back = findViewById(R.id.button4);
        tiet1 = findViewById(R.id.textInputEditText3);
        tiet2 = findViewById(R.id.textInputEditText4);
        tiet3 = findViewById(R.id.textInputEditText5);
        pb = findViewById(R.id.progressBar2);

        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                String s1 = tiet1.getText().toString(), s2 = tiet2.getText().toString(), s3 = tiet3.getText().toString();
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
                if(s3.isEmpty())
                {
                    tiet3.setError("confirm password");
                    return;
                }
                if(!s2.equals(s3))
                {
                    tiet3.setText("");
                    tiet3.setError("incorrect");
                    return;
                }
                //processing registration
                pb.setVisibility(View.VISIBLE);
                MainActivity.fba.createUserWithEmailAndPassword(s1,s2).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            //an email verification will be sent
                            MainActivity.fba.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(Second.this, "verify your email ...", Toast.LENGTH_SHORT).show();
                                        verify.setVisibility(View.INVISIBLE);
                                        register.setVisibility(View.VISIBLE);
                                        tiet1.setFocusable(false);
                                        tiet2.setFocusable(false);
                                        tiet3.setFocusable(false);
                                    } else {
                                        pb.setVisibility(View.INVISIBLE);
                                        Toast.makeText(Second.this, "invalid email", Toast.LENGTH_SHORT).show();
                                        MainActivity.fba.getCurrentUser().delete();
                                        Intent i = new Intent(Second.this, MainActivity.class);
                                        startActivity(i);
                                        finish();
                                    }
                                }
                            });
                        }
                        else
                        {
                            pb.setVisibility(View.INVISIBLE);
                            Toast.makeText(Second.this, "something went wrong", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                MainActivity.fba.getCurrentUser().reload(); //need to reload after verification
                if(!MainActivity.fba.getCurrentUser().isEmailVerified() && MainActivity.fba.getCurrentUser()!=null) {
                    Toast.makeText(Second.this, "verification pending", Toast.LENGTH_SHORT).show();
                }
                if(MainActivity.fba.getCurrentUser().isEmailVerified() && MainActivity.fba.getCurrentUser()!=null) {
                    Toast.makeText(Second.this, "registered successfully", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Second.this, Third.class);
                    startActivity(i); finish();
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent i = new Intent(Second.this, MainActivity.class);
                startActivity(i); finish();
            }
        });
    }

    @Override
    protected void onDestroy() // if user closes the app without email verification, then his/her email should not be saved to Firebase Auth
    {
        super.onDestroy();
        if(MainActivity.fba.getCurrentUser()!=null && !MainActivity.fba.getCurrentUser().isEmailVerified())
        {
            MainActivity.fba.getCurrentUser().delete();
        }
    }
}