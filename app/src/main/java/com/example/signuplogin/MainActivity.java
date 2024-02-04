package com.example.signuplogin;

import androidx.appcompat.app.AppCompatActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button signupButton;
    private Button loginButton;

    private BroadcastReceiver networkStatusReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String status = intent.getStringExtra(NetworkCheckService.NETWORK_STATUS_EXTRA);

            // Display Toast based on network connection status
            if (status != null) {
                if (status.equals("connected")) {
                    Toast.makeText(MainActivity.this, "Network connected. Moving to next activity.", Toast.LENGTH_SHORT).show();
                    // Open the desired activity
                    startActivity(new Intent(MainActivity.this, signup.class));
                } else if (status.equals("disconnected")) {
                    Toast.makeText(MainActivity.this, "Network disconnected. Cannot proceed.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Unexpected network status.", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(MainActivity.this, "Unable to retrieve network information.", Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signupButton = findViewById(R.id.signupButton);
        loginButton = findViewById(R.id.loginButton);

        // Register the BroadcastReceiver to listen for network status broadcasts
        registerReceiver(networkStatusReceiver, new IntentFilter(NetworkCheckService.NETWORK_STATUS_ACTION));

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start the NetworkCheckService
                startService(new Intent(MainActivity.this, NetworkCheckService.class));
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start the NetworkCheckService
                startService(new Intent(MainActivity.this, NetworkCheckService.class));
            }
        });
    }

    @Override
    protected void onDestroy() {
        // Unregister the BroadcastReceiver when the activity is destroyed
        unregisterReceiver(networkStatusReceiver);
        super.onDestroy();
    }
}
