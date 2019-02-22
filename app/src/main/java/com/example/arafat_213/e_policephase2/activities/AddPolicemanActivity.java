package com.example.arafat_213.e_policephase2.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.arafat_213.e_policephase2.Models.Policeman;
import com.example.arafat_213.e_policephase2.R;
import com.example.arafat_213.e_policephase2.utilities.SpinnerData;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class AddPolicemanActivity extends AppCompatActivity implements View.OnClickListener {

    EditText policemanNameET;
    EditText policemanPhoneET;
    EditText policemanMailET;
    ProgressBar mProgressBar;
    Button addPolicemanBTN;
    Spinner designationSpinner;
    Spinner stationSpinner;
    DatabaseReference mPolicemenRef;
    ArrayList<String> designationList, stationList;
    SpinnerData spinnerData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_policeman);
        init();
        setupSpinners();
        stationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void init() {
        mPolicemenRef = FirebaseDatabase.getInstance().getReference().child("policemen");
        policemanMailET = findViewById(R.id.policemanMailET);
        policemanNameET = findViewById(R.id.policemanNameET);
        policemanPhoneET = findViewById(R.id.policemanphoneET);
        mProgressBar = findViewById(R.id.addPolicemenPB);
        addPolicemanBTN = findViewById(R.id.addPolicemanBTN);
        addPolicemanBTN.setOnClickListener(this);

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
            case R.id.addPolicemanBTN:
                mProgressBar.setVisibility(View.VISIBLE);
                Policeman policeman = new Policeman(0,
                        policemanNameET.getText().toString(),
                        policemanPhoneET.getText().toString(),
                        policemanMailET.getText().toString(),
                        designationSpinner.getSelectedItem().toString()
                        , stationSpinner.getSelectedItem().toString()
                );
                addPoliceman(policeman);
                break;
        }
    }

    public void addPoliceman(Policeman policeman) {
        String key = mPolicemenRef.push().getKey();
        mPolicemenRef.child(key).setValue(policeman)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        mProgressBar.setVisibility(View.INVISIBLE);
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext()
                                    , "Policeman added"
                                    , Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext()
                                    , "Policeman added"
                                    , Toast.LENGTH_SHORT).show();
                        }
            }
                });
    }

    public void setupSpinners() {
        stationSpinner = findViewById(R.id.stationSpinner);
        designationSpinner = findViewById(R.id.designationSpinner);
        spinnerData = new SpinnerData();

        stationList = spinnerData.getStationList();
        ArrayAdapter stationsAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, stationList);
        stationSpinner.setAdapter(stationsAdapter);

        designationList = spinnerData.getDesignationList();
        ArrayAdapter designationAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, designationList);
        designationSpinner.setAdapter(designationAdapter);

    }
}