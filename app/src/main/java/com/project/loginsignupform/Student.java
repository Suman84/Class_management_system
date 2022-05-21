package com.project.loginsignupform;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

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

                Toast.makeText(Student.this, "Profile clicked", Toast.LENGTH_SHORT).show();
                openStudentProfile();
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
                Intent i = new Intent(Student.this, ViewImageActivity.class);
                startActivity(i);
                Toast.makeText(getApplicationContext(),"Welcome To Notice Section ! ",Toast.LENGTH_SHORT).show();
               // Toast.makeText(Student.this, "Notice clicked", Toast.LENGTH_SHORT).show();
            }
        });


        btn_studentresult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                Intent i = new Intent(Student.this, SelectChatroomActivity.class);
                startActivity(i);
                Toast.makeText(getApplicationContext(),"Welcome To Chat Room ! ",Toast.LENGTH_SHORT).show();

              //  Toast.makeText(Student.this, "Chat room clicked", Toast.LENGTH_SHORT).show();
            }
        });


        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                firebaseAuth.signOut();
                finish();
                Intent intent =new Intent(Student.this,Login_Form.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
               // Toast.makeText(Student.this, "Logout clicked", Toast.LENGTH_SHORT).show();
            }
        });


    }

    public void openStudentProfile() {

        Intent intent = new Intent(Student.this, Student_profile.class);
        startActivity(intent);
    }
}
