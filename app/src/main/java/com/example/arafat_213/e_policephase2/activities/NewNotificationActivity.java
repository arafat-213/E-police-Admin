package com.example.arafat_213.e_policephase2.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.arafat_213.e_policephase2.Models.Notification;
import com.example.arafat_213.e_policephase2.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NewNotificationActivity extends AppCompatActivity implements View.OnClickListener {

    DatabaseReference mNotificationRef;
    EditText notifyContentET;
    Button notifyBTN;
    ProgressBar mProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_notification);
        init();
    }

    public void init() {
        notifyContentET = findViewById(R.id.notifyContentET);
        notifyBTN = findViewById(R.id.notifyBTN);
        notifyBTN.setOnClickListener(this);
        mProgressBar = findViewById(R.id.newNotificationPB);
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
                mProgressBar.setVisibility(View.VISIBLE);
                Notification notification = new Notification("Emergency", notifyContentET.getText().toString(), " ");
                sendNotification(notification);
                break;
        }
    }

    public void sendNotification(Notification notification) {
        String key = mNotificationRef.push().getKey();
        mNotificationRef.child(key).setValue(notification)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        mProgressBar.setVisibility(View.INVISIBLE);
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext()
                                    , "Notification sent"
                                    , Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext()
                                    , "Failed to send notification"
                                    , Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
