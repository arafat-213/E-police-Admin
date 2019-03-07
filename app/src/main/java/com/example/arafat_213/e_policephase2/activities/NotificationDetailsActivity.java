package com.example.arafat_213.e_policephase2.activities;

import android.os.Bundle;
import android.view.Menu;

import com.example.arafat_213.e_policephase2.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class NotificationDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_details);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //  getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }
}
