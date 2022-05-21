package com.project.loginsignupform;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class doAttendance extends AppCompatActivity {


    private ArrayList<String> mUsername = new ArrayList<>();
    private ArrayList<String> mKey = new ArrayList<>();

    String[] ListValue = new String[] {"","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","",""};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_do_attendance);
        getSupportActionBar().setTitle("Attendance");

        final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("listofStds");

        ListView muserlist = (ListView) findViewById(R.id.user_list);
        ListView mAbsent_rollno_list = (ListView) findViewById(R.id.absent_rollno_list);


        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mUsername);
        muserlist.setAdapter(arrayAdapter);

        final ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ListValue);
        mAbsent_rollno_list.setAdapter(arrayAdapter2);

        Button btn_Save = (Button)findViewById(R.id.buttonSave);

        mDatabase.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String value = dataSnapshot.getValue(String.class);
                mUsername.add(value);
                arrayAdapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        muserlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            ListView mAbsent_rollno_list = (ListView) findViewById(R.id.absent_rollno_list);
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                // TODO Auto-generated method stub
                Toast.makeText(doAttendance.this, position + 45 + " Marked!", Toast.LENGTH_SHORT).show();

                //mDatabase.child("01").setValue("01-samir ");
                for(int i=0; i<20; i++){
                    if(ListValue[i].equals("") || ListValue[i].equals(Integer.toString(45 + position))){

                        if(ListValue[i].equals(Integer.toString(45 + position))){
                            ListValue[i] = "";
                        }else{
                            ListValue[i] = Integer.toString(45 + position);
                            arrayAdapter.notifyDataSetChanged();
                        }
                        break;
                    }
                }
                mAbsent_rollno_list.setAdapter(arrayAdapter2);
            }
        });
        Spinner spinner = findViewById(R.id.spinner_SelectSub);
        ArrayAdapter<CharSequence> adapterSpinner = ArrayAdapter.createFromResource(doAttendance.this, R.array.select_subject, android.R.layout.simple_spinner_item);
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapterSpinner);
        String item = spinner.getSelectedItem().toString();
        String sub = "os";
            btn_Save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int j =0;
                    for (int i2 = 45; i2 <= 61; i2++) {
                            if (ListValue[j].equals(Integer.toString(i2))) {
                                j++;

                            } else {
                                final DatabaseReference mDatabase2 = FirebaseDatabase.getInstance().getReference().child("AttendenceInfo").child("Os").child(String.valueOf(i2));
                                mDatabase2.addValueEventListener(new ValueEventListener() {
                                    int i = -1;

                                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        String data2 = "";
                                        i++;
                                        if (i < 1) {
                                            String data = Objects.requireNonNull(dataSnapshot.getValue()).toString();
                                            int number = Integer.valueOf(data);
                                            number++;
                                            data2 = String.valueOf(number);
                                            mDatabase2.setValue(data2.toString());
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });
                            }
                    }
                    Intent i = new Intent(doAttendance.this, WelcomeTeachers.class);
                    startActivity(i);
                }
            });
    }
}
