package com.example.arafat_213.e_policephase2.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.arafat_213.e_policephase2.Models.Complaint;
import com.example.arafat_213.e_policephase2.R;
import com.example.arafat_213.e_policephase2.activities.ComplaintDetailsActivity;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

public class ComplaintsAdapter extends FirebaseRecyclerAdapter<Complaint, ComplaintsAdapter.ComplaintsViewHolder> {

    Context mContext;
    private static final int PENDING = 0;
    private static final int SEEN = 1;
    private static final int COMPLETED = 2;
    int lightgray;
    int white;
    DatabaseReference mComplaintsRef;
    String mKey;
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */


    public ComplaintsAdapter(@NonNull FirebaseRecyclerOptions<Complaint> options, Context context) {
        super(options);
        this.mContext = context;
        lightgray = ContextCompat.getColor(mContext, R.color.lightgray);
        white = ContextCompat.getColor(mContext, android.R.color.white);
        mComplaintsRef = FirebaseDatabase.getInstance().getReference().child("complaints");
    }
    /*public ComplaintsAdapter(Class<Complaint> modelClass, int modelLayout, Class<ComplaintsHolder> viewHolderClass, DatabaseReference ref, Context context) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        this.mContext = context;

    }*/

    @Override
    protected void onBindViewHolder(@NonNull final ComplaintsViewHolder complaintsViewHolder, final int position, @NonNull final Complaint model) {
        complaintsViewHolder.complaintName.setText(model.getUsername());
        complaintsViewHolder.complaintContent.setText(model.getDescription());
        Glide.with(mContext)
                .load(model.getMedia())
                .circleCrop()
                .thumbnail(0.25f)
                .into(complaintsViewHolder.complaintImage);

        // Fetches status from database and if status == SEEN,
        // hide the seen button and set bgColor == lightgray
        // if status == COMPLETED hide the card
        switch (model.getStatus()) {
            case PENDING:
                complaintsViewHolder.seenBTN.setEnabled(true);
                complaintsViewHolder.cardView.setCardBackgroundColor(white);
                complaintsViewHolder.cardView.setVisibility(View.VISIBLE);
                break;
            case SEEN:
                complaintsViewHolder.seenBTN.setEnabled(false);
                complaintsViewHolder.seenBTN.setVisibility(View.GONE);
                complaintsViewHolder.cardView.setBackgroundColor(lightgray);
                break;
            case COMPLETED:
                complaintsViewHolder.cardView.setVisibility(View.GONE);
                break;
        }

        // Showing complaint details when a card is clicked
        complaintsViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, ComplaintDetailsActivity.class);
                intent.putExtra("complaint", model);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });

        complaintsViewHolder.seenBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mKey = getRef(position).getKey();
                if (mKey != null)
                    mComplaintsRef.child(mKey).child("status").setValue(SEEN);
            }
        });


        complaintsViewHolder.completedBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mKey = getRef(position).getKey();
                if (mKey != null)
                    mComplaintsRef.child(mKey).child("status").setValue(COMPLETED);
            }
        });
    }

    @NonNull
    @Override
    public ComplaintsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.item_complaint, viewGroup, false);
        return new ComplaintsViewHolder(view);
    }

    class ComplaintsViewHolder extends RecyclerView.ViewHolder {

        ImageView complaintImage;
        TextView complaintName, complaintContent;
        CardView cardView;
        Button seenBTN;
        Button completedBTN;

        ComplaintsViewHolder(@NonNull View itemView) {
            super(itemView);
            complaintImage = itemView.findViewById(R.id.complaintIV);
            complaintName = itemView.findViewById(R.id.complaintNameTV);
            complaintContent = itemView.findViewById(R.id.complaintContentET);
            cardView = itemView.findViewById(R.id.complaintCardView);
            seenBTN = itemView.findViewById(R.id.complaintSeenBTN);
            completedBTN = itemView.findViewById(R.id.complaintCompletedBTN);
        }
    }
}
