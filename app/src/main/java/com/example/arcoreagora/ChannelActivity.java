package com.example.arcoreagora;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.arcoreagora.models.Recent;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ChannelActivity extends AppCompatActivity {
    private EditText channelEditText;
    private DatabaseReference recentChannelsRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel);
        channelEditText = findViewById(R.id.channel_edit_text);

        // Initialize Firebase database reference
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        recentChannelsRef = database.getReference("Recent"); // Or your desired path
    }

    public void onJoinButtonClicked(View view) {
        String channelName = channelEditText.getText().toString();

        if (channelName.isEmpty()) {
            Toast.makeText(this, "Channel Name cannot be empty", Toast.LENGTH_SHORT).show();
        } else {
            // Get current time and date
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
            String currentDateTime = dateFormat.format(new Date());
            String username = getCurrentUsername(); // Implement this method
            Recent recentChannel = new Recent(username, channelName, currentDateTime);
            recentChannelsRef.push().setValue(recentChannel); // Push the object

            // Start the appropriate activity based on button click
            switch (view.getId()) {
                case R.id.btn_join_audience:
                    Intent intent = new Intent(this, AgoraARAudienceActivity.class);
                    intent.putExtra("ChannelName", channelName);
                    startActivity(intent);
                    break;
                case R.id.btn_join_streamer:
                    Intent intent2 = new Intent(this, AgoraARStreamerActivity.class);
                    String model_name = intent2.getStringExtra("model_name"); // Make sure this extra is set correctly
                    intent2.putExtra("ChannelName", channelName);
                    intent2.putExtra("mn", model_name);
                    startActivity(intent2);
                    break;
            }
        }
    }

    private String getCurrentUsername() {
        SharedPreferences prefs = getSharedPreferences("user_data", MODE_PRIVATE);
        return prefs.getString("username", "");
    }
}