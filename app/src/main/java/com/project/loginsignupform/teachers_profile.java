package com.project.loginsignupform;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class teachers_profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teachers_profile);


        final TextView mName = (TextView) findViewById(R.id.name_view);
        final TextView mRollnum = (TextView) findViewById(R.id.Rollno_View);
        final TextView mEmail = (TextView) findViewById(R.id.email_view);

        Toast.makeText(teachers_profile.this,"Loading.....", Toast.LENGTH_SHORT).show();
        String email = getIntent().getStringExtra("email1");

        final DatabaseReference mDatabase2 = FirebaseDatabase.getInstance().getReference().child("Chatnames").child(email).child(email);
        mDatabase2.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String email1 = getIntent().getStringExtra("email1");
                String data = Objects.requireNonNull(dataSnapshot.getValue()).toString();
                int iend = data.indexOf("]");
                String name = "Name: " + data.substring(5,iend);
                String Rollnum ="";
                String email2 = "Email: " + email1.substring(2) +".com";
                mName.setText(name);
                mEmail.setText(email2);

                switch (email1.substring(2)) {
                    case "OO":
                        Rollnum = "Subject: OOAD";
                        mRollnum.setText(Rollnum);
                        break;
                    case "DB":
                        Rollnum = "Subject: DBMS";
                        mRollnum.setText(Rollnum);
                        break;
                    case "EC":
                        Rollnum = "Subject: Economics";
                        mRollnum.setText(Rollnum);
                        break;
                    default:
                        Rollnum = "Subject: " + data.substring(0,2);
                        mRollnum.setText(Rollnum);
                        break;
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}
