package com.project.loginsignupform;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class time_table_teachers extends AppCompatActivity {
    Button b7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table_teachers);
        getSupportActionBar().setTitle("Time-Table-Teachers");
        b7 = (Button) findViewById(R.id.Back);
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(time_table_teachers.this, WelcomeTeachers.class);
                startActivity(i);
                Toast.makeText(getApplicationContext(),"Welcome Teachers ! ",Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void btn_Back(View view) {

        startActivity(new Intent(getApplicationContext(),WelcomeTeachers.class));

    }
}
