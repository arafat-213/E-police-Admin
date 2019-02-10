package com.example.arafat_213.e_policephase2.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.arafat_213.e_policephase2.Models.Feedback;
import com.example.arafat_213.e_policephase2.Models.Notification;
import com.example.arafat_213.e_policephase2.R;

import java.util.ArrayList;

public class FeedbackAdapter extends RecyclerView.Adapter<FeedbackAdapter.FeedbackViewHolder> {


    private ArrayList<Feedback> feedbackArrayList;
    private Context context;

    public FeedbackAdapter(ArrayList<Feedback> feedbackArrayList) {
        this.feedbackArrayList = feedbackArrayList;
    }

    @NonNull
    @Override
    public FeedbackViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.item_feedback, viewGroup, false);
        return new FeedbackViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedbackViewHolder feedbackViewHolder, int i) {
        feedbackViewHolder.policeStation.setText(feedbackArrayList.get(i).getPoliceStation());
        feedbackViewHolder.policeOfficer.setText(feedbackArrayList.get(i).getPoliceOfficer());
        feedbackViewHolder.rating.setText(" "+feedbackArrayList.get(i).getRating()+" ★");
        feedbackViewHolder.description.setText(feedbackArrayList.get(i).getDescription());
    }

    @Override
    public int getItemCount() {
        return feedbackArrayList.size();
    }

    class FeedbackViewHolder extends  RecyclerView.ViewHolder{

        public TextView policeStation, policeOfficer, rating, description;

        public FeedbackViewHolder(@NonNull View itemView) {
            super(itemView);
            policeStation = itemView.findViewById(R.id.feedbackStationTV);
            policeOfficer = itemView.findViewById(R.id.feedbackNameTV);
            rating = itemView.findViewById(R.id.feedbackRatingTV);
            description = itemView.findViewById(R.id.feedbackContentTV);
        }
    }
}
