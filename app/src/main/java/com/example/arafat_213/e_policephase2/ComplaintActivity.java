package com.example.arafat_213.e_policephase2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.arafat_213.e_policephase2.Adapters.ComplaintAdapter;
import com.example.arafat_213.e_policephase2.Adapters.NotificationAdapter;
import com.example.arafat_213.e_policephase2.Models.Complaint;
import com.example.arafat_213.e_policephase2.Models.Notification;

import java.util.ArrayList;

public class ComplaintActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ArrayList<Complaint> complaintArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_arrow_back_black_24dp);

        mRecyclerView = findViewById(R.id.complaintRV);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        complaintArrayList = new ArrayList<Complaint>();

        for (int i=0; i<10; i++)
            complaintArrayList.add(new Complaint("TYPE: ANONYMOUS OR KNOWN IDENTITY" , " Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s."));
//        No policemanAdapter = new PolicemanAdapter(policemanArrayList);
//        policeRecyclerView.setAdapter(policemanAdapter);
        ComplaintAdapter complaintAdapter = new ComplaintAdapter(complaintArrayList);
        mRecyclerView.setAdapter(complaintAdapter);

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}
