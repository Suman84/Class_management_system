package com.project.loginsignupform;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class WelcomeTeachers extends AppCompatActivity  {

    Button btn_profile,
            btn_teacherattendance,
            btn_teacherroutine,
            btn_teachernotice,
            btn_teacherresult,
            btn_teacherassignment,
            btn_teacherchatroom,
            btn_logout;

    // firebase
    private FirebaseAuth firebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_teachers);
        getSupportActionBar().setTitle("Welcome Teachers");

        firebaseAuth = FirebaseAuth.getInstance();

        btn_profile=(Button)findViewById(R.id.profilebutton);
        btn_teacherattendance=(Button)findViewById(R.id.teacherattendancebutton);
        btn_teacherroutine=(Button)findViewById(R.id.teacherroutinebutton);
        btn_teachernotice=(Button)findViewById(R.id.teachernoticebutton);
        btn_teacherresult=(Button)findViewById(R.id.teacherresultbutton);
        btn_teacherassignment=(Button)findViewById(R.id.teacherassignmentbutton);
        btn_teacherchatroom=(Button)findViewById(R.id.teacherchatroombutton);
        btn_logout=(Button)findViewById(R.id.logoutbutton);


        btn_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(WelcomeTeachers.this, "View Profile", Toast.LENGTH_SHORT).show();
                String email = getIntent().getStringExtra("email");
                int iend = email.indexOf(".");
                String subEmail = email.substring(0,iend);
                Intent i = new Intent(WelcomeTeachers.this, teachers_profile.class).putExtra("email1", subEmail);
                startActivity(i);
            }
        });



        btn_teacherattendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(WelcomeTeachers.this, "Loading Attendance..", Toast.LENGTH_SHORT).show();
                String email = getIntent().getStringExtra("email");
                int iend = email.indexOf(".");
                String subEmail =  email.substring(0,iend);

                final DatabaseReference mDatabase2 = FirebaseDatabase.getInstance().getReference().child("Chatnames").child(subEmail).child(subEmail);
                mDatabase2.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String data = dataSnapshot.getValue().toString();
                        String sub = data.substring(0,2);
                        switch (sub) {
                            case "AI":
                                data = "AI" ;
                                break;
                            case "OS":
                                data = "Os" ;
                                break;
                            case "EC":
                                data = "ECO" ;
                                break;
                            case "OO":
                                data = "OOAD" ;
                                break;
                            case "DB":
                                data = "DBMS" ;
                                break;
                            case "ES":
                                data = "ES" ;
                                break;
                        }

                        Intent i = new Intent(WelcomeTeachers.this, doAttendance.class).putExtra("subject",data);
                        startActivity(i);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });



        btn_teacherroutine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(WelcomeTeachers.this, "View Attendance", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(WelcomeTeachers.this, ViewAttendence.class);
                startActivity(i);


            }
        });



        btn_teachernotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(WelcomeTeachers.this, MainImageActivity.class);
                startActivity(i);
                Toast.makeText(getApplicationContext(),"Welcome to Notices Section ! ",Toast.LENGTH_SHORT).show();
              //  Toast.makeText(WelcomeTeachers.this, "Notice clicked", Toast.LENGTH_SHORT).show();
            }
        });


        btn_teacherresult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(WelcomeTeachers.this, "Post and view Results", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(WelcomeTeachers.this, MainImageActivityforMarks.class);
                startActivity(i);
            }
        });


        btn_teacherassignment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(WelcomeTeachers.this, "Give or Check Assignments", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(WelcomeTeachers.this, MainPDFActivityPost.class);
                startActivity(i);
            }
        });


        btn_teacherchatroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(WelcomeTeachers.this, "Loading Chatroom.....", Toast.LENGTH_SHORT).show();
                String email = getIntent().getStringExtra("email");
                int iend = email.indexOf(".");
                String subEmail =  email.substring(0,iend);

                final DatabaseReference mDatabase2 = FirebaseDatabase.getInstance().getReference().child("Chatnames").child(subEmail).child(subEmail);
                mDatabase2.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String data = dataSnapshot.getValue().toString();
                        String sub = data.substring(0,2);
                        switch (sub) {
                            case "AI":
                                data = "ArIn" + data;
                                break;
                            case "OS":
                                data = "OpSy" + data;
                                break;
                            case "EC":
                                data = "ECOm" + data;
                                break;
                            case "OO":
                                data = "OOAD" + data;
                                break;
                            case "DB":
                                data = "DBMS" + data;
                                break;
                            case "ES":
                                data = "EmbS" + data;
                                break;
                        }

                        Intent i = new Intent(WelcomeTeachers.this, MainChatActivity.class).putExtra("string",data);
                        startActivity(i);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                Toast.makeText(WelcomeTeachers.this, "Chat room clicked", Toast.LENGTH_SHORT).show();
            }
        });


        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(WelcomeTeachers.this,Login_Form.class));
               // Toast.makeText(WelcomeTeachers.this, "Logout clicked", Toast.LENGTH_SHORT).show();
            }
        });



    }
    @Override
    public void onBackPressed() {

    }
}
