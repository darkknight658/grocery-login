package com.example.signuplogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class signup extends AppCompatActivity {

    public EditText signupgmail, password;
    public Button signupbutton;
    public TextView loginalready;
    DB DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signupgmail = findViewById(R.id.signup);
        password = findViewById(R.id.signuppassword);
        signupbutton = findViewById(R.id.signupbutton);
        loginalready = findViewById(R.id.loginalready);
        DB = new DB(this);

        signupbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = signupgmail.getText().toString().trim();
                String pass = password.getText().toString().trim();

                if (user.equals("") || pass.equals("")) {
                    Toast.makeText(signup.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean checkuser = DB.checkusername(user);
                    if (!checkuser) {
                        Boolean insert = DB.insertData(user, pass);
                        if (insert) {
                            Toast.makeText(signup.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), login.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(signup.this, "Registration failed", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(signup.this, "User already exists! Please sign in", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        loginalready.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(signup.this, login.class));
            }
        });
    }
}
