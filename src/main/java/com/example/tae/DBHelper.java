package com.example.tae;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "Database.db";

    public DBHelper(Context context) {
        super(context, "Database.db", null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase Mydb) {
        //System.out.println(Mydb.isOpen());
        Mydb.execSQL("Create table users(emailid VarChar primary key, password VarChar, username VarChar, points Integer)");

        Mydb.execSQL("Create table destination(name VarChar, lat varchar, long VarChar, points VarChar, image VarChar)");

        String[] name = new String[] {
                "Delhi", "Marine Lines", "Ooty", "Mumbai"
        };

        String[] lat = new String[] {
                "28.607912", "69.6969", "11.4102", "19.0490196"
        };

        String[] longi = new String[] {
                "77.331184", "19.24708", "76.6950", "72.8862522"
        };

        String[] points = new String[] {
                "1500", "1650", "1800", "1000"
        };

        String[] image_src = new String[] {
                "img_delhi.png", "img_marine.png", "3.png", "img_mumbai.png"
        };
//        SQLiteDatabase Mydb_insert = this.getWritableDatabase();
        for (int i=0; i<=3; i++) {

            ContentValues contentValues = new ContentValues();
            contentValues.put("name", name[i]);
            contentValues.put("lat", lat[i]);
            contentValues.put("long", longi[i]);
            contentValues.put("points", points[i]);
            contentValues.put("image", image_src[i]);
            long result = Mydb.insert("destination", null,contentValues );
//            System.out.println(result);
        }
        Mydb.execSQL("Create table visited(emailid VarChar, destination VarChar, status VarChar)");






        //Mydb.execSQL("Create table posts(postid Int primary key AUTOINCREMENT, Location Text, Dateofgoing Date, Coins Int, emailid Varchar, FOREIGN KEY (emailid) REFERENCES users(emailid) )");
        // POST ->(primary key, email-foreign, post-image url
    }

    @Override
    public void onUpgrade(SQLiteDatabase Mydb, int oldVersion, int newVersion) {
        Mydb.execSQL("Drop table if exists users");
    }

    public boolean insertData(String emailid,String username, String password){
        SQLiteDatabase Mydb = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("emailid", emailid);
        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("points", 0);
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
        // session type variable which will save username in some string
        SQLiteDatabase Mydb = this.getWritableDatabase();
        Cursor cursor = Mydb.rawQuery("Select * from users where emailid = ? and password = ?", new String[] {emailid, password});
        if(cursor.getCount()>0) {
            cursor.moveToFirst();
            User.email = emailid;
            User.name = cursor.getString(2);
            return true;
        }
        else {
            return false;
        }
            
    }

    public boolean addpost(String emailid, String location, String date, Integer coins ){
        SQLiteDatabase Mydb = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("emailid", emailid);
        contentValues.put("Location", location);
        contentValues.put("date", date);
        contentValues.put("coins", coins);
        long result = Mydb.insert("posts", null,contentValues );
        if(result==-1) return false;
        else
            return true;
    }

    public ArrayList<ArrayList<String>> getDestinationAll() {
        SQLiteDatabase Mydb_get = this.getWritableDatabase();
        Cursor cursor = Mydb_get.rawQuery("SELECT * from destination",null);
        ArrayList<ArrayList<String>> res = new ArrayList<ArrayList<String>>();

        if(cursor.moveToFirst()){
            do{
                ArrayList<String> ar = new ArrayList<>();
                String name = cursor.getString(0);
                String lat = cursor.getString(1);
                String longi = cursor.getString(2);
                String points = cursor.getString(3);
                String image = cursor.getString(4);
                ar.add(name);
                ar.add(lat);
                ar.add(longi);
                ar.add(points);
                ar.add(image);
                res.add(ar);
            }while(cursor.moveToNext());
        }


//        for(ArrayList<String> ar:res){
//            for(String ch:ar){
//                System.out.println(ch);
//            }
//        }
        return  res;

    }

    public boolean addDestinationGoal(String place) {
        SQLiteDatabase Mydb = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("emailid", User.email);
        contentValues.put("destination", place);
        contentValues.put("status", 0);

        long result = Mydb.insert("visited", null,contentValues );
        if(result == -1) {
            return false;
        }
        return true;
    }

    public int getGoalsCount() {
        SQLiteDatabase Mydb = this.getWritableDatabase();
        Cursor cursor = Mydb.rawQuery("Select * from visited where emailid = ?", new String[] {User.email});
        return  cursor.getCount();

    }

    public ArrayList<ArrayList<String>> getGoals() {
        SQLiteDatabase Mydb = this.getWritableDatabase();
        Cursor cursor = Mydb.rawQuery("Select * from visited where emailid = ? and status = 0", new String[] {User.email});
        ArrayList<String> dest = new ArrayList<>();
        if(cursor.moveToFirst()) {
            do {
                dest.add(cursor.getString(1));
            }while(cursor.moveToNext());
        }
        ArrayList<ArrayList<String>> res = new ArrayList<ArrayList<String>>();
        for(String entry:dest){
            Cursor cursor_new = Mydb.rawQuery("Select * from destination where name = ?", new String[] {entry});
            cursor_new.moveToFirst();
            ArrayList<String> temp = new ArrayList<>();
            temp.add(cursor_new.getString(0));
            temp.add(cursor_new.getString(1));
            temp.add(cursor_new.getString(2));
            temp.add(cursor_new.getString(3));
            temp.add(cursor_new.getString(4));
            res.add(temp);

        }
        return res;

    }

    public ArrayList<ArrayList<String>> getGoalsCompleted() {
        SQLiteDatabase Mydb = this.getWritableDatabase();
        Cursor cursor = Mydb.rawQuery("Select * from visited where emailid = ? and status = 1", new String[] {User.email});
        ArrayList<String> dest = new ArrayList<>();
        if(cursor.moveToFirst()) {
            do {
                dest.add(cursor.getString(1));
            }while(cursor.moveToNext());
        }
        ArrayList<ArrayList<String>> res = new ArrayList<ArrayList<String>>();
        for(String entry:dest){
            Cursor cursor_new = Mydb.rawQuery("Select * from destination where name = ?", new String[] {entry});
            cursor_new.moveToFirst();
            ArrayList<String> temp = new ArrayList<>();
            temp.add(cursor_new.getString(0));
            temp.add(cursor_new.getString(1));
            temp.add(cursor_new.getString(2));
            temp.add(cursor_new.getString(3));
            temp.add(cursor_new.getString(4));
            res.add(temp);

        }
        return res;

    }
    public int getPoints() {
        SQLiteDatabase Mydb = this.getWritableDatabase();
        Cursor cursor = Mydb.rawQuery("Select * from users where emailid = ? ", new String[] {User.email});
        if(cursor.moveToFirst()) {
            return cursor.getInt(3);
        }
        return 0;
    }
    public boolean setPoints(int Points) {
        SQLiteDatabase Mydb = this.getWritableDatabase();
        int points = 0;
        Cursor cursor = Mydb.rawQuery("Select * from users where emailid = ? ", new String[] {User.email});
        if(cursor.moveToFirst()) {
            points = cursor.getInt(2) + Points;

        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("points", points);


        long result = Mydb.update("users",contentValues,"emailid = ?",new String[] {User.email} );
        if(result==-1) return false;
        else
            return true;


    }

    public boolean completeGoals(String place) {
        SQLiteDatabase Mydb = this.getWritableDatabase();
        Cursor cursor = Mydb.rawQuery("Select * from destination where name = ? ", new String[] {place});
        if(cursor.moveToFirst()) {
            int points = cursor.getInt(3);
            if(setPoints(points)) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("status", 1);


                long result = Mydb.update("visited",contentValues,"emailid = ? AND destination = ? ",new String[] {User.email, place} );
                if(result==-1) return false;
                else
                    return true;
            }
        }
        return false;
    }


    // change status
    //get visisted list


}
