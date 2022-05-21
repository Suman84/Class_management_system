package com.project.loginsignupform;


import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.telecom.TelecomManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class View_PDF_files_Submitted_assignments extends AppCompatActivity {

    ListView myPDFListView;
    DatabaseReference databaseReference;
    List<uploadPDF> uploadPDFS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__pdf__files);
        getSupportActionBar().setTitle("Assignment");


        myPDFListView = (ListView)findViewById(R.id.myListView);
        uploadPDFS= new ArrayList<>();


        viewAllFiles();

        myPDFListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                uploadPDF uploadPDF = uploadPDFS.get(position);

                String selectedItem = (String) adapterView.getItemAtPosition(position);
                String c = selectedItem.charAt(0) + " ";


                if(!c.equals("* ")){
                    Intent intent= new Intent();
                    intent.setType(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(uploadPDF.getUrl()));
                    startActivity(intent);
                }else{
                    Toast.makeText(View_PDF_files_Submitted_assignments.this,"No PDF file attached",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void viewAllFiles(){

        databaseReference = FirebaseDatabase.getInstance().getReference("pdfuploads_submitted");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()){

                    uploadPDF uploadPDF = postSnapshot.getValue(com.project.loginsignupform.uploadPDF.class);
                    uploadPDFS.add(uploadPDF);
                }


                String[] uploads = new String[uploadPDFS.size()];

                for (int i=0;i<uploads.length;i++){

                    uploads[i]= uploadPDFS.get(i).getName();

                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,uploads)
                {

                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {

                        View view = super.getView(position, convertView, parent);

                        TextView myText=(TextView) view.findViewById(android.R.id.text1);
                        myText.setTextColor(Color.BLACK);



                        return view;
                    }
                };

                myPDFListView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }



}

