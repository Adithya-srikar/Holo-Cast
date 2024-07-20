package com.example.arcoreagora.adapters;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

//import com.example.arcoreagora.ArActivity;
import com.example.arcoreagora.AgoraARStreamerActivity;
import com.example.arcoreagora.ChannelActivity;
import com.example.arcoreagora.R;
import com.example.arcoreagora.models.GalleryListItem;

import java.util.List;

public class GalleryListAdapter extends RecyclerView.Adapter<GalleryListAdapter.ListItemViewHolder>  {
    private List<GalleryListItem> data;
    private Context context;

    public GalleryListAdapter(List<GalleryListItem> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public ListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.gallery_list_item, parent, false);
        return new ListItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListItemViewHolder holder, int position) {
        final GalleryListItem currentData = data.get(position);
        holder.imageView.setImageURI(null);
        holder.imageView.setImageURI(currentData.thumbnailUri);
        holder.nameTextView.setText(currentData.model_name);


        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ChannelActivity.class);
                intent.putExtra("model_location", currentData.modelLocation);
                intent.putExtra("texture_location", currentData.textureLocation);
                intent.putExtra("model_name", currentData.model_name);
                SharedPreferences sharedPref = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("model_location", currentData.modelLocation);
                editor.putString("texture_location", currentData.textureLocation);
                editor.putString("model_name", currentData.model_name);
                editor.apply();
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ListItemViewHolder extends RecyclerView.ViewHolder {
        View view;
        ImageView imageView;
        TextView nameTextView;

        public ListItemViewHolder(View view) {
            super(view);
            this.view = view;
            imageView = view.findViewById(R.id.list_item_image);
            nameTextView = view.findViewById(R.id.list_item_name);
        }
    }

}
