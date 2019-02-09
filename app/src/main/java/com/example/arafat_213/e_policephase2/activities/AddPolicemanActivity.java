package com.example.arafat_213.e_policephase2.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.arafat_213.e_policephase2.Models.Policeman;
import com.example.arafat_213.e_policephase2.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



public class AddPolicemanActivity extends AppCompatActivity implements View.OnClickListener {

    EditText policemanNameET;
    EditText policemanPhoneET;
    EditText policemanMailET;
    Button addPolicemanBTN;
    DatabaseReference mPolicemenRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_policeman);

        mPolicemenRef = FirebaseDatabase.getInstance().getReference().child("policemen");
        policemanMailET=findViewById(R.id.policemanMailET);
        policemanNameET=findViewById(R.id.policemanNameET);
        policemanPhoneET=findViewById(R.id.policemanphoneET);
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
        switch (view.getId()){
            case R.id.addPolicemanBTN:
                String key = mPolicemenRef.push().getKey();
                mPolicemenRef.child(key).setValue(new Policeman(0,
                        policemanNameET.getText().toString(),
                        policemanPhoneET.getText().toString(),
                        policemanMailET.getText().toString(),
                        "ACP", "AREA"
                        )).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        policemanMailET.setText("");
                        policemanPhoneET.setText("");
                        policemanNameET.setText("");

                    }
                });
            }
        }
    }

