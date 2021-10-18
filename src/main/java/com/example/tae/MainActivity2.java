package com.example.tae;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {
    private Button login;
    private Button signup;
    private EditText username;
    private EditText password;
    private EditText email;
    DBHelper db;
    String emailid;
    String userpassword;
    String user_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        username = findViewById(R.id.idUsername);
        password = findViewById(R.id.editTextTextPassword);
        email = findViewById(R.id.idEmail);
        db = new DBHelper(this);

        login = findViewById(R.id.button4);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity2.this, MainActivity.class );
                startActivity(intent);

            }
        });
        signup = findViewById(R.id.button2);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Toast.makeText(MainActivity2.this, "Insertion successful", Toast.LENGTH_SHORT).show();
                //Intent intent = new Intent(MainActivity2.this, Home.class );
                //startActivity(intent);
                emailid = email.getText().toString();
                user_name = username.getText().toString();
                userpassword = password.getText().toString();
                if(emailid.equals("")||userpassword.equals("")||user_name.equals(""))
                    Toast.makeText(MainActivity2.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuser = db.checkuserexists(emailid);
                    if(checkuser==false){
                        Boolean insert = db.insertData(emailid,user_name, userpassword);
                        if(insert==true)
                            Toast.makeText(MainActivity2.this, "Registration successful", Toast.LENGTH_SHORT).show();
                        else{
                            Toast.makeText(MainActivity2.this, "Registration unsuccessful", Toast.LENGTH_SHORT).show();

                        }
                    }
                    else{
                        Toast.makeText(MainActivity2.this, "User already exists", Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });
    }
}