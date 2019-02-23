package com.example.arafat_213.e_policephase2.Adapters;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.arafat_213.e_policephase2.GlideApp;
import com.example.arafat_213.e_policephase2.Models.Notification;
import com.example.arafat_213.e_policephase2.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;

import java.util.ArrayList;

public class NotificationAdapter extends FirebaseRecyclerAdapter<Notification, NotificationAdapter.NotificationViewHolder> {

    private ArrayList<Notification> notificationArrayList;
    private Context context;
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
        this.context=context;
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
        Glide.with(context).load(model.getNotificationImage()).circleCrop().into(notificationViewHolder.notifyImage);


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
