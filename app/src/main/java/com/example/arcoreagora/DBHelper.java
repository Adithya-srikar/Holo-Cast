package com.example.arcoreagora;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
;
public class DBHelper extends SQLiteOpenHelper {
    public DBHelper( Context context) {
        super(context, "Experts.db", null, 1);

    }
    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("CREATE TABLE Experts (name TEXT primary key,expert TEXT,lang TEXT,expi TEXT,phone TEXT)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists Experts");
    }

    public Boolean insertfirstdata(String name,String expert,String lang,String expi,String phone)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("expert",expert);
        contentValues.put("lang",lang);
        contentValues.put("expi",expi);
        contentValues.put("phone",phone);
        long result=DB.insert("Experts", "0", contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }

    }
    public Cursor getdata ()
    {
        Cursor cursor = null;

            SQLiteDatabase DB = this.getWritableDatabase();
            cursor = DB.rawQuery("Select * from Experts", null);
        return cursor;
    }


}
