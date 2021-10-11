package com.example.tae;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
public class Profile extends AppCompatActivity {
    private ImageView home;
    private TextView uname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        home = findViewById(R.id.imageView26);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this, Home.class );
                startActivity(intent);
            }
        });
        uname=findViewById(R.id.textView11);
        Intent intent = getIntent();

        String name = intent.getStringExtra(Home.EXTRA_NAME);;
        uname.setText(name);
    }
}