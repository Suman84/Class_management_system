package com.project.loginsignupform;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class AdminManageAccountsActivity extends AppCompatActivity {

    Button btn_add_user;
    Button btn_delete_teacher;
    Button btn_delete_student;
    Button btn_deletethisAcc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_manage_accounts);

        btn_add_user= (Button) findViewById(R.id.button_adduser);


        btn_add_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AdminManageAccountsActivity.this, Signup_Form_admin.class);
                startActivity(i);

            }
        });



    }

}
