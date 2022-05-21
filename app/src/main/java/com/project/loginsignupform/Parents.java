package com.project.loginsignupform;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Parents extends AppCompatActivity {
    Button btn_profile,btn_studentrecord,btn_parentchatroom,btn_logout;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parents);
        getSupportActionBar().setTitle("Teacher");

        btn_profile=(Button)findViewById(R.id.profilebutton);
        btn_studentrecord=(Button)findViewById(R.id.studentrecordbutton);
        btn_parentchatroom=(Button)findViewById(R.id.parentchatroombutton);
        btn_logout=(Button)findViewById(R.id.logoutbutton);

    firebaseAuth =FirebaseAuth.getInstance();

        btn_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(Parents.this, "Profile clicked", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(Parents.this, parents_profile.class);
                startActivity(i);
            }
        });



        btn_studentrecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Parents.this, "Record clicked", Toast.LENGTH_SHORT).show();

            }
        });



        btn_parentchatroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Parents.this, "Chat room clicked", Toast.LENGTH_SHORT).show();
            }
        });


        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                firebaseAuth.signOut();
                finish();
                Intent intent =new Intent(Parents.this,Login_Form.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
               // Toast.makeText(Parents.this, "Logout clicked", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
