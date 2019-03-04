package com.example.arafat_213.e_policephase2.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.arafat_213.e_policephase2.Adapters.PolicemanAdapter;
import com.example.arafat_213.e_policephase2.Models.Policeman;
import com.example.arafat_213.e_policephase2.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class PolicemenListActivity extends AppCompatActivity {

    private RecyclerView policeRecyclerView;
    private Policeman policeman;
    private PolicemanAdapter mPolicemanAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_policemen_list);
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
        policeRecyclerView.setLayoutManager(layoutManager);
        mPolicemanAdapter = new PolicemanAdapter(options,getApplicationContext());
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
