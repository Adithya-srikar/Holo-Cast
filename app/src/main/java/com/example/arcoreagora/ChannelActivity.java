package com.example.arcoreagora;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.arcoreagora.models.Recent;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;

public class ChannelActivity extends AppCompatActivity {
    private EditText channelEditText;
    private DatabaseReference recentChannelsRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel);
        channelEditText = findViewById(R.id.channel_edit_text);
        FirebaseDatabase database = FirebaseDatabase.getInstance();

    }

    /**
     * when user clicks the join channel button
     * @param view
     */
    public void onJoinButtonClicked(View view){
        String channelName = channelEditText.getText().toString();

        if (channelName == null) {

            Toast.makeText(this, "Channel Name cannot be empty", Toast.LENGTH_SHORT).show();
        }else {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
            String currentDateTime = dateFormat.format(new Date());
            recentChannelsRef.push().setValue(channelName);
            switch (view.getId()) {
                case R.id.btn_join_audience:
                    Intent intent = new Intent(this, AgoraARAudienceActivity.class);
                    intent.putExtra("ChannelName", channelName);
                    startActivity(intent);
                    break;
                case R.id.btn_join_streamer:
                    Intent intent2 = new Intent(this, AgoraARStreamerActivity.class);
                    String model_name = intent2.getStringExtra("model_name");
                    intent2.putExtra("ChannelName", channelName);
                    intent2.putExtra("mn", model_name);
                   startActivity(intent2);
                    break;
            }
        }

    }

}
