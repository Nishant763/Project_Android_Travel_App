package com.example.tae;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button login;
    private EditText username;
    private EditText password;
    private Button signup;

    DBHelper db;
   // User user;

    public static final String EXTRA_NAME = "com.example.tae.extra.NAME";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login = findViewById(R.id.button2);
        username = findViewById(R.id.idUsername);
        password = findViewById(R.id.editTextTextPassword);
        db = new DBHelper(this);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uname = username.getText().toString();
                String passwd = password.getText().toString();
                if(uname.equals("")||passwd.equals(""))
                    Toast.makeText(MainActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    Boolean login = db.loginfunction(uname, passwd);
                    if(login==true){
                        Toast.makeText(MainActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, Home.class );
                        intent.putExtra(EXTRA_NAME, uname);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(MainActivity.this, "Bhai galat dala hai sab", Toast.LENGTH_SHORT).show();
                    }
                }
                /*if(uname == "zeelmehta" && passwd == "19072908"){
                    //Toast.makeText(context: MainActivity.this, text : "Login successful", Toast.LENGTH_SHORT).show();
                    Toast.makeText(MainActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "Please check credentials", Toast.LENGTH_SHORT).show();
                }*/

            }
        });
        signup = findViewById(R.id.button4);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class );
                startActivity(intent);
            }
        });
    }
}