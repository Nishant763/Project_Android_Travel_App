package com.example.tae;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class Profile extends AppCompatActivity {
    private ImageView home;
    private TextView uname;
    private TextView points;
    private LinearLayout l ;

    DBHelper db = new DBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        home = findViewById(R.id.imageView26);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this, Home.class);
                startActivity(intent);
            }
        });
        uname = findViewById(R.id.name);
        l =  findViewById(R.id.profileLinear);
        points = findViewById(R.id.points);
        uname.setText(User.name);
        points.setText(""+db.getPoints());
        int g = db.getGoalsCount();
        if (g != 0) {
            ArrayList<ArrayList<String>> res = db.getGoalsCompleted();

            for (int i = 0; i < res.size(); i++) {
                ArrayList<String> r = res.get(i);
                if (r.get(0).equals("Delhi")) {
                    ImageView iv = new ImageView(this);
                    iv.setImageResource(R.drawable.img_delhi);
                    l.addView(iv);
                }
                if (r.get(0).equals("Ooty")) {
                    ImageView iv = new ImageView(this);
                    iv.setImageResource(R.drawable.img_ooty);
                    l.addView(iv);
                }
                if (r.get(0).equals("Marine Lines")) {
                    ImageView iv = new ImageView(this);
                    iv.setImageResource(R.drawable.img_marine);
                    l.addView(iv);
                }
                if (r.get(0).equals("Mumbai")) {
                    ImageView iv = new ImageView(this);
                    iv.setImageResource(R.drawable.img_mumbai);
                    l.addView(iv);
                }
            }


        }
    }
}