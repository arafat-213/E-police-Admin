package com.example.arafat_213.e_policephase2.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.arafat_213.e_policephase2.Models.PatrollingRequest;
import com.example.arafat_213.e_policephase2.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

public class RequestsAdapter extends FirebaseRecyclerAdapter <PatrollingRequest, RequestsAdapter.RequestsViewHolder> {

    private static final int PENDING = 0;
    private static final int SEEN = 1;
    private static final int COMPLETED = 2;
    int lightgray;
    DatabaseReference mRequestsRef;
    String mKey;
    private Context mContext;
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public RequestsAdapter(@NonNull FirebaseRecyclerOptions<PatrollingRequest> options, Context context) {
        super(options);
        this.mContext = context;
        lightgray = ContextCompat.getColor(mContext, R.color.lightgray);
        mRequestsRef = FirebaseDatabase.getInstance().getReference().child("requests");
    }

    @Override
    protected void onBindViewHolder(@NonNull final RequestsViewHolder requestsViewHolder, final int position, @NonNull final PatrollingRequest model) {
        requestsViewHolder.requestNameTV.setText(model.getFullName());
        requestsViewHolder.requestFromDateTV.setText(model.getFromDate());
        requestsViewHolder.requestToDateTV.setText(model.getToDate());
        requestsViewHolder.requestAreaTV.setText(model.getArea());
        requestsViewHolder.requestPhoneTV.setText(model.getPhoneNo());
        requestsViewHolder.requestAddressTV.setText(model.getAddress());

        // Fetch status from database and if status == SEEN,
        // hide the seen button and set bgColor == lightgray
        // if status == COMPLETED hide the card
        switch (model.getStatus()) {
            case SEEN:
                requestsViewHolder.seenBTN.setEnabled(false);
                requestsViewHolder.seenBTN.setVisibility(View.GONE);
                requestsViewHolder.cardView.setBackgroundColor(lightgray);
                break;
            case COMPLETED:
                requestsViewHolder.cardView.setVisibility(View.GONE);
                break;
        }

        requestsViewHolder.seenBTN.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                mKey = getRef(position).getKey();
                if (mKey != null)
                    mRequestsRef.child(mKey).child("status").setValue(SEEN);
            }
        });


        requestsViewHolder.completedBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mKey = getRef(position).getKey();
                if (mKey != null)
                    mRequestsRef.child(mKey).child("status").setValue(COMPLETED);
            }
        });

        // An Intent to call the number mentioned in request when it is clicked
        requestsViewHolder.requestPhoneTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialPhoneNumber(model.getPhoneNo());
            }
        });
    }

    @NonNull
    @Override
    public RequestsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.item_patrolling_request, viewGroup, false);
        return new RequestsViewHolder(view);
    }


    public void dialPhoneNumber(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:+91" + phoneNumber));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);

    }

    class RequestsViewHolder extends RecyclerView.ViewHolder {

        TextView requestNameTV;
        TextView requestPhoneTV;
        TextView requestAddressTV;
        TextView requestFromDateTV;
        TextView requestToDateTV;
        TextView requestAreaTV;
        Button seenBTN;
        Button completedBTN;
        CardView cardView;
        public RequestsViewHolder(@NonNull View itemView) {
            super(itemView);
            requestNameTV = itemView.findViewById(R.id.requestNameTV);
            requestPhoneTV = itemView.findViewById(R.id.requestPhoneTV);
            requestPhoneTV.setPaintFlags(requestPhoneTV.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
            requestAddressTV = itemView.findViewById(R.id.requestAddressTV);
            requestFromDateTV = itemView.findViewById(R.id.requestFromDateTV);
            requestToDateTV = itemView.findViewById(R.id.requestToDateTV);
            requestAreaTV = itemView.findViewById(R.id.requestAreaTV);
            seenBTN = itemView.findViewById(R.id.seenBTN);
            completedBTN = itemView.findViewById(R.id.completedBTN);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }
}
