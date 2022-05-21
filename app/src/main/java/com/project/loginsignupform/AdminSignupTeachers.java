package com.project.loginsignupform;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class AdminSignupTeachers extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText txtUserName, txtEmail ,txtPassword, txtConfirmPassword;
    Button btn_register;
    ProgressBar progressBar;


    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_signup_teachers);
        getSupportActionBar().setTitle("Signup Form for teachers");

        txtUserName= (EditText) findViewById(R.id.UserName);
        txtEmail= (EditText) findViewById(R.id.Email);
        txtPassword= (EditText) findViewById(R.id.Password);
        txtConfirmPassword =(EditText) findViewById(R.id.ConPassword);
        btn_register =(Button) findViewById(R.id.Register);
        progressBar =(ProgressBar) findViewById(R.id.progressBar);

        Spinner spinner = findViewById(R.id.spinner_SelectSub);
        ArrayAdapter<CharSequence> adapterSpinner = ArrayAdapter.createFromResource(AdminSignupTeachers.this, R.array.select_subject, android.R.layout.simple_spinner_item);
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapterSpinner);


        firebaseAuth =FirebaseAuth.getInstance();

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email =txtEmail.getText().toString().trim();
                String password =txtPassword.getText().toString().trim();
                String confirmPassword =txtConfirmPassword.getText().toString().trim();

                    email = "T_" + email;


                if(TextUtils.isEmpty(email)){
                    Toast.makeText(AdminSignupTeachers.this,"Please Enter Email !", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(AdminSignupTeachers.this,"Please Enter Valid Password !", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(confirmPassword)){
                    Toast.makeText(AdminSignupTeachers.this,"Please Enter Valid Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(password.length()<6){
                    Toast.makeText(AdminSignupTeachers.this,"Password too Short ! ", Toast.LENGTH_SHORT).show();
                }

                progressBar.setVisibility(View.VISIBLE);

                if(password.equals(confirmPassword)){

                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(AdminSignupTeachers.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    progressBar.setVisibility(View.GONE);

                                    if (task.isSuccessful()) {  startActivity(new Intent(getApplicationContext(),AdminManageAccountsActivity.class));
                                    } else {
                                        Toast.makeText(AdminSignupTeachers.this,"Authentication Failed",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
