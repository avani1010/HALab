package com.example.sqlite;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

class DBHelper extends SQLiteOpenHelper {
    public DBHelper(@Nullable Context context){
        super(context,"User.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table Userdata(name TEXT primary key, contact TEXT, age TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists Userdata");
    }
    public boolean insert(String name, String contact, String age){
        ContentValues values = new ContentValues() ;
        SQLiteDatabase db = getWritableDatabase() ;
        values.put("name",name);
        values.put("contact",contact);
        values.put("age",age);
        long status = db.insert("Userdata",null,values) ;
        if(status==-1)
            return false;
        else return true ;

    }
    public boolean delete(String name){
        SQLiteDatabase db = getWritableDatabase() ;
        Cursor cursor = db.rawQuery("Select * from Userdata where name = ?",new String[]{name}) ;
        if(cursor.getCount()>0){
            long result = db.delete("Userdata","name=?",new String[]{name}) ;
            if(result==-1)
                return false ;
            else return true ;
        }
        else return false ;
    }
    public boolean update(String name, String contact, String age){
        SQLiteDatabase db =  getWritableDatabase() ;
        ContentValues values = new ContentValues() ;
        values.put("contact",contact); ;
        values.put("age",age) ;
        Cursor cursor = db.rawQuery("Select * from Userdata where name = ?",new String[]{name}) ;
        if(cursor.getCount()>0){
            long result = db.update("Userdata",values,"name=?",new String[]{name}) ;
            if(result==-1) return false ;
            else return true ;

        }
        else return false ;
    }
    public Cursor viewData(){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from Userdata",null) ;
        return cursor ;
    }

}