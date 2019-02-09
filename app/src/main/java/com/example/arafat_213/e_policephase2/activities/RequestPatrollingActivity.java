package com.example.arafat_213.e_policephase2.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.arafat_213.e_policephase2.Adapters.ComplaintsAdapter;
import com.example.arafat_213.e_policephase2.Adapters.RequestsAdapter;
import com.example.arafat_213.e_policephase2.Models.Complaint;
import com.example.arafat_213.e_policephase2.Models.PatrollingRequest;
import com.example.arafat_213.e_policephase2.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class RequestPatrollingActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    RequestsAdapter mRequestsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_patrolling);
        mRecyclerView = findViewById(R.id.requestRecyclerView);

        FirebaseApp.initializeApp(this);

        Query requestsRef = FirebaseDatabase.getInstance()
                .getReference()
                .child("requests");


        FirebaseRecyclerOptions<PatrollingRequest> options =
                new FirebaseRecyclerOptions.Builder<PatrollingRequest>()
                        .setQuery(requestsRef, PatrollingRequest.class)
                        .build();

        mRequestsAdapter = new RequestsAdapter(options);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mRequestsAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mRequestsAdapter.startListening();
    }
}
