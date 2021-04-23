package com.example.tourgk.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tourgk.ListTour;
import com.example.tourgk.MainActivity;
import com.example.tourgk.R;
import com.example.tourgk.TourDetail;
import com.example.tourgk.api.ApiClient;
import com.example.tourgk.model.Tour;

import java.util.List;

public class TourFullListAdapter extends RecyclerView.Adapter<TourFullListAdapter.TourFullListViewHolder> {
    Context context;
    List<Tour> tourList;

    public TourFullListAdapter(Context context, List<Tour> tourList) {
        this.context = context;
        this.tourList = tourList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TourFullListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tour_item, parent, false);
        return new TourFullListAdapter.TourFullListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TourFullListViewHolder holder, int position) {
        Tour tour = tourList.get(position);
        holder.tvTourName.setText(tour.getName());
        holder.tvLocation.setText(tour.getLocation());
        holder.tvPrice.setText(String.valueOf(tour.getPrice())+" vnd");
        Glide.with(context).load(ApiClient.BASE_URL + "template/upload/tour/"
                + tour.getImageCover()).into(holder.imgTour);
        holder.tvDuration.setText(String.valueOf(tour.getDuration())+" ngày");
        holder.tvStartDate.setText(tour.getStartDate());

        int curGS = tour.getCurrentGroupSize();
        int maxGS = tour.getMaxGroupSize();
        holder.tvGroupSize.setText(String.valueOf(curGS) + "/" + String.valueOf(maxGS));
        String status = "Còn vé";
        int color = Color.GREEN;
        if (curGS == maxGS) {
            status = "Hết vé";
            color = Color.RED;
        }
        holder.tvStatus.setText(status);
        holder.tvStatus.setTextColor(color);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    ((ListTour)context).chuyenTrangDetail(tour.getId());
                }
                catch (Exception e){
                    ((MainActivity)context).chuyenTrangDetail(tour.getId());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (tourList == null) {
            return 0;
        }
        return tourList.size();
    }

    public static final class TourFullListViewHolder extends RecyclerView.ViewHolder {
        ImageView imgTour;
        TextView tvTourName, tvLocation, tvPrice, tvDuration, tvStartDate, tvStatus, tvGroupSize;

        public TourFullListViewHolder(@NonNull View itemView) {
            super(itemView);

            imgTour = itemView.findViewById(R.id.imgTour);
            tvTourName = itemView.findViewById(R.id.tvName);
            tvLocation = itemView.findViewById(R.id.tvLocation);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvDuration = itemView.findViewById(R.id.tvDuration);
            tvStartDate = itemView.findViewById(R.id.tvStartDate);
            tvStatus = itemView.findViewById(R.id.tvStatus);
            tvGroupSize = itemView.findViewById(R.id.tvGroupSize);
        }
    }
}
