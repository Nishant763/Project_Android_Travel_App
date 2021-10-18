package com.example.tae;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Add extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        DBHelper db;
        db = new DBHelper(this);
        Button btn;


//        SQLiteDatabase sq = SQLiteDatabase.openDatabase("/data/data/com.example.test/databases/Database.db",null,0);
//        db.onCreate(sq);
        ArrayList<ArrayList<String>> res = db.getDestinationAll();


        Spinner s = (Spinner) findViewById(R.id.spinner);

        ArrayList<String> dest =new ArrayList<>();
        for(int i=0; i<4; i++) {
            ArrayList<String> record = res.get(i);
            String dest_name = record.get(0);
            dest.add(dest_name);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, dest);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(adapter);
        ImageView img;
        img = findViewById(R.id.location);
        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int position, long id) {
                Object item = adapterView.getItemAtPosition(position);
                if (item != null) {
                    String name = item.toString();


                    if(name.equals( "Delhi"))
                        img.setImageResource(R.drawable.img_delhi);
                    if(name.equals( "Marine Lines")) {
                        img.setImageResource(R.drawable.img_marine);
                    }
                    if(name.equals( "Ooty")) {
                        img.setImageResource(R.drawable.img_ooty);
                    }
                    if(name.equals( "Mumbai")) {
                        img.setImageResource(R.drawable.img_mumbai);
                    }

                    Toast.makeText(Add.this, name,
                            Toast.LENGTH_SHORT).show();
                }
//                Toast.makeText(Add.this, "Selected",
//                        Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // TODO Auto-generated method stub

            }
        });

        btn = findViewById(R.id.button5);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String text = s.getSelectedItem().toString();
                boolean check = db.addDestinationGoal(text);
                if(check) {
                    Toast.makeText(Add.this, "Succesfully Added your New Goal!!",
                            Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(Add.this, "Something went Wrong!!",
                            Toast.LENGTH_SHORT).show();
                }



            }
        });

        // spinner
        // datetime
        //submit button
    }


}