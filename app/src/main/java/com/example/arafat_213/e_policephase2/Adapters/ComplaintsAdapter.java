package com.example.arafat_213.e_policephase2.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.arafat_213.e_policephase2.Models.Complaint;
import com.example.arafat_213.e_policephase2.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class ComplaintsAdapter extends FirebaseRecyclerAdapter<Complaint, ComplaintsAdapter.ComplaintsViewHolder> {

    Context mContext;



    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public ComplaintsAdapter(@NonNull FirebaseRecyclerOptions<Complaint> options) {
        super(options);
    }

    /*public ComplaintsAdapter(Class<Complaint> modelClass, int modelLayout, Class<ComplaintsHolder> viewHolderClass, DatabaseReference ref, Context context) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        this.mContext = context;

    }*/

    @Override
    protected void onBindViewHolder(@NonNull ComplaintsViewHolder  complaintsViewHolder, int position, @NonNull Complaint model) {
        complaintsViewHolder.complaintType.setText(""+model.isAnonymous());
        complaintsViewHolder.complaintContent.setText(model.getDescription());
    }

    @NonNull
    @Override
    public ComplaintsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.item_complaint, viewGroup, false);
        return new ComplaintsViewHolder(view);
    }

    class ComplaintsViewHolder extends RecyclerView.ViewHolder {

        public ImageView complaintImage;
        public TextView complaintType, complaintContent;

        public ComplaintsViewHolder(@NonNull View itemView) {
            super(itemView);
            complaintImage = itemView.findViewById(R.id.complaintIV);
            complaintType = itemView.findViewById(R.id.complaintTypeTV);
            complaintContent = itemView.findViewById(R.id.complaintContentTV);
        }
    }
}
