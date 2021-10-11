package com.example.tae;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "Database.db";

    public DBHelper(Context context) {
        super(context, "Database.db", null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase Mydb) {
        Mydb.execSQL("Create table users(emailid VarChar primary key, password VarChar)");
        //Mydb.execSQL("Create table posts(postid Int primary key AUTOINCREMENT, location Text, Dateofgoing Date, Coins Int, emailid Varchar, FOREIGN KEY (emailid) REFERENCES users(emailid) )");
        // POST ->(primary key, email-foreign, post-image url
    }

    @Override
    public void onUpgrade(SQLiteDatabase Mydb, int oldVersion, int newVersion) {
        Mydb.execSQL("Drop table if exists users");
    }

    public boolean insertData(String emailid, String password){
        SQLiteDatabase Mydb = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("emailid", emailid);
        contentValues.put("password", password);
        long result = Mydb.insert("users", null,contentValues );
        if(result==-1) return false;
        else
            return true;
    }

    public boolean checkuserexists(String emailid){
        SQLiteDatabase Mydb = this.getWritableDatabase();
        Cursor cursor = Mydb.rawQuery("Select * from users where emailid = ?", new String[] {emailid});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public boolean loginfunction(String emailid, String password){
        SQLiteDatabase Mydb = this.getWritableDatabase();
        Cursor cursor = Mydb.rawQuery("Select * from users where emailid = ? and password = ?", new String[] {emailid, password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public boolean addpost(String emailid, String location, String date, Integer coins ){
        SQLiteDatabase Mydb = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("emailid", emailid);
        contentValues.put("location", location);
        contentValues.put("date", date);
        contentValues.put("coins", coins);
        long result = Mydb.insert("posts", null,contentValues );
        if(result==-1) return false;
        else
            return true;
    }
}
