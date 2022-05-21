package com.project.loginsignupform;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Signup_Form_student extends AppCompatActivity  {

EditText txtUserName, txtEmail ,txtPassword, txtConfirmPassword, txtRollnum;
Button btn_register;

private FirebaseAuth firebaseAuth;

@Override
    protected void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_signup_form_student);
    getSupportActionBar().setTitle("Signup Form Student");

    txtUserName= (EditText) findViewById(R.id.UserName);
    txtEmail= (EditText) findViewById(R.id.Email);
    txtPassword= (EditText) findViewById(R.id.Password);
    txtConfirmPassword =(EditText) findViewById(R.id.ConPassword);
    txtRollnum =(EditText) findViewById(R.id.Rollnumber);
    btn_register =(Button) findViewById(R.id.register);

    firebaseAuth =FirebaseAuth.getInstance();

    final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("Users");

    btn_register.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String email =txtEmail.getText().toString().trim();
            String password =txtPassword.getText().toString().trim();
            String confirmPassword =txtConfirmPassword.getText().toString().trim();
            String name =txtUserName.getText().toString().trim();
            String Rollno =txtRollnum.getText().toString().trim();

                email = "S_" + email;

            if(TextUtils.isEmpty(email)){
                Toast.makeText(Signup_Form_student.this,"Please Enter Email !", Toast.LENGTH_SHORT).show();
                return;
            }
            if(TextUtils.isEmpty(password)){
                Toast.makeText(Signup_Form_student.this,"Please Enter Valid Password !", Toast.LENGTH_SHORT).show();
                return;
            }
            if(TextUtils.isEmpty(confirmPassword)){
                Toast.makeText(Signup_Form_student.this,"Please Enter Valid Password", Toast.LENGTH_SHORT).show();
                return;
            }

            if(password.length()<6){
                Toast.makeText(Signup_Form_student.this,"Password too Short ! ", Toast.LENGTH_SHORT).show();
                return;
            }


            if(password.equals(confirmPassword)){
                Toast.makeText(Signup_Form_student.this,"Registering....",Toast.LENGTH_SHORT).show();
                firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Signup_Form_student.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {

                                    String email =txtEmail.getText().toString().trim();
                                    email = "S_" + email;
                                    String name =txtUserName.getText().toString().trim();
                                    String Rollno =txtRollnum.getText().toString().trim();
                                    int iend = email.indexOf(".");
                                    String subEmail = "null";
                                    if(iend != -1){
                                        subEmail = email.substring(0,iend);
                                    }
                                    DatabaseReference mDatabase;
                                    mDatabase = FirebaseDatabase.getInstance().getReference("Chatnames").child(subEmail);
                                    mDatabase.child(subEmail).setValue(Rollno + name);

                                    Intent i = new Intent(Signup_Form_student.this, Login_Form.class);
                                    startActivity(i);
                                } else {
                                    Toast.makeText(Signup_Form_student.this,"Authentication Failed",Toast.LENGTH_SHORT).show();
                                }

                            }
                        });

            }else{
                Toast.makeText(Signup_Form_student.this,"Password do not match!",Toast.LENGTH_SHORT).show();
            }


        }
    });
    }


}
