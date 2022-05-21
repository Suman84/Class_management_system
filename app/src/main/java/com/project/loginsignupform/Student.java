package com.project.loginsignupform;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class Student extends AppCompatActivity {
    Button btn_studentprofile,btn_studentattendance,btn_studentroutine,btn_studentnotice,
            btn_studentresult,btn_studentassignment,btn_studentchatroom,btn_logout;
private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        firebaseAuth= FirebaseAuth.getInstance();

        btn_studentprofile=(Button)findViewById(R.id.profilebutton);
        btn_studentattendance=(Button)findViewById(R.id.studentattendancebutton);
        btn_studentroutine=(Button)findViewById(R.id.studentroutinebutton);
        btn_studentnotice=(Button)findViewById(R.id.studentnoticebutton);
        btn_studentresult=(Button)findViewById(R.id.studentresultbutton);
        btn_studentassignment=(Button)findViewById(R.id.studentassignmentbutton);
        btn_studentchatroom=(Button)findViewById(R.id.studentchatroombutton);
        btn_logout=(Button)findViewById(R.id.logoutbutton);


        btn_studentprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = getIntent().getStringExtra("email");
                int iend = email.indexOf(".");
                String subEmail = email.substring(0,iend);

                Toast.makeText(Student.this, "Profile clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Student.this, Student_profile.class).putExtra("email1", subEmail);
                startActivity(intent);
            }
        });

        btn_studentattendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Student.this, "Attendance clicked", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(Student.this, ViewAttendence.class);
                startActivity(i);
            }
        });



        btn_studentroutine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Student.this, "Routine clicked", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(Student.this, RoutineStudents.class);
                startActivity(i);
            }
        });



        btn_studentnotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Student.this, ViewImageActivity.class).putExtra("string","uploads_notice");
                startActivity(i);
                Toast.makeText(getApplicationContext(),"Welcome To Notice Section ! ",Toast.LENGTH_SHORT).show();
               // Toast.makeText(Student.this, "Notice clicked", Toast.LENGTH_SHORT).show();
            }
        });


        btn_studentresult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Student.this, ViewImageActivity.class).putExtra("string","uploads_marks");
                startActivity(i);
                Toast.makeText(Student.this, "Result clicked", Toast.LENGTH_SHORT).show();


            }
        });


        btn_studentassignment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Student.this, MainPDFActivity.class);
                startActivity(i);
               // Toast.makeText(getApplicationContext(),"Post or View Assignments ! ",Toast.LENGTH_SHORT).show();
               // Toast.makeText(Student.this, "Assignment clicked", Toast.LENGTH_SHORT).show();
            }
        });


        btn_studentchatroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = getIntent().getStringExtra("email");
                int iend = email.indexOf(".");
                String subEmail =  email.substring(0,iend);

               final DatabaseReference mDatabase2 = FirebaseDatabase.getInstance().getReference().child("Chatnames").child(subEmail).child(subEmail);
                                mDatabase2.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        String data = dataSnapshot.getValue().toString();
                                        Intent i = new Intent(Student.this, SelectChatroomActivity.class).putExtra("Dname",data);
                                        startActivity(i);

                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });




            }
        });


        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(Student.this,Login_Form.class));
               // Toast.makeText(Student.this, "Logout clicked", Toast.LENGTH_SHORT).show();
            }
        });




    }
    @Override
    public void onBackPressed() {

    }

}
