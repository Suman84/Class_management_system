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
import android.widget.AutoCompleteTextView;
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

public class Admin_signup_Form extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText txtUserName, txtEmail ,txtPassword, txtConfirmPassword, txtRollnum;
    Button btn_register;
    ProgressBar progressBar;
    Spinner spinner1, spinner2;

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
        txtRollnum =(EditText) findViewById(R.id.Rollnumber);

        btn_register =(Button) findViewById(R.id.Register);
        progressBar =(ProgressBar) findViewById(R.id.progressBar);
        spinner1 =(Spinner) findViewById(R.id.spinner1);
        spinner2 =(Spinner) findViewById(R.id.spinner2);



        firebaseAuth =FirebaseAuth.getInstance();

        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.Designation,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);
        spinner1.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> adapter2=ArrayAdapter.createFromResource(this,R.array.select_subject,android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(this);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email1 =txtEmail.getText().toString().trim();
                String password =txtPassword.getText().toString().trim();
                String confirmPassword =txtConfirmPassword.getText().toString().trim();

                String item= spinner1.getSelectedItem().toString();
                String email = "1";
                if(item.equals("Admin"))
                {
                    email = "A_" + email1;

                }
                else if(item.equals("Student")){
                    email = "S_" + email1;

                }
                else if(item.equals("Teachers")){
                    email = "T_" + email1;

                }
                else if(item.equals("Parents")){
                    email = "P_" + email1;

                }

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(Admin_signup_Form.this,"Please Enter Email !", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(Admin_signup_Form.this,"Please Enter Valid Password !", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(confirmPassword)){
                    Toast.makeText(Admin_signup_Form.this,"Please Enter Valid Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(password.length()<6){
                    Toast.makeText(Admin_signup_Form.this,"Password too Short ! ", Toast.LENGTH_SHORT).show();
                }

                progressBar.setVisibility(View.VISIBLE);

                if(password.equals(confirmPassword)){

                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(Admin_signup_Form.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    progressBar.setVisibility(View.GONE);

                                    if (task.isSuccessful()) {

                                        String item1= spinner1.getSelectedItem().toString();
                                        String item2= spinner2.getSelectedItem().toString();

                                        String email =txtEmail.getText().toString().trim();
                                        email = "S_" + email;
                                        String name =txtUserName.getText().toString().trim();
                                        String Rollno =txtRollnum.getText().toString().trim();

                                        String sub = "OS";

                                        int iend = email.indexOf(".");
                                        String subEmail = "null";
                                        if(iend != -1){
                                            subEmail = email.substring(0,iend);
                                        }
                                        DatabaseReference mDatabase;
                                        mDatabase = FirebaseDatabase.getInstance().getReference("Chatnames").child(subEmail);
                                        if(item1.equals("Student")) {
                                            mDatabase.child(subEmail).setValue(Rollno + name);
                                        }
                                        if(item1.equals("Teachers")) {
                                            mDatabase.child(subEmail).setValue(sub + name);
                                        }







                                        startActivity(new Intent(getApplicationContext(),AdminManageAccountsActivity.class));
                                    } else {
                                        Toast.makeText(Admin_signup_Form.this,"Authentication Failed",Toast.LENGTH_SHORT).show();
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
