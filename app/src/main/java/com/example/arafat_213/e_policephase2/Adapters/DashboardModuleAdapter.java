package com.example.arafat_213.e_policephase2.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.arafat_213.e_policephase2.Models.DashboardModule;
import com.example.arafat_213.e_policephase2.R;
import com.example.arafat_213.e_policephase2.activities.ComplaintActivity;
import com.example.arafat_213.e_policephase2.activities.FeedbackActivity;
import com.example.arafat_213.e_policephase2.activities.NotificationActivity;
import com.example.arafat_213.e_policephase2.activities.PolicemenListActivity;
import com.example.arafat_213.e_policephase2.activities.RequestPatrollingActivity;

import java.util.ArrayList;

public class DashboardModuleAdapter extends RecyclerView.Adapter<DashboardModuleAdapter.DashboardModuleViewHolder> {

    private ArrayList<DashboardModule> dashboardModuleArrayList;
    private Context mContext;

    public DashboardModuleAdapter(ArrayList<DashboardModule> dashboardModuleArrayList) {
        this.dashboardModuleArrayList = dashboardModuleArrayList;
    }

    @NonNull
    @Override
    public DashboardModuleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.item_dashboard_module, viewGroup, false);
        return new DashboardModuleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final DashboardModuleViewHolder dashboardModuleViewHolder, int i) {
        dashboardModuleViewHolder.icon.setImageResource(dashboardModuleArrayList.get(i).getIcon());
        dashboardModuleViewHolder.name.setText(dashboardModuleArrayList.get(i).getName());

        dashboardModuleViewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = dashboardModuleViewHolder.getAdapterPosition();
                mContext = view.getContext();
                Intent intent;
                switch (id) {
                    case 0:
                        intent = new Intent(view.getContext(), PolicemenListActivity.class);
                        view.getContext().startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(view.getContext(), NotificationActivity.class);
                        view.getContext().startActivity(intent);
                        break;
                    case 2:
                        intent = new Intent(view.getContext(), ComplaintActivity.class);
                        view.getContext().startActivity(intent);
                        break;
                    case 3:
                        intent = new Intent(view.getContext(), RequestPatrollingActivity.class);
                        view.getContext().startActivity(intent);
                        break;
                    case 4:
                        intent = new Intent(view.getContext(), FeedbackActivity.class);
                        view.getContext().startActivity(intent);
                        break;
                    case 5:
                        break;
                    case 6:
                        break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return dashboardModuleArrayList.size();
    }

    public class DashboardModuleViewHolder extends RecyclerView.ViewHolder {

        public ImageView icon;
        public TextView name;
        public RelativeLayout parentLayout;


        public DashboardModuleViewHolder(@NonNull View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.icon);
            name = itemView.findViewById(R.id.name);
            parentLayout = itemView.findViewById(R.id.parentLayout);
        }
    }
}
