package com.example.arafat_213.e_policephase2.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.example.arafat_213.e_policephase2.Adapters.PolicemanAdapter;
import com.example.arafat_213.e_policephase2.Models.Policeman;
import com.example.arafat_213.e_policephase2.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PolicemenListActivity extends AppCompatActivity {

    private RecyclerView policeRecyclerView;
    private PolicemanAdapter mPolicemanAdapter;
    ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_policemen_list);
        mProgressBar = findViewById(R.id.policemenPB);
        mProgressBar.setVisibility(View.VISIBLE);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PolicemenListActivity.this, AddPolicemanActivity.class);
                startActivity(intent);
            }
        });

        policeRecyclerView = findViewById(R.id.policeRecyclerView);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_arrow_back_black_24dp);




        FirebaseApp.initializeApp(this);

        Query policeListQuery = FirebaseDatabase.getInstance().getReference().child("policemen");

        FirebaseRecyclerOptions <Policeman> options =
                new FirebaseRecyclerOptions.Builder<Policeman>()
                        .setQuery(policeListQuery,Policeman.class)
                        .build();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        ((LinearLayoutManager) layoutManager).setReverseLayout(true);
        policeRecyclerView.setLayoutManager(layoutManager);
        mPolicemanAdapter = new PolicemanAdapter(options, getApplicationContext()) {
            @Override
            public void onDataChanged() {
                super.onDataChanged();
                mProgressBar.setVisibility(View.INVISIBLE);
            }
        };
        policeRecyclerView.setAdapter(mPolicemanAdapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPolicemanAdapter.startListening();
    }
}
