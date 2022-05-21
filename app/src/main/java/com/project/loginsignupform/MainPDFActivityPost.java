package com.project.loginsignupform;


import android.support.v7.app.AppCompatActivity;
import android.support.annotation.Nullable;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class MainPDFActivityPost extends AppCompatActivity {

    EditText editPDFName;
    Button btn_upload;
    Button btn_attach_pdf;
    Button btn_viewSubmittedAssignments;
    StorageReference storageReference;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_pdfpost);
        getSupportActionBar().setTitle("Assignment");

        editPDFName = (EditText) findViewById(R.id.txt_pdfName);
        btn_upload = (Button) findViewById(R.id.btn_upload);
        btn_attach_pdf = (Button) findViewById(R.id.button_attach_pdf);
        btn_viewSubmittedAssignments = (Button) findViewById(R.id.btn_viewPosted_assessments);

        storageReference = FirebaseStorage.getInstance().getReference();
        databaseReference = FirebaseDatabase.getInstance().getReference("pdfuploads");

        btn_attach_pdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = String.valueOf(editPDFName);
                if(editPDFName.getText().toString().equals("") ){
                    Toast.makeText(MainPDFActivityPost.this, "Write a name first", Toast.LENGTH_SHORT).show();
                }else{
                    selectPDFFILE();
                }
            }
        });

        btn_viewSubmittedAssignments.setOnClickListener(new View.OnClickListener(){
             @Override
                public void onClick(View view){
                 Intent i = new Intent(MainPDFActivityPost.this, View_PDF_files_Submitted_assignments.class);
                 startActivity(i);
             }
        });
        btn_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = String.valueOf(editPDFName);
                if(editPDFName.getText().toString().equals("") ){
                    Toast.makeText(MainPDFActivityPost.this, "Write a name first", Toast.LENGTH_SHORT).show();
                }else{
                    uploadTEXTonly();
                }
            }
        });
    }

    private void selectPDFFILE(){

        Intent intent=new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select PDF File"),1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==1 && resultCode==RESULT_OK  && data !=null && data.getData() !=null){
            uploadPDFFILE(data.getData());
        }

    }


    private void uploadPDFFILE(Uri data){

        final ProgressDialog progressDialog= new ProgressDialog(this);
        progressDialog.setTitle("uploading.......");
        progressDialog.show();

        StorageReference reference=storageReference.child("upload/"+System.currentTimeMillis()+".pdf");
        reference.putFile(data)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        Task<Uri> uri =taskSnapshot.getStorage().getDownloadUrl();
                        while(!uri.isComplete());
                        Uri url = uri.getResult();

                        uploadPDF uploadPDF = new uploadPDF(editPDFName.getText().toString(),url.toString());
                        databaseReference.child(databaseReference.push().getKey()).setValue(uploadPDF);
                        Toast.makeText(MainPDFActivityPost.this, "Assignment Posted", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                double progress=(100.0*taskSnapshot.getBytesTransferred())/taskSnapshot.getTotalByteCount();

                progressDialog.setMessage("Uploaded:"+(int)progress+"%");



            }
        });

    }
    private void uploadTEXTonly(){

        uploadPDF uploadPDF = new uploadPDF("* " + editPDFName.getText().toString(),"--NULL--");
        databaseReference.child(databaseReference.push().getKey()).setValue(uploadPDF);
        Toast.makeText(MainPDFActivityPost.this, "Assignment Posted", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(MainPDFActivityPost.this,View_PDF_Files.class);
        startActivity(i);

    }


    public void btn_action(View view) {
        startActivity(new Intent(getApplicationContext(),View_PDF_Files.class));
    }
}
