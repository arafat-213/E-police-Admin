package com.example.arafat_213.e_policephase2.activities;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.arafat_213.e_policephase2.Models.Notification;
import com.example.arafat_213.e_policephase2.R;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.security.PrivateKey;

public class NewNotificationActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int PICK_IMAGE_REQUEST=1;

    StorageReference mStorageRef;
    DatabaseReference mNotificationRef;
    EditText notifyContentET;
    Button notifyBTN;
    ImageView notificationIV;

    private Uri mImageUri, uri;
    StorageTask mUploadTask;
    UploadTask myUploadTask;
    StorageReference fileRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_notification);

        notifyContentET = findViewById(R.id.notifyContentET);
        notifyBTN = findViewById(R.id.notifyBTN);
        notifyBTN.setOnClickListener(this);
        notificationIV=findViewById(R.id.notificationIV);
        notificationIV.setOnClickListener(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_arrow_back_black_24dp);

        mStorageRef= FirebaseStorage.getInstance().getReference().child("notifications/");
        mNotificationRef = FirebaseDatabase.getInstance().getReference().child("notifications/");
        fileRef = mStorageRef.child(System.currentTimeMillis()+"");


    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }


    @Override
    public void onClick(View view) {
        switch(view.getId()){

            case R.id.notificationIV:
                openFileChooser();
                break;
            case R.id.notifyBTN:
                uploadImage();
                break;

        }
    }

    private void openFileChooser()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null){
            mImageUri = data.getData();
            notificationIV.setImageURI(mImageUri);
        }
    }

    private String getFileExtension(Uri uri){
        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(uri));

    }

    public void uploadImage(){
        if(mImageUri!= null){
            final StorageReference fileRef = mStorageRef.child(System.currentTimeMillis()
                    +"");//+getFileExtension(mImageUri));
            mUploadTask = fileRef.putFile(mImageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            // var = fileRef.getDownloadUrl().getResult().toString();
                            Task<Uri> result= taskSnapshot.getMetadata().getReference().getDownloadUrl();
                            result.addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    Notification notificationobj = new Notification("Emergency",notifyContentET.getText().toString(),
                                            uri.toString());
                                    String key = mNotificationRef.push().getKey();
                                    mNotificationRef.child(key).setValue(notificationobj)
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                    Toast.makeText(NewNotificationActivity.this,"Complaint Uploaded Successfully",
                                                            Toast.LENGTH_LONG).show();
                                                    notifyContentET.setText("");
                                                    notificationIV.setImageResource(R.drawable.ic_person_black_24dp);
                                                }
                                            });
                                }
                            });



                        }
                    });
        }
        else{
            String key = mNotificationRef.push().getKey();
            mNotificationRef.child(key).setValue(
                    new Notification("Emergency", notifyContentET.getText().toString(),"NO IMAGE SELECTED")
            ).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    notifyContentET.setText("");
                }
            });
        }

    }




}

