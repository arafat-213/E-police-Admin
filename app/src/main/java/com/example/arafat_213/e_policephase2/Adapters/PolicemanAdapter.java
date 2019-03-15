package com.example.arafat_213.e_policephase2.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.arafat_213.e_policephase2.Models.Policeman;
import com.example.arafat_213.e_policephase2.R;
import com.example.arafat_213.e_policephase2.activities.AddPolicemanActivity;
import com.example.arafat_213.e_policephase2.activities.FeedbackActivity;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PolicemanAdapter extends FirebaseRecyclerAdapter<Policeman, PolicemanAdapter.PolicemanViewHolder> {

    private Context mContext;
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */

    String mKey;

    public PolicemanAdapter(@NonNull FirebaseRecyclerOptions<Policeman> options, Context mContext) {
        super(options);
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public PolicemanViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.item_policemen_list, viewGroup, false);
        return new PolicemanViewHolder(view);
    }



    @Override
    protected void onBindViewHolder(@NonNull final PolicemanViewHolder policemanViewHolder, final int position, @NonNull final Policeman model) {
        Glide.with(mContext)
                .load(model.getImage_id())
                .circleCrop()
                .thumbnail(0.25f)
                .into(policemanViewHolder.policemanImage);
        policemanViewHolder.policemanName.setText(model.getName());
        policemanViewHolder.policemanRank.setText(model.getRank());
        policemanViewHolder.policemanArea.setText(model.getArea());
        policemanViewHolder.policemanPhone.setText(model.getMobile_no());
        policemanViewHolder.policemanEmail.setText(model.getEmail());
        policemanViewHolder.policemanRatingTV.setText(model.getRating() + " ★");
        mKey = getRef(position).getKey();

        policemanViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mKey = getRef(position).getKey();

//                mOnInputListener.sendKey(mKey);
                Intent mIntent = new Intent(mContext, FeedbackActivity.class);
                mIntent.putExtra("key", mKey);
                mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
               /*
               A pretty cool animation
                Bundle options = ActivityOptionsCompat.makeScaleUpAnimation(
                        policemanViewHolder.itemView, 0, 0, policemanViewHolder.itemView.getWidth(), policemanViewHolder.itemView.getHeight()).toBundle();

                ActivityCompat.startActivity(mContext, mIntent, options);*/
                mContext.startActivity(mIntent);
            }
        });

        policemanViewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mKey = getRef(position).getKey();
                Intent intent = new Intent(mContext, AddPolicemanActivity.class);
                intent.putExtra("policeman", model);
                intent.putExtra("key", mKey);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
                return false;
            }
        });

        policemanViewHolder.policemanPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialPhoneNumber(model.getMobile_no());
            }
        });
    }

    public void dialPhoneNumber(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:+91" + phoneNumber));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);

    }


    public class PolicemanViewHolder extends RecyclerView.ViewHolder {

        public ImageView policemanImage;
        public TextView policemanName, policemanRank, policemanArea, policemanPhone, policemanEmail;
        public TextView policemanRatingTV;
        public PolicemanViewHolder(@NonNull View itemView) {
            super(itemView);
            policemanImage = itemView.findViewById(R.id.policemanIV);
            policemanName = itemView.findViewById(R.id.policemanNameTV);
            policemanRank = itemView.findViewById(R.id.policemanDesignationTV);
            policemanArea = itemView.findViewById(R.id.policemanAreaTV);
            policemanPhone = itemView.findViewById(R.id.policemanPhoneTV);
            policemanEmail = itemView.findViewById(R.id.policemanEmailTV);
            policemanRatingTV = itemView.findViewById(R.id.policemanRatingTV);
        }
    }

}
