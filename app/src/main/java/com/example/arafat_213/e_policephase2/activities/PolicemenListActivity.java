package com.example.arafat_213.e_policephase2.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.arafat_213.e_policephase2.Adapters.PolicemanAdapter;
import com.example.arafat_213.e_policephase2.Models.Policeman;
import com.example.arafat_213.e_policephase2.R;

import java.util.ArrayList;

public class PolicemenListActivity extends AppCompatActivity {

    private RecyclerView policeRecyclerView;
    private ArrayList<Policeman> policemanArrayList;
    private Policeman policeman;
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
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent intent = new Intent(PolicemenListActivity.this, AddPolicemanActivity.class);
                startActivity(intent);
            }
        });

        policeRecyclerView = findViewById(R.id.policeRecyclerView);
        policemanArrayList = new ArrayList<Policeman>();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_arrow_back_black_24dp);
        for (int i = 0; i < 20; i++) {
            policeman = new Policeman();
            //policeman.setImage_id(R.drawable.jack);
            policeman.setName("Jack Ryan");
            policeman.setRank("Commissioner of police");
            policeman.setArea("Vadodara city");
            policeman.setEmail("tai.arafat@amazon.in");
            policeman.setMobile_no("9876543210");
            policeman.setRating(4.20f);
            policemanArrayList.add(policeman);
        }

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        policeRecyclerView.setLayoutManager(layoutManager);

        PolicemanAdapter policemanAdapter = new PolicemanAdapter(policemanArrayList);
        policeRecyclerView.setAdapter(policemanAdapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}
