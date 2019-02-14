package com.example.arafat_213.e_policephase2.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.example.arafat_213.e_policephase2.Adapters.RequestsAdapter;
import com.example.arafat_213.e_policephase2.Models.PatrollingRequest;
import com.example.arafat_213.e_policephase2.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class RequestPatrollingActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    RequestsAdapter mRequestsAdapter;
    ProgressBar mProgressBar;
    RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_patrolling);
        init();

        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mRequestsAdapter);
    }

    public void init() {
        mRecyclerView = findViewById(R.id.requestRecyclerView);
        mProgressBar = findViewById(R.id.requestPB);
        mProgressBar.setVisibility(View.VISIBLE);
        FirebaseApp.initializeApp(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_arrow_back_black_24dp);

        Query requestsRef = FirebaseDatabase.getInstance()
                .getReference()
                .child("requests");


        FirebaseRecyclerOptions<PatrollingRequest> options =
                new FirebaseRecyclerOptions.Builder<PatrollingRequest>()
                        .setQuery(requestsRef, PatrollingRequest.class)
                        .build();

        layoutManager = new LinearLayoutManager(this);
        mRequestsAdapter = new RequestsAdapter(options) {
            @Override
            public void onDataChanged() {
                super.onDataChanged();
                mProgressBar.setVisibility(View.INVISIBLE);
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
        mRequestsAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mRequestsAdapter.stopListening();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}
