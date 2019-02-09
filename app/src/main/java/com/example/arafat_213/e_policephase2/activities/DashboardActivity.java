package com.example.arafat_213.e_policephase2.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.arafat_213.e_policephase2.Adapters.DashboardModuleAdapter;
import com.example.arafat_213.e_policephase2.Models.DashboardModule;
import com.example.arafat_213.e_policephase2.R;
import com.google.firebase.FirebaseApp;

import java.util.ArrayList;

public class DashboardActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<DashboardModule> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        arrayList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);

        arrayList.add(new DashboardModule("Police Force", R.drawable.icon_policeman2));
        arrayList.add(new DashboardModule("Notifications", R.drawable.icon_notification));
        arrayList.add(new DashboardModule("Complaints", R.drawable.icon_complaint));
        arrayList.add(new DashboardModule("Requests", R.drawable.icon_police_station));
        arrayList.add(new DashboardModule("Feedbacks", R.drawable.icon_request));

        // arrayList.add(new DashboardModule("Give feedback", R.drawable.feedback));
//        arrayList.add(new DashboardModule("Connect with us", R.drawable.icon_connect));

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        DashboardModuleAdapter dashboardModuleAdapter = new DashboardModuleAdapter(arrayList);
        recyclerView.setAdapter(dashboardModuleAdapter);

        temp();
    }

    public void temp() {
        //
    }
}
