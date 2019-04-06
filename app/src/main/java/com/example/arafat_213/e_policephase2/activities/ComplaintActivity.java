package com.example.arafat_213.e_policephase2.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.example.arafat_213.e_policephase2.Adapters.ComplaintsAdapter;
import com.example.arafat_213.e_policephase2.Models.Complaint;
import com.example.arafat_213.e_policephase2.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ComplaintActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    ComplaintsAdapter complaintsAdapter;
    ProgressBar mProgressBar;
    RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint);

        init();


//        for (int i=0; i<10; i++)
//            complaintArrayList.add(new Complaint("TYPE: ANONYMOUS OR KNOWN IDENTITY" , " Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s."));
//        No policemanAdapter = new PolicemanAdapter(policemanArrayList);
//        policeRecyclerView.setAdapter(policemanAdapter);
//        ComplaintAdapter complaintAdapter = new ComplaintAdapter(complaintArrayList);
//        mRecyclerView.setAdapter(complaintAdapter);


    }

    public void init() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_arrow_back_black_24dp);

        mRecyclerView = findViewById(R.id.complaintRV);
        mProgressBar = findViewById(R.id.complaintPB);
        mProgressBar.setVisibility(View.VISIBLE);
        FirebaseApp.initializeApp(this);

        Query complainstRef = FirebaseDatabase.getInstance()
                .getReference()
                .child("complaints");


        FirebaseRecyclerOptions<Complaint> options =
                new FirebaseRecyclerOptions.Builder<Complaint>()
                        .setQuery(complainstRef, Complaint.class)
                        .build();

        complaintsAdapter = new ComplaintsAdapter(options,getApplicationContext()) {
            @Override
            public void onDataChanged() {
                super.onDataChanged();
                mProgressBar.setVisibility(View.INVISIBLE);
            }
        };
        layoutManager = new LinearLayoutManager(this);
        //   ((LinearLayoutManager) layoutManager).setReverseLayout(true);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(complaintsAdapter);
    }
    @Override
    public boolean onSupportNavigateUp() {
        Intent intent = new Intent(ComplaintActivity.this, DashboardActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        return super.onSupportNavigateUp();
    }

    @Override
    protected void onStart() {
        super.onStart();
        complaintsAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        complaintsAdapter.stopListening();
    }
}
