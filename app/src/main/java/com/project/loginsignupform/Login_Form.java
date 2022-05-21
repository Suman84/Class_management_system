package com.project.loginsignupform;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login_Form extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    EditText txtEmail;
    EditText txtPassword;
    Button btn_login;
    Button btn_register;
  // ProgressBar progressBar;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__form);
        getSupportActionBar().setTitle("Login Form");

        txtEmail = (EditText) findViewById(R.id.EmailLog);
        txtPassword = (EditText) findViewById(R.id.PasswordLog);
        btn_login = (Button) findViewById(R.id.Login);
        btn_register = (Button) findViewById(R.id.Register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login_Form.this, Signup_Form.class);
                startActivity(i);
            }
        });

        firebaseAuth = FirebaseAuth.getInstance();
        /*
        FirebaseUser currentUser= firebaseAuth.getCurrentUser();

        if(currentUser != null ){
                startActivity(new Intent(Login_Form.this, WelcomeTeachers.class));
        }
        */

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Designation, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email1 = txtEmail.getText().toString().trim();
                String password = txtPassword.getText().toString().trim();
                String email = "a";
                if (TextUtils.isEmpty(email1)) {
                    Toast.makeText(Login_Form.this, "Please Enter Email !", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(Login_Form.this, "Please Enter Valid Password !", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.length() < 6) {
                    Toast.makeText(Login_Form.this, "Password too Short ! ", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(Login_Form.this, "Login in progress...", Toast.LENGTH_SHORT).show();
                int flag = 0;

                email = "T_" + email1;
                firebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Login_Form.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Intent intent = new Intent(Login_Form.this, WelcomeTeachers.class);
                                    startActivity(intent);
                                } else {
                                    String email1 = txtEmail.getText().toString().trim();
                                    String password = txtPassword.getText().toString().trim();
                                    String email = "S_" + email1;
                                    firebaseAuth.signInWithEmailAndPassword(email, password)
                                            .addOnCompleteListener(Login_Form.this, new OnCompleteListener<AuthResult>() {
                                                @Override
                                                public void onComplete(@NonNull Task<AuthResult> task) {
                                                    if (task.isSuccessful()) {
                                                        Intent intent = new Intent(Login_Form.this, Student.class);
                                                        startActivity(intent);
                                                    } else {
                                                        String email1 = txtEmail.getText().toString().trim();
                                                        String password = txtPassword.getText().toString().trim();
                                                        String email = "A_" + email1;
                                                        firebaseAuth.signInWithEmailAndPassword(email, password)
                                                                .addOnCompleteListener(Login_Form.this, new OnCompleteListener<AuthResult>() {
                                                                    @Override
                                                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                                                        if (task.isSuccessful()) {
                                                                            Intent intent = new Intent(Login_Form.this, Admin.class);
                                                                            startActivity(intent);
                                                                        } else {
                                                                            Toast.makeText(Login_Form.this, "Login Failed or USER NOT REGISTERED !", Toast.LENGTH_SHORT).show();
                                                                        }
                                                                    }
                                                                });


                                                    }
                                                }
                                            });
                                }
                            }
                        });
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