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

import java.util.ArrayList;

public class ComplaintAdapter extends RecyclerView.Adapter<ComplaintAdapter.ComplaintViewHolder> {


    private ArrayList<Complaint> complaintArrayList;
    private Context context;

    public ComplaintAdapter(ArrayList<Complaint> complaintArrayList) {
        this.complaintArrayList = complaintArrayList;
    }

    @NonNull
    @Override
    public ComplaintAdapter.ComplaintViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.item_complaint, viewGroup, false);
        return new ComplaintViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ComplaintAdapter.ComplaintViewHolder complaintViewHolder, int i) {
        complaintViewHolder.complaintType.setText(complaintArrayList.get(i).getComplaintType());
        complaintViewHolder.complaintContent.setText(complaintArrayList.get(i).getComplaintContent());
        // notificationViewHolder.notifyImage.setImageResource(notificationArrayList.get(i).getNotificationImage());
    }

    @Override
    public int getItemCount() {
        return complaintArrayList.size();
    }

    class ComplaintViewHolder extends RecyclerView.ViewHolder {

        public ImageView complaintImage;
        public TextView complaintType, complaintContent;

        public ComplaintViewHolder(@NonNull View itemView) {
            super(itemView);
            complaintImage = itemView.findViewById(R.id.complaintIV);
            complaintType = itemView.findViewById(R.id.complaintTypeTV);
            complaintContent = itemView.findViewById(R.id.complaintContentTV);
        }

    }
}



//
////    }

