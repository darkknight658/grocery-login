package com.example.signuplogin;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class login extends AppCompatActivity {
    private EditText loginUsername, loginPassword;
    private TextView LOGIN;
    private Button loginButton;
    private DB DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginUsername = findViewById(R.id.login);
        loginPassword = findViewById(R.id.loginpassword);
        loginButton = findViewById(R.id.loginbutton);
        LOGIN = findViewById(R.id.LOGINN);
        DB = new DB(this);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = loginUsername.getText().toString().trim();
                String password = loginPassword.getText().toString().trim();

                if (isValidCredentials(username, password)) {
                    checkLoginCredentials(username, password);
                } else {
                    Toast.makeText(login.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void checkLoginCredentials(String username, String password) {
        if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)) {
            Boolean checkUserPass = DB.checkusernamepassword(username, password);
            if (checkUserPass) {
                Toast.makeText(login.this, "Sign in successful", Toast.LENGTH_SHORT).show();
                navigateToFragmentPage();
            } else {
                Toast.makeText(login.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(login.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isValidCredentials(String username, String password) {
        // You can add any specific validation logic here if needed
        return true;
    }

    private void navigateToFragmentPage() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.contaner, new Fragment1()).commit();
        loginUsername.setEnabled(false);
        loginButton.setVisibility(View.GONE);
        loginPassword.setVisibility(View.GONE);
        LOGIN.setVisibility(View.GONE);
        LOGIN.setText("WELCOME");
        LOGIN.setVisibility(View.VISIBLE);
    }
}
