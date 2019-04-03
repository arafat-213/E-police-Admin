package com.example.arafat_213.e_policephase2.activities;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;

import com.example.arafat_213.e_policephase2.Adapters.DashboardModuleAdapter;
import com.example.arafat_213.e_policephase2.Models.DashboardModule;
import com.example.arafat_213.e_policephase2.Models.Notification;
import com.example.arafat_213.e_policephase2.R;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class DashboardActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<DashboardModule> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

            NotificationChannel channel =
                    new NotificationChannel("Phase2Notifications","Phase2Notifications", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }




        arrayList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);

        arrayList.add(new DashboardModule("Police Force", R.drawable.icon_policeman2));
        arrayList.add(new DashboardModule("Notifications", R.drawable.icon_notification));
        arrayList.add(new DashboardModule("Complaints", R.drawable.icon_complaint));
        arrayList.add(new DashboardModule("Requests", R.drawable.icon_request));
        arrayList.add(new DashboardModule("Feedbacks", R.drawable.feedback));

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
