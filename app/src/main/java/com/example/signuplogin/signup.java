package com.example.signuplogin;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.text.TextUtils;
import android.util.Patterns;



public class signup extends AppCompatActivity {

    public EditText signupgmail, password;
    public Button signupbutton;
    public TextView loginalready;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signupgmail = findViewById(R.id.signup);
        password = findViewById(R.id.signuppassword);
        signupbutton = findViewById(R.id.signupbutton);
        loginalready = findViewById(R.id.loginalready);

        signupbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = signupgmail.getText().toString().trim();
                String pass = password.getText().toString().trim();

                if (user.isEmpty()) {
                    signupgmail.setError("Email cannot be empty");
                }

                if (pass.isEmpty()) {
                    password.setError("Password cannot be empty");
                } else {
                    if (isValidSignup(user, pass)) {
                        Toast.makeText(signup.this, "Signup successful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(signup.this, login.class));
                    } else {
                        Toast.makeText(signup.this, "Signup failed, please try again", Toast.LENGTH_SHORT).show();
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

    private boolean isValidSignup(String email, String password) {

        if (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {

            if (!TextUtils.isEmpty(password) && password.length() >= 6) {
                return true;
            } else {

                return false;
            }
        } else {
            //
            return false;
        }
    }

}
