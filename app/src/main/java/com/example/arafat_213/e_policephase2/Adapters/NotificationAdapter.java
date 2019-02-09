package com.example.arafat_213.e_policephase2.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.arafat_213.e_policephase2.Models.Notification;
import com.example.arafat_213.e_policephase2.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.ArrayList;

public class NotificationAdapter extends FirebaseRecyclerAdapter<Notification, NotificationAdapter.NotificationViewHolder> {

    private ArrayList<Notification> notificationArrayList;
    private Context context;

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public NotificationAdapter(@NonNull FirebaseRecyclerOptions<Notification> options) {
        super(options);
    }

    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.item_notification, viewGroup, false);
        return new NotificationViewHolder(view);
    }



    @Override
    protected void onBindViewHolder(@NonNull NotificationViewHolder notificationViewHolder, int position, @NonNull Notification model) {
        notificationViewHolder.notifyType.setText(model.getNotificationType());
        notificationViewHolder.notifyContent.setText(model.getNotificationContent());
    }


    class NotificationViewHolder extends RecyclerView.ViewHolder {

        public ImageView notifyImage;
        public TextView notifyType, notifyContent;

        public NotificationViewHolder(@NonNull View itemView) {
            super(itemView);
            notifyImage = itemView.findViewById(R.id.notifyIV);
            notifyType = itemView.findViewById(R.id.notifyTypeTV);
            notifyContent = itemView.findViewById(R.id.notifyContentTV);

        }
    }
}
