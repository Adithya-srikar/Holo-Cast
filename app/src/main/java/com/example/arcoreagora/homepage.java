package com.example.arcoreagora;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class homepage extends AppCompatActivity {
    private Button consult;
    private Button nconsult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        consult=findViewById(R.id.consult);
        consult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(homepage.this,consult_sub.class);
                startActivity(intent);
            }
        });
        nconsult=findViewById(R.id.nconsult);
        nconsult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(homepage.this,nconsult_sub.class);
                startActivity(intent);
            }
        });
    }
}