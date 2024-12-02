package com.example.dictonary;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class Sqliteclass extends SQLiteAssetHelper {

    public Sqliteclass(Context context) {
        super(context, "dictionary.db", null,1);
    }

    public Cursor cursordata(){
       SQLiteDatabase db=this.getReadableDatabase();
       Cursor cursor= db.rawQuery("select * from Dictionary",null);
       return cursor;
    }

    public Cursor searchdata(String key) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Dictionary WHERE word LIKE ?", new String[]{key + "%"});
        return cursor;
    }





}
