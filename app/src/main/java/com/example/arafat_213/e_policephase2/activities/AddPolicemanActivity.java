package com.example.arafat_213.e_policephase2.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


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
    boolean isEditAction;
    String mKey;
    String mRating = "0.0";
    String oldImage = "N.A.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_policeman);
        init();

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
        setupSpinner();
        mPolicemenRef = FirebaseDatabase.getInstance().getReference().child("policemen");
        mStorageRef = FirebaseStorage.getInstance().getReference().child("policemen");

        Intent intent = getIntent();
        Policeman policeman = (Policeman) intent.getSerializableExtra("policeman");
        if (policeman != null) {
            isEditAction = true;
            loadPoliceman(policeman);
            mKey = intent.getStringExtra("key");
            fileRef = mStorageRef.child(mKey);
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_arrow_back_black_24dp);
        if (isEditAction) {
            getSupportActionBar().setTitle(getString(R.string.title_activity_edit_policeman));
        } else
            getSupportActionBar().setTitle(getString(R.string.title_activity_add_policeman));
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
                Policeman policeman = new Policeman(
                        oldImage,
                        policemanNameET.getText().toString(),
                        mRating,
                        policemanPhoneET.getText().toString(),
                        policemanMailET.getText().toString(),
                        rankspinner.getSelectedItem().toString(),
                        areaspinner.getSelectedItem().toString()
                );
                uploadImage(policeman);
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

    public void uploadImage(final Policeman policeman) {
        if(mImageUri!= null){
            final StorageReference fileRef = mStorageRef.child(System.currentTimeMillis()
                    +"");//+getFileExtension(mImageUri));
            mUploadTask = fileRef.putFile(mImageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            // var = fileRef.getDownloadUrl().getResult().toString();
                            final Task<Uri> result = taskSnapshot.getMetadata().getReference().getDownloadUrl();
                            result.addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    policeman.setImage_id(uri.toString());
                                    addPoliceman(policeman);
                                }
                            });
                        }
                    });
        }
        else{
            addPoliceman(policeman);
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

    public void loadPoliceman(Policeman policeman) {
        policemanNameET.setText(policeman.getName());
        policemanPhoneET.setText(policeman.getMobile_no());
        policemanMailET.setText(policeman.getEmail());
        rankspinner.setSelection(designationArrayList.indexOf(policeman.getRank()));
        areaspinner.setSelection(policeStationsArrayList.indexOf(policeman.getArea()));
        oldImage = policeman.getImage_id();
        Glide.with(getApplicationContext())
                .load(oldImage)
                .thumbnail(0.25f)
                .circleCrop()
                .into(addPolicemanIV);
        mRating = policeman.getRating();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delete:
                mPolicemenRef.child(mKey).removeValue();
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (isEditAction) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu_policeman, menu);
        }
        return true;
    }

    public void addPoliceman(Policeman policeman) {
        if (!isEditAction)
            mKey = mPolicemenRef.push().getKey();
        mPolicemenRef.child(mKey).setValue(policeman)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            finish();
                            if (isEditAction)
                                Toast.makeText(
                                        getApplicationContext(),
                                        "Policeman updated successfully",
                                        Toast.LENGTH_SHORT
                                ).show();
                            else {
                                // added policeman successfully
                                Toast.makeText(
                                        getApplicationContext(),
                                        "Policeman added successfully",
                                        Toast.LENGTH_SHORT
                                ).show();
                            }
                        } else {
                            Toast.makeText(
                                    getApplicationContext(),
                                    "Failed to add/update policeman",
                                    Toast.LENGTH_SHORT
                            ).show();
                        }
                    }
                });
    }
}