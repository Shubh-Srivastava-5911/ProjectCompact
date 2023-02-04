package com.example.projectcompact;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Third extends AppCompatActivity {

    Button submit, logout;
    TextView tv;
    TextInputEditText tiet1,tiet2;
    FirebaseDatabase fbdb;
    DatabaseReference node;
    public static String user_email="", user_name="", user_phone="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        submit = findViewById(R.id.button5);
        logout = findViewById(R.id.button8);
        tv = findViewById(R.id.textView4);
        tiet1 = findViewById(R.id.textInputEditText6);
        tiet2 = findViewById(R.id.textInputEditText7);
        fbdb = FirebaseDatabase.getInstance();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                node = fbdb.getReference("user");
                String s1 = tv.getText().toString(), s2 = tiet1.getText().toString(), s3 = tiet2.getText().toString();
                if(s2.isEmpty())
                {
                    tiet1.setError("fill name");
                    return;
                }
                if(s3.length()!=10)
                {
                    tiet2.setError("fill phone");
                    return;
                }

                User u = new User(s1,s2,s3);

                node.child(MainActivity.fba.getCurrentUser().getUid()).setValue(u); // no SQL database with unique value as email, which defines whole info about user
                // if we use node.setValue(u) we can store only one user, bcs there must be unique key in a reference
                // but by making a parent node (user) and then creating further child nodes under user node with user's email as key and
                // email, name, phone as key-value pairs inside it, can identify every user by its unique identity or key which is email here
                Toast.makeText(Third.this, "successfully submitted", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(Third.this, Fourth.class);
                startActivity(i); finish();
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.fba.signOut();
                Intent i = new Intent(Third.this, MainActivity.class);
                startActivity(i); finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(MainActivity.fba.getCurrentUser() != null)
        {
            user_email = MainActivity.fba.getCurrentUser().getEmail();
            tv.setText(user_email);
            // fetching data from Firebase's Realtime Database
            // UID is safest way to provide as key in database, as it is configured by firebase itself
            // we cannot give email as key bcs firebase key doesn't accept special characters like '@','.'
            FirebaseDatabase.getInstance().getReference("user").child(MainActivity.fba.getCurrentUser().getUid()).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if(task.getResult().exists())
                    {
                        DataSnapshot dbsnap = task.getResult();
                        user_name = String.valueOf(dbsnap.child("name").getValue());
                        user_phone = String.valueOf(dbsnap.child("phone").getValue());
                    }
                    tiet1.setText(user_name);
                    tiet2.setText(user_phone);
                }
            });
        }

    }
}