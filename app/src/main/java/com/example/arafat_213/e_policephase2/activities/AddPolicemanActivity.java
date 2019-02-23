package com.example.arafat_213.e_policephase2.activities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.arafat_213.e_policephase2.Models.Notification;
import com.example.arafat_213.e_policephase2.Models.Policeman;
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


public class AddPolicemanActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int PICK_IMAGE_REQUEST=1;

    EditText policemanNameET;
    EditText policemanPhoneET;
    EditText policemanMailET;
    ProgressBar mProgressBar;
    Spinner rankspinner,areaspinner;
    Button addPolicemanBTN;
    ImageView addPolicemanIV;
    private ArrayList<String> policeStationsArrayList;
    private ArrayList<String> designationArrayList;
    DatabaseReference mPolicemenRef;
    StorageReference mStorageRef, fileRef;
    private Uri mImageUri;
    StorageTask mUploadTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_policeman);
        init();
        setupSpinner();

    }

    public void init() {
        policemanMailET = findViewById(R.id.policemanMailET);
        policemanNameET = findViewById(R.id.policemanNameET);
        policemanPhoneET = findViewById(R.id.policemanphoneET);
        mProgressBar = findViewById(R.id.addPolicemenPB);
        addPolicemanIV=findViewById(R.id.addPolicemanIV);
        addPolicemanIV.setOnClickListener(this);
        addPolicemanBTN = findViewById(R.id.addPolicemanBTN);
        addPolicemanBTN.setOnClickListener(this);

        mPolicemenRef = FirebaseDatabase.getInstance().getReference().child("policemen");
        mStorageRef = FirebaseStorage.getInstance().getReference().child("policemen");
        fileRef = mStorageRef.child(System.currentTimeMillis()+"");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_arrow_back_black_24dp);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.addPolicemanIV:
                openFileChooser();

                break;
            case R.id.addPolicemanBTN:
                mProgressBar.setVisibility(View.VISIBLE);
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
            addPolicemanIV.setImageURI(mImageUri);
        }
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
                                    Policeman policemanobj =new Policeman(uri.toString(),
                        policemanNameET.getText().toString(),
                        policemanPhoneET.getText().toString(),
                        policemanMailET.getText().toString(),
                        rankspinner.getSelectedItem().toString(), areaspinner.getSelectedItem().toString()
                );
                                    String key = mPolicemenRef.push().getKey();
                                    mPolicemenRef.child(key).setValue(policemanobj)
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                    mProgressBar.setVisibility(View.INVISIBLE);
                                                    Toast.makeText(AddPolicemanActivity.this,"Policeman added Successfully",
                                                            Toast.LENGTH_LONG).show();
                                                    policemanNameET.setText("");
                                                    policemanPhoneET.setText("");
                                                    policemanMailET.setText("");
                                                    addPolicemanIV.setImageResource(R.drawable.ic_person_black_24dp);
                                                }
                                            });
                                }
                            });



                        }
                    });
        }
        else{
            String key = mPolicemenRef.push().getKey();
            mPolicemenRef.child(key).setValue(
                    new Policeman("NO IMAGE SELECTED",
                            policemanNameET.getText().toString(),
                            policemanPhoneET.getText().toString(),
                            policemanMailET.getText().toString(),
                            rankspinner.getSelectedItem().toString(),areaspinner.getSelectedItem().toString()
                    )
            ).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    policemanNameET.setText("");
                    policemanPhoneET.setText("");
                    policemanMailET.setText("");
                    addPolicemanIV.setImageResource(R.drawable.ic_person_black_24dp);
                }
            });
        }

    }

    public void setupSpinner(){

        rankspinner=findViewById(R.id.designationSpinner);
        areaspinner=findViewById(R.id.stationSpinner);
        SpinnerData spinnerData = new SpinnerData();
        policeStationsArrayList = spinnerData.getStationList();
        designationArrayList = spinnerData.getDesignationList();
        ArrayAdapter policeStationsAdapter = new ArrayAdapter(this,
                R.layout.support_simple_spinner_dropdown_item,policeStationsArrayList);
        ArrayAdapter designationAdapter = new ArrayAdapter(this,
                R.layout.support_simple_spinner_dropdown_item,designationArrayList);
        rankspinner.setAdapter(designationAdapter);
        areaspinner.setAdapter(policeStationsAdapter);

    }

}