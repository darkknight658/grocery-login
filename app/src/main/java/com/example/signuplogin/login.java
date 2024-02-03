package com.example.signuplogin;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class login extends AppCompatActivity {
    private EditText login, loginPassword;
    private TextView LOGIN;
    private Button loginButton;
    private InternetReceiver broadcastReceiver;

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
                            checkInternetConnection(); // Check internet connection
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
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
                && !TextUtils.isEmpty(password) && password.length() >= 6;
    }

    private void checkInternetConnection() {
        String status = checkinternet.getNetworkInfo(this);

        if (status.equals("connected")) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.contaner, new Fragment1()).commit();
            login.setEnabled(false);
            loginButton.setVisibility(View.GONE);
            loginPassword.setVisibility(View.GONE);
            LOGIN.setVisibility(View.GONE);
            LOGIN.setText("WELCOME ");
            LOGIN.setVisibility(View.VISIBLE);
        } else {
            Toast.makeText(login.this, " No Internet connection , please check network", Toast.LENGTH_SHORT).show();
        }
    }

}
