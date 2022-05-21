package com.project.loginsignupform;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Admin extends AppCompatActivity {
    Button btn_manageaccount,btn_studentrecord,btn_adminnotice,btn_logout;

    // firebase
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        getSupportActionBar().setTitle("Admin");

        firebaseAuth= FirebaseAuth.getInstance();

        btn_manageaccount=(Button)findViewById(R.id.manageaccountbutton);
        btn_studentrecord=(Button)findViewById(R.id.studentrecordbutton);
        btn_adminnotice=(Button)findViewById(R.id.adminnoticebutton);
        btn_logout=(Button)findViewById(R.id.logoutbutton);


        btn_manageaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Admin.this, AdminManageAccountsActivity.class);
                startActivity(i);
                Toast.makeText(Admin.this, "Manage account clicked", Toast.LENGTH_SHORT).show();
            }
        });



        btn_studentrecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Admin.this, AdminRecordsActivity.class);
                startActivity(i);
                Toast.makeText(Admin.this, "Record clicked", Toast.LENGTH_SHORT).show();
            }
        });



        btn_adminnotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Admin.this, MainImageActivity.class);
                startActivity(i);
                Toast.makeText(getApplicationContext(),"Welcome to Notices Section ! ",Toast.LENGTH_SHORT).show();

                //Toast.makeText(Admin.this, "Notice clicked", Toast.LENGTH_SHORT).show();
            }
        });


        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(Admin.this,Login_Form.class));
               // Toast.makeText(Admin.this, "Logout clicked", Toast.LENGTH_SHORT).show();
            }
        });


    }
    @Override
    public void onBackPressed() {

    }
}
