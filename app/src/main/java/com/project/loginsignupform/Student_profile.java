package com.project.loginsignupform;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Student_profile extends AppCompatActivity {
    private Button btn_save;
    EditText Name;
    EditText rollnumber;

    DatabaseReference DatabaseStudents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);

        DatabaseStudents = FirebaseDatabase.getInstance().getReference("listofStds");

        Name = (EditText) findViewById(R.id.Name);
        rollnumber = (EditText) findViewById(R.id.rollno);
        btn_save = (Button) findViewById(R.id.buttonSave);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addStudent();
            }
        });
    }
    public void  addStudent(){
        int flag =0;
        String NameOfStd = Name.getText().toString().trim();
        String Rollnum = rollnumber.getText().toString();
        if(!TextUtils.isEmpty(NameOfStd) && !TextUtils.isEmpty(Rollnum)){

            //String id = DatabaseStudents.push().getKey();
            String id = Rollnum + NameOfStd;

            //StudentIni studentinfo = new StudentIni(id, NameOfStd, Rollnum);

            DatabaseStudents.child(id).setValue(Rollnum + ".  " + NameOfStd);
            flag = 1;

        }
        else {
            Toast.makeText(this,"ENTER BOTH NAME AND ROLL NUMBER",Toast.LENGTH_LONG).show();
        }

        if( flag == 1){
            Intent intent = new Intent(Student_profile.this, Student.class);
            startActivity(intent);}
    }
}
