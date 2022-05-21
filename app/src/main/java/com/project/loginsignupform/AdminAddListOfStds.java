package com.project.loginsignupform;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class AdminAddListOfStds extends AppCompatActivity {
    EditText Name;
    EditText rollnumber;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_list_of_stds);


        Name = (EditText) findViewById(R.id.Name);
        rollnumber = (EditText) findViewById(R.id.rollno);
        Button btn_save = (Button) findViewById(R.id.buttonSave);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int flag =0;
                String NameOfStd = Name.getText().toString().trim();
                String Rollnum = rollnumber.getText().toString().trim();
                if(!TextUtils.isEmpty(NameOfStd) && !TextUtils.isEmpty(Rollnum)){

                    //String id = DatabaseStudents.push().getKey();
                    DatabaseReference DatabaseStudents;
                    String id = Rollnum;
                    DatabaseStudents = FirebaseDatabase.getInstance().getReference("listofStds");
                    //StudentIni studentinfo = new StudentIni(id, NameOfStd, Rollnum);

                    DatabaseStudents.child(id).setValue(Rollnum + "-" + NameOfStd);
                    flag = 1;

                }
                else {
                    Toast.makeText(AdminAddListOfStds.this,"ENTER BOTH NAME AND ROLL NUMBER",Toast.LENGTH_LONG).show();
                }

                if( flag == 1) {
                    Toast.makeText(AdminAddListOfStds.this, "Saved!", Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}
