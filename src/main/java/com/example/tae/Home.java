package com.example.tae;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Home extends AppCompatActivity {
    private ImageView profile;
    private TextView welcometext;
    private Button add;
    private ImageView leaderboard;
    public static final String EXTRA_NAME = "com.example.tae.extra.NAME";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        profile = findViewById(R.id.imageView16);
        Intent intent = getIntent();
        String name=intent.getStringExtra(MainActivity.EXTRA_NAME);
        welcometext = findViewById(R.id.textView7);
        welcometext.setText("Welcome "+name);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                Intent intent = new Intent(Home.this, Profile.class );
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

        leaderboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, Camera.class);
                intent.putExtra(EXTRA_NAME, name);
                startActivity(intent);



            }
        });



    }
}