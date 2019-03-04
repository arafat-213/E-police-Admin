package com.example.arafat_213.e_policephase2.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.arafat_213.e_policephase2.Adapters.AllFeedbacksAdadpter;
import com.example.arafat_213.e_policephase2.Adapters.FeedbackAdapter;
import com.example.arafat_213.e_policephase2.Models.Feedback;
import com.example.arafat_213.e_policephase2.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

import static com.example.arafat_213.e_policephase2.Models.Feedback.COMPARE_BY_TIMESTAMP;


public class FeedbackActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    FeedbackAdapter mFeedbackAdapter;
    AllFeedbacksAdadpter mAllFeedbacksAdadpter;
    ProgressBar mProgressBar;
    ArrayList<Feedback> mFeedbacksArrayList = new ArrayList<Feedback>();
    private static final String TAG = "FeedbackActivity";
    DatabaseReference feedbacksRef = FirebaseDatabase.getInstance().getReference("feedbacks");
    String mKey;

    ValueEventListener mValueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            loadAllFeedbacks();
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };

    //    @Override
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        Intent intent = getIntent();
        mKey = intent.getStringExtra("key");
        init();
        if (mKey == null) {
            loadAllFeedbacks();
        } else
            loadFeedbacks(mKey);

    }

    public void init() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_arrow_back_black_24dp);
        mRecyclerView = findViewById(R.id.feedbackRV);
        mProgressBar = findViewById(R.id.feedbackPB);
        mProgressBar.setVisibility(View.VISIBLE);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mFeedbackAdapter != null)
            mFeedbackAdapter.startListening();
    }


    // Load all the feedbacks from database
    public void loadAllFeedbacks() {
        Log.e(TAG, "loadAllFeedbacks: " + mFeedbacksArrayList);
        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        Query query = databaseReference.child("feedbacks");

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot singleSnapshot : dataSnapshot.getChildren()) {
                    for (DataSnapshot snapshot : singleSnapshot.getChildren()) {
                        Feedback feedback = snapshot.getValue(Feedback.class);
                        mFeedbacksArrayList.add(feedback);
                        //Log.e(TAG, "onDataChange: Feedback found: "+feedback);
                    }
                }
                Collections.sort(mFeedbacksArrayList, COMPARE_BY_TIMESTAMP);
                mAllFeedbacksAdadpter = new AllFeedbacksAdadpter(getApplicationContext(), mFeedbacksArrayList);
                mRecyclerView.setAdapter(mAllFeedbacksAdadpter);
                Log.e(TAG, "onDataChange: " + mFeedbacksArrayList);
                mProgressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Log.e(TAG, "loadAllFeedbacks: " + mFeedbacksArrayList);

    }

    // Load feedbacks of a particular policemen at stored at key = "mKey"
    public void loadFeedbacks(String key) {
        Query feedbacksQuery = FirebaseDatabase.getInstance().
                getReference().
                child("feedbacks")
                .child(key);
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
        mRecyclerView.setAdapter(mFeedbackAdapter);
    }
}
