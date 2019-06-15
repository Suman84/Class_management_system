package com.project.loginsignupform;

import android.content.Intent;
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

                Toast.makeText(WelcomeTeachers.this, "Profile clicked", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(WelcomeTeachers.this, teachers_profile.class);
                startActivity(i);
            }
        });



        btn_teacherattendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(WelcomeTeachers.this, "Attendance clicked", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(WelcomeTeachers.this, doAttendance.class);
                startActivity(i);
            }
        });



        btn_teacherroutine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(WelcomeTeachers.this, "Routine clicked", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(WelcomeTeachers.this, "Result clicked", Toast.LENGTH_SHORT).show();
            }
        });


        btn_teacherassignment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(WelcomeTeachers.this, "Assignment clicked", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(WelcomeTeachers.this, MainPDFActivity.class);
                startActivity(i);
            }
        });


        btn_teacherchatroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
}
