package com.example.arafat_213.e_policephase2.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.arafat_213.e_policephase2.Models.Feedback;
import com.example.arafat_213.e_policephase2.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AllFeedbacksAdadpter extends RecyclerView.Adapter<AllFeedbacksAdadpter.FeedbackViewHolder> {

    private Context mContext;
    private ArrayList<Feedback> feedbackArrayList;

    public AllFeedbacksAdadpter(Context mContext, ArrayList<Feedback> feedbackArrayList) {
        this.mContext = mContext;
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
        feedbackViewHolder.policeOfficerNameTV.setText(feedbackArrayList.get(i).getPoliceOfficer());
        feedbackViewHolder.policeRatingTV.setText(" " + feedbackArrayList.get(i).getRating() + " ★");
        feedbackViewHolder.descriptionTV.setText(feedbackArrayList.get(i).getDescription());
    }

    @Override
    public int getItemCount() {
        return feedbackArrayList.size();
    }

    public class FeedbackViewHolder extends RecyclerView.ViewHolder {

        public TextView policeStationTV, policeOfficerNameTV, policeRatingTV, descriptionTV;

        public FeedbackViewHolder(@NonNull View itemView) {
            super(itemView);
//            policeStationTV = itemView.findViewById(R.id.feedbackStationTV);
            policeOfficerNameTV = itemView.findViewById(R.id.complaintNameTV);
            policeRatingTV = itemView.findViewById(R.id.feedbackRatingTV);
            descriptionTV = itemView.findViewById(R.id.complaintContentET);
        }
    }
}
