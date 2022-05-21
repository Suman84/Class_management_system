package com.project.loginsignupform;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;


public class Student_profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);


        final TextView mName = (TextView) findViewById(R.id.name_view);
        final TextView mRollnum = (TextView) findViewById(R.id.Rollno_View);
        final TextView mEmail = (TextView) findViewById(R.id.email_view);

        Toast.makeText(Student_profile.this,"Loading.....", Toast.LENGTH_SHORT).show();
        String email = getIntent().getStringExtra("email1");

        final DatabaseReference mDatabase2 = FirebaseDatabase.getInstance().getReference().child("Chatnames").child(email).child(email);
        mDatabase2.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String email1 = getIntent().getStringExtra("email1");
                String data = Objects.requireNonNull(dataSnapshot.getValue()).toString();
                String name = "Name: " + data.substring(2);
                String email2 = "Email: " + email1.substring(2) +".com";
                String Rollnum = "Roll number: " + data.substring(0,2);
                mName.setText(name);
                mRollnum.setText(Rollnum);
                mEmail.setText(email2);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
