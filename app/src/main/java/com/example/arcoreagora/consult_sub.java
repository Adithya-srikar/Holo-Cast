package com.example.arcoreagora;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class consult_sub extends AppCompatActivity {
    EditText nametv,expert_typetv,lang_known,exp,phnno;
    Button genotp;
    Button BtnToast;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult_sub);
        nametv=findViewById(R.id.editTextTextPersonName);
        expert_typetv=findViewById(R.id.editTextTextPersonName2);
        lang_known=findViewById(R.id.editTextTextPersonName3);
        exp=findViewById(R.id.editTextTextPersonName4);
        phnno=findViewById(R.id.editTextPhone);



        BtnToast=findViewById(R.id.submit);
        BtnToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=nametv.getText().toString();
                String expert=expert_typetv.getText().toString();
                String lang=lang_known.getText().toString();
                String expi=exp.getText().toString();
                String phone= phnno.getText().toString();
                boolean check=validateinfo(name,expert,lang,expi,phone);
                if (check==true){

                    DB = new DBHelper(consult_sub.this);

                        Boolean checkinsertdata = DB.insertfirstdata(name,expert,lang,expi,phone);
                        if(checkinsertdata)
                            Toast.makeText(consult_sub.this,"DONE", Toast.LENGTH_SHORT).show();
                        else{
                            Toast.makeText(consult_sub.this,"NOT DONE", Toast.LENGTH_SHORT).show();
                        }


                }
                else{
                    Toast.makeText(consult_sub.this, "Sorry check information again", Toast.LENGTH_SHORT).show();
                }
            }
        });
        genotp=findViewById(R.id.genotp);
        genotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(consult_sub.this,otpgenerate.class);
                startActivity(intent);
            }
        });

    }
    private Boolean validateinfo(String name,String expert,String lang,String expi,String phone){
        if(name.length()==0){
            nametv.requestFocus();
            nametv.setError("FEILD CANNOT BE EMPTY");
            return false;
        }
        else if(!name.matches("[a-zA-Z]+")){
            nametv.requestFocus();
            nametv.setError("ONLY ALPHABETICAL CHARACERS");
            return false;
        }
        else if(phone.length()==0){
            phnno.requestFocus();
            phnno.setError("FEILD CANNOT BE EMPTY");
            return false;
        }
        else if(phone.length()!=10){
            phnno.requestFocus();
            phnno.setError("NO CANNOT BE GREATER THAN 10 DIGITS");
            return false;
        }
        else if(!phone.matches("^[6-9]\\d{9}$")){
            phnno.requestFocus();
            phnno.setError("ENTER VALID NO.");
            return false;
        }
        else if(expi.length()==0){
            exp.requestFocus();
            exp.setError("FEILD CANNOT BE EMPTY");
            return false;
        }

        else if(lang.length()==0) {
            lang_known.requestFocus();
            lang_known.setError("FEILD CANNOT BE EMPTY");
            return false;
        }
        else if(expert.length()==0) {
            expert_typetv.requestFocus();
            expert_typetv.setError("FEILD CANNOT BE EMPTY");
            return false;
        }
        else{
            return true;
        }

    }
}
