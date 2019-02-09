package com.example.arafat_213.e_policephase2.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.arafat_213.e_policephase2.Adapters.FeedbackAdapter;
import com.example.arafat_213.e_policephase2.Adapters.NotificationAdapter;
import com.example.arafat_213.e_policephase2.Models.Feedback;
import com.example.arafat_213.e_policephase2.Models.Notification;
import com.example.arafat_213.e_policephase2.R;

import java.util.ArrayList;

public class FeedbackActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ArrayList<Feedback> feedbackArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_arrow_back_black_24dp);

        mRecyclerView = findViewById(R.id.feedbackRV);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        feedbackArrayList = new ArrayList<Feedback>();

        for (int i=0; i<10; i++)
            feedbackArrayList.add(new Feedback("BAJIRAO SINGHAM","SINGHAD",5,"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries"));
//        No policemanAdapter = new PolicemanAdapter(policemanArrayList);
//        policeRecyclerView.setAdapter(policemanAdapter);
        FeedbackAdapter feedbackAdapter = new FeedbackAdapter(feedbackArrayList);
        mRecyclerView.setAdapter(feedbackAdapter);
    }

}
