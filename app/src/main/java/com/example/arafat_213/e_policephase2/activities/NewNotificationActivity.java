package com.example.arafat_213.e_policephase2.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.arafat_213.e_policephase2.Models.Notification;
import com.example.arafat_213.e_policephase2.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NewNotificationActivity extends AppCompatActivity implements View.OnClickListener {

    DatabaseReference mNotificationRef;
    EditText notifyContentET;
    Button notifyBTN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_notification);

        notifyContentET = findViewById(R.id.notifyContentET);
        notifyBTN = findViewById(R.id.notifyBTN);
        notifyBTN.setOnClickListener(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_arrow_back_black_24dp);

        mNotificationRef = FirebaseDatabase.getInstance().getReference().child("notifications");

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.notifyBTN:
                String key = mNotificationRef.push().getKey();
                mNotificationRef.child(key).setValue(
                        new Notification("Emergency", notifyContentET.getText().toString(), " ")
                ).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        notifyContentET.setText("");
                    }
                });
                break;
        }
    }
}
