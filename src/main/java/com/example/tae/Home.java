package com.example.tae;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


class ImageTags {
    String name;
    String lat;
    String longi;
    String points;

}

class TrueLocation {
    public static Double lat;
    public static Double lon;
}

public class Home extends AppCompatActivity implements LocationListener {
    private ImageView profile;
    private TextView welcometext;
    private Button add;
    private ImageView leaderboard;
    LocationManager locationManager;
    public static final String EXTRA_NAME = "com.example.tae.extra.NAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        profile = findViewById(R.id.imageView16);
        DBHelper db = new DBHelper(this);
        Intent intent = getIntent();
        String name = intent.getStringExtra(MainActivity.EXTRA_NAME);
        welcometext = findViewById(R.id.textView7);
        welcometext.setText("Welcome " + User.name);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                Intent intent = new Intent(Home.this, Profile.class);
                intent.putExtra(EXTRA_NAME, name);
                startActivity(intent);
            }
        });

        add = findViewById(R.id.button3);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, Add.class);
                intent.putExtra(EXTRA_NAME, name);
                startActivity(intent);


            }
        });
        leaderboard = findViewById(R.id.imageView17);
        LinearLayout l = findViewById(R.id.linear);
        int g = db.getGoalsCount();
        if (g != 0) {
            ArrayList<ArrayList<String>> res = db.getGoals();

            for (int i = 0; i < res.size(); i++) {
                ArrayList<String> r = res.get(i);
                if (r.get(0).equals("Delhi")) {
                    // Add onlickListener -> Toast.text - tag
                    //Add Tag
                    // Add Layout width match parent

                    ImageView iv = new ImageView(this);
                    iv.setImageResource(R.drawable.img_delhi);
//                    iv.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;

                    ImageTags it = new ImageTags();
                    it.name = r.get(0);
                    it.lat = r.get(1);
                    it.longi = r.get(2);
                    it.points = r.get(3);
                    iv.setTag(it);

                    iv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ImageTags t = (ImageTags) iv.getTag();
                             getLocation();

                            // now get the lat/lon from the Location and do something with it.


                             Double lon = TrueLocation.lon;
                            Double lat = TrueLocation.lat;
                            double cur_lat = Math.round(lat*100)/100;
                            double cur_lon = Math.round(lon*100)/100;
                            double img_lat = Math.round(Double.parseDouble(t.lat)*100)/100;
                            double img_lon = Math.round(Double.parseDouble(t.longi)*100)/100;
                             if(cur_lat == img_lat && cur_lon == img_lon) {
                                 boolean results = db.completeGoals(t.name);
                                if(results) {
                                    Toast.makeText(Home.this, "Good!",
                                            Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(Home.this, Camera.class);
                                    intent.putExtra(EXTRA_NAME, name);
                                    startActivity(intent);
                                }
                                else {
                                    Toast.makeText(Home.this, "Database Error!",
                                            Toast.LENGTH_SHORT).show();
                                }

                             }
                             else{
                                 Toast.makeText(Home.this, "Please check your Location",
                                         Toast.LENGTH_SHORT).show();
                             }





                        }
                    });

                    l.addView(iv);
                }
                if(r.get(0).equals("Marine Lines")) {
                    ImageView iv = new ImageView (this);
                    iv.setImageResource(R.drawable.img_marine);
//                    iv.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
                    ImageTags it = new ImageTags();
                    it.name = r.get(0);
                    it.lat = r.get(1);
                    it.longi = r.get(2);
                    it.points = r.get(3);
                    iv.setTag(it);

                    iv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ImageTags t = (ImageTags)iv.getTag();
//
                            Double lat = 28.60801;
                            Double lon = 77.33063;
//                             Double lon = TrueLocation.lon;
//                            Double lat = TrueLocation.lat;
                            double cur_lat = Math.round(lat*100)/100;
                            double cur_lon = Math.round(lon*100)/100;
                            double img_lat = Math.round(Double.parseDouble(t.lat)*100)/100;
                            double img_lon = Math.round(Double.parseDouble(t.longi)*100)/100;
                            if(cur_lat == img_lat && cur_lon == img_lon) {
                                boolean results = db.completeGoals(t.name);
                                if(results) {
                                    Toast.makeText(Home.this, "Good!",
                                            Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(Home.this, Camera.class);
                                    intent.putExtra(EXTRA_NAME, name);
                                    startActivity(intent);
                                }
                                else {
                                    Toast.makeText(Home.this, "Database Error!",
                                            Toast.LENGTH_SHORT).show();
                                }

                            }
                            else{
                                Toast.makeText(Home.this, "Please check your Location",
                                        Toast.LENGTH_SHORT).show();
                            }

//                            Intent intent = new Intent(Home.this, Camera.class);
//                            intent.putExtra(EXTRA_NAME, name);
//                            startActivity(intent);



                        }
                    });
                    l.addView(iv);
                }
                if(r.get(0).equals("Ooty")) {
                    ImageView iv = new ImageView (this);
                    iv.setImageResource(R.drawable.img_ooty);
//                    iv.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
                    ImageTags it = new ImageTags();
                    it.name = r.get(0);
                    it.lat = r.get(1);
                    it.longi = r.get(2);
                    it.points = r.get(3);
                    iv.setTag(it);

                    iv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ImageTags t = (ImageTags)iv.getTag();

                            Double lat = 11.4102;
                            Double lon = 72.8862522;
//                             Double lon = TrueLocation.lon;
//                            Double lat = TrueLocation.lat;
                            double cur_lat = Math.round(lat*100)/100;
                            double cur_lon = Math.round(lon*100)/100;
                            double img_lat = Math.round(Double.parseDouble(t.lat)*100)/100;
                            double img_lon = Math.round(Double.parseDouble(t.longi)*100)/100;
                            if(cur_lat == img_lat && cur_lon == img_lon) {
                                boolean results = db.completeGoals(t.name);
                                if(results) {
                                    Toast.makeText(Home.this, "Good!",
                                            Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(Home.this, Camera.class);
                                    intent.putExtra(EXTRA_NAME, name);
                                    startActivity(intent);
                                }
                                else {
                                    Toast.makeText(Home.this, "Database Error!",
                                            Toast.LENGTH_SHORT).show();
                                }

                            }
                            else{
                                Toast.makeText(Home.this, "Please check your Location",
                                        Toast.LENGTH_SHORT).show();
                            }


//                            Intent intent = new Intent(Home.this, Camera.class);
//                            intent.putExtra(EXTRA_NAME, name);
//                            startActivity(intent);



                        }
                    });
                    l.addView(iv);
                }
                if(r.get(0).equals("Mumbai")) {
                    ImageView iv = new ImageView (this);
                    iv.setImageResource(R.drawable.img_mumbai);
//                    iv.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
                    ImageTags it = new ImageTags();
                    it.name = r.get(0);
                    it.lat = r.get(1);
                    it.longi = r.get(2);
                    it.points = r.get(3);
                    iv.setTag(it);

                    iv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ImageTags t = (ImageTags)iv.getTag();
                            Double lat = 20.0490196;
                            Double lon = 75.8862522;
//                             Double lon = TrueLocation.lon;
//                            Double lat = TrueLocation.lat;
                            double cur_lat = Math.round(lat*100)/100;
                            double cur_lon = Math.round(lon*100)/100;
                            double img_lat = Math.round(Double.parseDouble(t.lat)*100)/100;
                            double img_lon = Math.round(Double.parseDouble(t.longi)*100)/100;
                            if(cur_lat == img_lat && cur_lon == img_lon) {
                                boolean results = db.completeGoals(t.name);
                                if(results) {
                                    Toast.makeText(Home.this, "Good!",
                                            Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(Home.this, Camera.class);
                                    intent.putExtra(EXTRA_NAME, name);
                                    startActivity(intent);
                                }
                                else {
                                    Toast.makeText(Home.this, "Database Error!",
                                            Toast.LENGTH_SHORT).show();
                                }

                            }
                            else{
                                Toast.makeText(Home.this, "Please check your Location",
                                        Toast.LENGTH_SHORT).show();
                            }

//                            Intent intent = new Intent(Home.this, Camera.class);
//                            intent.putExtra(EXTRA_NAME, name);
//                            startActivity(intent);



                        }
                    });
                    l.addView(iv);
                }

            }

        }
        else {

            TextView textView = new TextView(this);
            textView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            textView.setGravity(Gravity.CENTER);
            textView.setText("Please Add A New Destination Goals!");
        }


        leaderboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, Location.class);
                intent.putExtra(EXTRA_NAME, name);
                startActivity(intent);

                  //getLocation();


            }
        });



    }
    void getLocation() {
        try {
            if (ActivityCompat.checkSelfPermission(Home.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(Home.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(Home.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
                return;
            }else{
                // Write you code here if permission already given.
            }
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1, 5, this);
        }
        catch(SecurityException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLocationChanged(android.location.Location location) {
//        locationText.setText("Current Location: " + Location.getLatitude() + ", " + Location.getLongitude());
        Toast.makeText(Home.this, "Current Location: " + location.getLatitude() + ", " + location.getLongitude(), Toast.LENGTH_SHORT).show();
        TrueLocation.lat = location.getLatitude();
        TrueLocation.lon = location.getLongitude();
    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(Home.this, "Please Enable GPS and Internet", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

}