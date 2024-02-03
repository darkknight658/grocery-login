package com.example.signuplogin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;


import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class login extends AppCompatActivity {
    private EditText login, loginPassword;
    private TextView LOGIN;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = findViewById(R.id.login);
        loginPassword = findViewById(R.id.loginpassword);
        loginButton = findViewById(R.id.loginbutton);
        LOGIN = findViewById(R.id.LOGINN);



        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = login.getText().toString();
                String password = loginPassword.getText().toString();


                if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    if (!password.isEmpty()) {
                        if (isValidCredentials(email, password)) {
                            Toast.makeText(login.this, "Login successful", Toast.LENGTH_SHORT).show();
                            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                            fragmentTransaction.replace(R.id.contaner,new Fragment1()).commit();
                            login.setEnabled(false);
                            loginButton.setVisibility(View.GONE);
                            loginPassword.setVisibility(View.GONE);
                            LOGIN.setVisibility(View.GONE);
                            LOGIN.setText("WELCOME ");
                            LOGIN.setVisibility(View.VISIBLE);
                        } else {
                            Toast.makeText(login.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        loginPassword.setError("Password cannot be empty");
                    }
                } else if (email.isEmpty()) {
                    login.setError("Email cannot be empty");
                } else {
                    login.setError("Please enter a valid email");
                }
            }
        });

    }

    private boolean isValidCredentials(String email, String password) {
        if (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {

            if (!TextUtils.isEmpty(password) && password.length() >= 6) {
                return true;
            } else {

                return false;
            }
        } else {

            return false;
        }
    }


}



