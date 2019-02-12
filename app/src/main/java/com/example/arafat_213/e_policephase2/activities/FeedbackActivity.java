package com.example.arafat_213.e_policephase2.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.example.arafat_213.e_policephase2.Adapters.FeedbackAdapter;
import com.example.arafat_213.e_policephase2.Models.Feedback;
import com.example.arafat_213.e_policephase2.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;


public class FeedbackActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    FeedbackAdapter mFeedbackAdapter;
    ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        init();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mFeedbackAdapter);
    }

    public void init() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_arrow_back_black_24dp);
        mRecyclerView = findViewById(R.id.feedbackRV);
        mProgressBar = findViewById(R.id.feedbackPB);
        mProgressBar.setVisibility(View.VISIBLE);
        Query feedbacksQuery = FirebaseDatabase.getInstance().
                getReference().
                child("feedback")
                .orderByValue();

        FirebaseRecyclerOptions<Feedback> options =
                new FirebaseRecyclerOptions.Builder<Feedback>()
                        .setQuery(feedbacksQuery, Feedback.class)
                        .build();

        mFeedbackAdapter = new FeedbackAdapter(options){
            @Override
            public void onDataChanged() {
                mProgressBar.setVisibility(View.INVISIBLE);
            }
        };
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mFeedbackAdapter.startListening();
    }

}
