package com.project.loginsignupform;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminManageAccountsActivity extends AppCompatActivity {

    Button btn_add_teacher;
    Button btn_add_admin;
    Button btn_delete_teacher;
    Button btn_delete_student;
    Button btn_deletethisAcc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_manage_accounts);

        btn_add_teacher = (Button) findViewById(R.id.button_addteacher);
        btn_add_admin = (Button) findViewById(R.id.button_addadmin);
        btn_delete_teacher = (Button) findViewById(R.id.button_deleteteacher);
        btn_delete_student = (Button) findViewById(R.id.button_deletestudent);
        btn_deletethisAcc = (Button) findViewById(R.id.button_deletethisaccount);


        btn_add_teacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AdminManageAccountsActivity.this, AdminSignupTeachers.class);
                startActivity(i);

            }
        });

        btn_add_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AdminManageAccountsActivity.this, AdminSignupAdmin.class);
                startActivity(i);

            }
        });

        btn_delete_teacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btn_delete_student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btn_deletethisAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

}
