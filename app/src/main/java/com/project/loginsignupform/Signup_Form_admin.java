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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup_Form_admin extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText txtUserName, txtEmail ,txtPassword, txtConfirmPassword, txtrollnum;
    Button btn_register;
    ProgressBar progressBar;
    Spinner spinner, spinner2;

    static final String CHAT_PREFS = "ChatPrefs";
    static final String DISPLAY_NAME_KEY = "username";
    //private AutoCompleteTextView mUsernameView;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup__form);
        getSupportActionBar().setTitle("Signup Form");

        txtUserName= (EditText) findViewById(R.id.UserName);
        txtEmail= (EditText) findViewById(R.id.Email);
        txtPassword= (EditText) findViewById(R.id.Password);
        txtConfirmPassword =(EditText) findViewById(R.id.ConPassword);
        txtrollnum = (EditText) findViewById(R.id.Rollnumber);
        btn_register =(Button) findViewById(R.id.Register);
        progressBar =(ProgressBar) findViewById(R.id.progressBar);
        spinner =(Spinner) findViewById(R.id.spinner1);
        spinner2 =(Spinner) findViewById(R.id.spinner2);

        firebaseAuth =FirebaseAuth.getInstance();

        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.Designation,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> adapter2=ArrayAdapter.createFromResource(this,R.array.select_subject,android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(this);


        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email1 =txtEmail.getText().toString().trim();
                String RollNo =txtrollnum.getText().toString().trim();
                String password =txtPassword.getText().toString().trim();
                String confirmPassword =txtConfirmPassword.getText().toString().trim();

                String item= spinner.getSelectedItem().toString();
                String subject= spinner2.getSelectedItem().toString();


                String email = "1";
                switch (item) {
                    case "Admin":
                        email = "A_" + email1;
                        break;
                    case "Student":
                        email = "S_" + email1;
                        break;
                    case "Teachers":
                        email = "T_" + email1;
                        break;
                }

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(Signup_Form_admin.this,"Please Enter Email !", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(Signup_Form_admin.this,"Please Enter Valid Password !", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(confirmPassword)){
                    Toast.makeText(Signup_Form_admin.this,"Please Enter Valid Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(password.length()<6){
                    Toast.makeText(Signup_Form_admin.this,"Password too Short ! ", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(RollNo) && item.equals("Student")){
                    Toast.makeText(Signup_Form_admin.this,"Please Enter Roll number", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(item.equals("Teachers") && subject.equals("Select Subject (if teacher)")){
                    Toast.makeText(Signup_Form_admin.this,"Please Select A subject", Toast.LENGTH_SHORT).show();
                    return;
                }


                progressBar.setVisibility(View.VISIBLE);

                if(password.equals(confirmPassword)){

                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(Signup_Form_admin.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    progressBar.setVisibility(View.GONE);

                                    if (task.isSuccessful()) {

                                        String item1= spinner.getSelectedItem().toString();
                                        String item2= spinner2.getSelectedItem().toString();

                                        String email =txtEmail.getText().toString().trim();

                                        String name =txtUserName.getText().toString().trim();
                                        String Rollno =txtrollnum.getText().toString().trim();
                                        String sub = "00";
                                        switch (item2) {
                                            case "Database management system":
                                                sub = "DB";
                                                break;
                                            case "Object Oriented Analysis and Design":
                                                sub = "OO";
                                                break;
                                            case "Economics":
                                                sub = "EC";
                                                break;
                                            case "Artificial Intelligence":
                                                sub = "AI";
                                                break;
                                            case "Embedded Systems":
                                                sub = "ES";
                                                break;
                                            case "Operating Systems":
                                                sub = "OS";
                                                break;
                                        }
                                        int iend = email.indexOf(".");
                                        String subEmail = "null";
                                        if(iend != -1){
                                            subEmail = email.substring(0,iend);
                                        }
                                        DatabaseReference mDatabase;
                                        if(item1.equals("Student")) {
                                            mDatabase = FirebaseDatabase.getInstance().getReference("Chatnames").child("S_" + subEmail);
                                            mDatabase.child("S_" + subEmail).setValue(Rollno + name);
                                        }
                                        if(item1.equals("Teachers")) {
                                            mDatabase = FirebaseDatabase.getInstance().getReference("Chatnames").child("T_" + subEmail);
                                            mDatabase.child("T_" + subEmail).setValue(sub + "[[["+name+"]]]");
                                        }


                                        startActivity(new Intent(getApplicationContext(),AdminManageAccountsActivity.class));


                                    } else {
                                        Toast.makeText(Signup_Form_admin.this,"Authentication Failed",Toast.LENGTH_SHORT).show();
                                    }


                                }
                            });

                }
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text =parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(),text,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void saveDisplayName() {
        String displayName = txtUserName.getText().toString();
        SharedPreferences prefs = getSharedPreferences(CHAT_PREFS, 0);
        prefs.edit().putString(DISPLAY_NAME_KEY, displayName).apply();
    }

}
