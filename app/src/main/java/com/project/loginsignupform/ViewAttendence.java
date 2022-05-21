package com.project.loginsignupform;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ViewAttendence extends AppCompatActivity {

    private ArrayList<String> mUsername = new ArrayList<>();
    Button btn_DBMS, btn_OS, btn_ES, btn_OOAD, btn_ECO, btn_AI, btn_refresh;
    String sub = "Os";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_attendence);
        getSupportActionBar().setTitle("Attendance");

        btn_DBMS = (Button) findViewById(R.id.DBMS);
        btn_OS = (Button) findViewById(R.id.OS);
        btn_ES = (Button) findViewById(R.id.ES);
        btn_OOAD = (Button) findViewById(R.id.OOAD);
        btn_ECO = (Button) findViewById(R.id.ECO);
        btn_AI = (Button) findViewById(R.id.AI);
        btn_refresh = (Button) findViewById(R.id.Refresh);

        btn_DBMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mUsername.clear();
                sub = "DBMS";
                final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("AttendenceInfo").child(sub);
                final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(ViewAttendence.this, android.R.layout.simple_list_item_1, mUsername);

                final ListView muserlist = (ListView) findViewById(R.id.list_of_attendence);
                muserlist.setAdapter(arrayAdapter);
                mDatabase.addChildEventListener(new ChildEventListener() {
                    int i = 44;
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                        i++;
                        String value = dataSnapshot.getValue(String.class);
                        value = "Roll number:" + i + ". Total Days present :-  " + value;
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



            }
        });
        btn_OS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mUsername.clear();
                sub = "Os";
                final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("AttendenceInfo").child(sub);
                final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(ViewAttendence.this, android.R.layout.simple_list_item_1, mUsername);

                final ListView muserlist = (ListView) findViewById(R.id.list_of_attendence);
                muserlist.setAdapter(arrayAdapter);
                mDatabase.addChildEventListener(new ChildEventListener() {
                    int i = 44;
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                        i++;
                        String value = dataSnapshot.getValue(String.class);
                        value = "Roll number:" + i + ". Total Days present :-  " + value;
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



            }
        });
        btn_ES.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mUsername.clear();
                sub = "ES";
                final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("AttendenceInfo").child(sub);
                final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(ViewAttendence.this, android.R.layout.simple_list_item_1, mUsername);

                final ListView muserlist = (ListView) findViewById(R.id.list_of_attendence);
                muserlist.setAdapter(arrayAdapter);
                mDatabase.addChildEventListener(new ChildEventListener() {
                    int i = 44;
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                        i++;
                        String value = dataSnapshot.getValue(String.class);
                        value = "Roll number:" + i + ". Total Days present :-  " + value;
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


            }
        });
        btn_OOAD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mUsername.clear();
                sub = "OOAD";
                final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("AttendenceInfo").child(sub);
                final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(ViewAttendence.this, android.R.layout.simple_list_item_1, mUsername);

                final ListView muserlist = (ListView) findViewById(R.id.list_of_attendence);
                muserlist.setAdapter(arrayAdapter);
                mDatabase.addChildEventListener(new ChildEventListener() {
                    int i = 44;
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                        i++;
                        String value = dataSnapshot.getValue(String.class);
                        value = "Roll number:" + i + ". Total Days present :-  " + value;
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


            }
        });
        btn_ECO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mUsername.clear();
                sub = "ECO";
                final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("AttendenceInfo").child(sub);
                final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(ViewAttendence.this, android.R.layout.simple_list_item_1, mUsername);

                final ListView muserlist = (ListView) findViewById(R.id.list_of_attendence);
                muserlist.setAdapter(arrayAdapter);
                mDatabase.addChildEventListener(new ChildEventListener() {
                    int i = 44;
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                        i++;
                        String value = dataSnapshot.getValue(String.class);
                        value = "Roll number:" + i + ". Total Days present :-  " + value;
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


            }
        });
        btn_AI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mUsername.clear();
                sub = "AI";
                final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("AttendenceInfo").child(sub);
                final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(ViewAttendence.this, android.R.layout.simple_list_item_1, mUsername);

                final ListView muserlist = (ListView) findViewById(R.id.list_of_attendence);
                muserlist.setAdapter(arrayAdapter);
                mDatabase.addChildEventListener(new ChildEventListener() {
                    int i = 44;
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                        i++;
                        String value = dataSnapshot.getValue(String.class);
                        value = "Roll number:" + i + ". Total Days present :-  " + value;
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


            }
        });
        btn_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mUsername.clear();
                final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("AttendenceInfo").child(sub);
                final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(ViewAttendence.this, android.R.layout.simple_list_item_1, mUsername);
                arrayAdapter.notifyDataSetChanged();
                final ListView muserlist = (ListView) findViewById(R.id.list_of_attendence);
                muserlist.setAdapter(arrayAdapter);
                mUsername.clear();
            }
        });
    }
}
