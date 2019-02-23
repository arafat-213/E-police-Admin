package com.example.arafat_213.e_policephase2.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.arafat_213.e_policephase2.Models.Feedback;
import com.example.arafat_213.e_policephase2.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class FeedbackAdapter extends FirebaseRecyclerAdapter<Feedback, FeedbackAdapter.FeedbackViewHolder> {

    private Context context;


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public FeedbackAdapter(@NonNull FirebaseRecyclerOptions<Feedback> options) {
        super(options);
    }

    @NonNull
    @Override
    public FeedbackViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.item_feedback, viewGroup, false);
        return new FeedbackViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedbackViewHolder feedbackViewHolder, int position, @NonNull Feedback model) {
        feedbackViewHolder.policeStationTV.setText(model.getPoliceStation());
        feedbackViewHolder.policeOfficerNameTV.setText(model.getPoliceOfficer());
        feedbackViewHolder.policeRatingTV.setText(" " +model.getRating()+" ★");
        feedbackViewHolder.descriptionTV.setText(model.getDescription());
    }

    public class FeedbackViewHolder extends  RecyclerView.ViewHolder{

        public TextView policeStationTV, policeOfficerNameTV, policeRatingTV, descriptionTV;

        public FeedbackViewHolder(@NonNull View itemView) {
            super(itemView);
            policeStationTV = itemView.findViewById(R.id.feedbackStationTV);
            policeOfficerNameTV = itemView.findViewById(R.id.feedbackNameTV);
            policeRatingTV = itemView.findViewById(R.id.feedbackRatingTV);
            descriptionTV = itemView.findViewById(R.id.feedbackContentTV);
        }
    }
}
