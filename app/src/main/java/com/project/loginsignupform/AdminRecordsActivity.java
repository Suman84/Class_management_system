package com.project.loginsignupform;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class AdminRecordsActivity extends AppCompatActivity {

    Button btn_viewattendence, btn_resetattendence, btn_editListofstds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_records);

        btn_viewattendence = (Button) findViewById(R.id.button_attendence);
        btn_editListofstds = (Button) findViewById(R.id.button_editListofstds);

        btn_viewattendence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminRecordsActivity.this, ViewAttendence.class);
                startActivity(intent);
            }
        });

        btn_editListofstds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminRecordsActivity.this, AdminAddListOfStds.class);
                startActivity(intent);

            }
        });
    }
}
