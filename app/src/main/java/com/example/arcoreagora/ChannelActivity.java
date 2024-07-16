package com.example.arcoreagora;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ChannelActivity extends AppCompatActivity {
    private EditText channelEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel);
        channelEditText = findViewById(R.id.channel_edit_text);
//        Intent intent3 = new Intent();
//        String model_location = intent3.getStringExtra("model_location");
//        EditText model;
//        model = findViewById(R.id.modelname);
//        model.setText("sup");

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
            switch (view.getId()) {
                case R.id.btn_join_audience:
                    Intent intent = new Intent(this, AgoraARAudienceActivity.class);
                    intent.putExtra("ChannelName", channelName);
                    startActivity(intent);
                    break;
                case R.id.btn_join_streamer:
                    Intent intent2 = new Intent(this, AgoraARStreamerActivity.class);
//                    String model_location = intent2.getStringExtra("model_location");
//                    String texture_location = intent2.getStringExtra("texture_location");
                    intent2.putExtra("ChannelName", channelName);
//                    intent2.putExtra("Model", model_location);
//                    intent2.putExtra("Texture", texture_location);
                    startActivity(intent2);
                    break;
            }
        }

    }

}
