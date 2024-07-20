package com.example.arcoreagora;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.arcoreagora.models.Recent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;


import androidx.appcompat.app.AppCompatActivity;


public class student_main extends AppCompatActivity {
    Button button2 ;
    private RecyclerView galleryRecyclerView;
    private RecentAdapter recentAdapter;
    private List<Recent> recentItems = new ArrayList<Recent>();
    private DatabaseReference recentChannelsRef;
    TextView emptyListText;
// Declare the button

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_main);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        recentChannelsRef = database.getReference("Recent"); //Or your desired path
        emptyListText = findViewById(R.id.empty_list_text);
        button2 = findViewById(R.id.join_class);


        button2.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(student_main.this, ChannelActivity.class);
                startActivity(intent);
            }
        });
        galleryRecyclerView = findViewById(R.id.gallery_recycler_view);
        galleryRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        recentAdapter = new RecentAdapter(recentItems);
        galleryRecyclerView.setAdapter(recentAdapter);
        fetchRecentChannels();
    }

    private void fetchRecentChannels() {
        recentChannelsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                recentItems.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Recent recentItem = snapshot.getValue(Recent.class);
                    if (recentItem != null) {
                        recentItems.add(recentItem);
                    }
                }
                emptyListText.setVisibility(recentItems.isEmpty() ? View.VISIBLE : View.GONE);
                recentAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle errors
            }
        });
    }
}


