package com.example.arafat_213.e_policephase2.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.arafat_213.e_policephase2.Models.Notification;
import com.example.arafat_213.e_policephase2.R;
import com.example.arafat_213.e_policephase2.utilities.SpinnerData;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class NewNotificationActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int PICK_IMAGE_REQUEST=1;

    StorageReference mStorageRef;
    DatabaseReference mNotificationRef;
    EditText notifyContentET;
    ProgressBar mProgressBar;
    Button notifyBTN;
    ImageView notificationIV;
    Spinner typeSpinner;
    private ArrayList<String> typeArrayList;

    private Uri mImageUri;
    StorageTask mUploadTask;
    //UploadTask myUploadTask;
    StorageReference fileRef;
    String imageUri = "N.A.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_notification);

        notifyContentET = findViewById(R.id.notifyContentET);
        mProgressBar = findViewById(R.id.newNotificationPB);
        notifyBTN = findViewById(R.id.notifyBTN);
        notifyBTN.setOnClickListener(this);
        notificationIV=findViewById(R.id.notificationIV);
        notificationIV.setOnClickListener(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_arrow_back_black_24dp);

        mStorageRef= FirebaseStorage.getInstance().getReference().child("notifications/");
        mNotificationRef = FirebaseDatabase.getInstance().getReference().child("notifications/");
        fileRef = mStorageRef.child(System.currentTimeMillis()+"");

        setupSpinners();


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
                Notification notification =
                        new Notification(typeSpinner.getSelectedItem().toString(),
                                notifyContentET.getText().toString(),
                                imageUri);
                uploadImage(notification);
                mProgressBar.setVisibility(View.VISIBLE);
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

    public void uploadImage(final Notification notification) {
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
                                    notification.setNotificationImage(uri.toString());
                                    addNotification(notification);
                                }
                            });
                        }
                    });
        }
        else{
            addNotification(notification);
        }

    }

    public void setupSpinners()
    {
        typeSpinner = findViewById(R.id.notifyTypeSpinner);
        SpinnerData spinnerData = new SpinnerData();
        typeArrayList = spinnerData.getNotificationTypesList();
        ArrayAdapter typeAdapter = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,typeArrayList);
        typeSpinner.setAdapter(typeAdapter);
    }

    public void addNotification(Notification notification) {
        String key = mNotificationRef.push().getKey();
        mNotificationRef.child(key).setValue(notification)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(
                                    getApplicationContext(),
                                    "Notification sent successfully",
                                    Toast.LENGTH_SHORT
                            ).show();
                        } else {
                            Toast.makeText(
                                    getApplicationContext(),
                                    "Failed to send Notification",
                                    Toast.LENGTH_SHORT
                            ).show();
                        }
                    }
                });
    }


}

