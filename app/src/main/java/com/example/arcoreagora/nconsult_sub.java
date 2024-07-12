package com.example.arcoreagora;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class nconsult_sub extends AppCompatActivity {
    Button btn;
    Button nxt;
    DBHelper DB;
    TextView namings;
    TextView expertings;
    TextView phonings;
    TextView langings;
    TextView expings;



    public List<String> NAME = new ArrayList<String>();
    List<String> EXPERT = new ArrayList<String>();
    List<String> LANG = new ArrayList<String>();
    List<String> EXPI = new ArrayList<String>();
    List<String> PHONE = new ArrayList<String>();
    @SuppressLint("MissingInflatedId")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nconsult_sub);
        btn=findViewById(R.id.demo);

        namings=findViewById(R.id.nameing);
        expertings=findViewById(R.id.experting);
        phonings=findViewById(R.id.phoning);
        langings=findViewById(R.id.langing);
        expings=findViewById(R.id.exping);

        nxt=findViewById(R.id.next);
        nxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(nconsult_sub.this,Upload.class);
                startActivity(intent);
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertDialog();
            }
        });

        DB = new DBHelper(nconsult_sub.this);
        Cursor expert_data = DB.getdata();
        while(expert_data.moveToNext()){
            if(expert_data.getString(0)!=null)
            {
                NAME.add(expert_data.getString(0));
                EXPERT.add(expert_data.getString(1));
                LANG.add(expert_data.getString(2));
                EXPI.add(expert_data.getString(3));
                PHONE.add(expert_data.getString(4));
            }

        }


    }
    private void showAlertDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(nconsult_sub.this);
        alertDialog.setTitle("AlertDialog");
        alertDialog.setItems(NAME.toArray(new String[0]), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int choice = which+0;
                namings.setText(NAME.get(choice));
                expertings.setText(EXPERT.get(choice));
                phonings.setText(PHONE.get(choice));
                langings.setText(LANG.get(choice));
                expings.setText(EXPI.get(choice));
                }

        });
        AlertDialog alert = alertDialog.create();
        alert.setCanceledOnTouchOutside(false);
        alert.show();
    }
}