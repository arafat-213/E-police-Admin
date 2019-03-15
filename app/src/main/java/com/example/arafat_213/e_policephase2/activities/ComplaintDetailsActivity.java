package com.example.arafat_213.e_policephase2.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.arafat_213.e_policephase2.Models.Complaint;
import com.example.arafat_213.e_policephase2.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class ComplaintDetailsActivity extends AppCompatActivity {

    private static final String TAG = "ComplaintDetails";
    ImageView complaintIV;
    TextView complaintSenderTV;
    TextView complaintAreaTV;
    TextView complaintAddressTV;
    TextView complaintDescTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint_details);
        init();
        Intent intent = getIntent();
        final Complaint complaint = (Complaint) intent.getSerializableExtra("complaint");
        Log.e(TAG, "onCreate: Complaint recieved: " + complaint);
        loadComplaint(complaint);


        complaintIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent imageIntent = new Intent(getApplicationContext(), SingleImageActivity.class);
                imageIntent.putExtra("image-url", complaint.getMedia());
                startActivity(imageIntent);
            }
        });
    }

    public void init() {
        complaintIV = findViewById(R.id.complaintDetailsIV);
        complaintSenderTV = findViewById(R.id.complaintSenderTV);
        complaintAreaTV = findViewById(R.id.complaintAreaTV);
        complaintAddressTV = findViewById(R.id.complaintAddressTV);
        complaintDescTV = findViewById(R.id.complaintDescTV);
        Toolbar toolbar = findViewById(R.id.complaintDetailsToolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Simon
                goBack();
            }
        });

    }

    public void loadComplaint(Complaint complaint) {
        complaintSenderTV.setText(complaint.getUsername());
        complaintAreaTV.setText(complaint.getArea());
        complaintAddressTV.setText(complaint.getAddress());
        complaintDescTV.setText(complaint.getDescription());
        Glide.with(getApplicationContext())
                .load(complaint.getMedia())
                .placeholder(R.drawable.jack)
                .transition(withCrossFade())
                .into(complaintIV);
    }

    @Override
    public void onBackPressed() {
        goBack();
        super.onBackPressed();
    }

    public void goBack() {
        Intent intent = new Intent(ComplaintDetailsActivity.this, ComplaintActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
