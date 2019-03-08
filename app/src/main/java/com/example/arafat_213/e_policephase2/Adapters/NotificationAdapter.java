package com.example.arafat_213.e_policephase2.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.arafat_213.e_policephase2.Models.Notification;
import com.example.arafat_213.e_policephase2.R;
import com.example.arafat_213.e_policephase2.activities.NotificationDetailsActivity;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NotificationAdapter extends FirebaseRecyclerAdapter<Notification, NotificationAdapter.NotificationViewHolder> {

    private ArrayList<Notification> notificationArrayList;
    private Context mContext;
    //StorageReference mStorageRef,pathRef;
   // private Uri mImageUri;
   // private StorageTask mDownTask;

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */


    public NotificationAdapter(@NonNull FirebaseRecyclerOptions<Notification> options, Context context) {
        super(options);
        this.mContext = context;
    }


    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.item_notification, viewGroup, false);
        return new NotificationViewHolder(view);
    }





    @Override
    protected void onBindViewHolder(@NonNull final NotificationViewHolder notificationViewHolder, int position, @NonNull final Notification model)
    {

        notificationViewHolder.notifyType.setText(model.getNotificationType());
        notificationViewHolder.notifyContent.setText(model.getNotificationContent());
        Glide.with(mContext)
                .load(model.getNotificationImage())
                .thumbnail(0.25f)
                .circleCrop()
                .into(notificationViewHolder.notifyImage);

        notificationViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, NotificationDetailsActivity.class);
                intent.putExtra("Notification", model);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });

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
