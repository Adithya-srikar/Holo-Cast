package com.example.arcoreagora;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Random;

public class otpgenerate extends AppCompatActivity {
    EditText ed1;
    Button nxt1;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpgenerate);
        ed1=findViewById(R.id.otpno);
        b1=findViewById(R.id.genbutton);
        nxt1=findViewById(R.id.nextone);
        nxt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(otpgenerate.this,ChannelActivity.class);
                startActivity(intent);
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               RandomString randomString=new RandomString();
               String result=randomString.generatealphabetnumber(6);
               ed1.setText(result);
            }
        });

    }
}