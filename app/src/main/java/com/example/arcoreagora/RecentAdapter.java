package com.example.arcoreagora;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.arcoreagora.models.Recent;

import java.util.ArrayList;
import java.util.List;

public class RecentAdapter extends RecyclerView.Adapter<RecentAdapter.RecentViewHolder> {

    private List<Recent> recentItems = new ArrayList<>();
    private Context context;

    // Constructor to initialize with data
    public RecentAdapter(List<Recent> recentItems) {
        this.context=context;
        this.recentItems = recentItems;
    }

    @NonNull
    @Override
    public RecentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recent_item_layout, parent, false); // Use your item layout
        return new RecentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecentViewHolder holder, int position) {
        Recent currentItem = recentItems.get(position);
        if (currentItem != null) {
            holder.channelNameTextView.setText(currentItem.channel != null ? currentItem.channel : "");
            holder.joinedDateTimeTextView.setText(currentItem.joinedDateTime != null ? currentItem.joinedDateTime : "");
        }
    }



    @Override
    public int getItemCount() {
        return recentItems.size();
    }

    // ViewHolder class
    static class RecentViewHolder extends RecyclerView.ViewHolder {
        TextView channelNameTextView;
        TextView joinedDateTimeTextView;

        public RecentViewHolder(@NonNull View itemView) {
            super(itemView);
            channelNameTextView = itemView.findViewById(R.id.channel_name_text_view); // IDs from your item layout
            joinedDateTimeTextView = itemView.findViewById(R.id.joined_date_time_text_view);
        }
    }
}