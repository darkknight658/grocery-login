package com.example.signuplogin;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class NetworkCheckService extends Service {

    public static final String NETWORK_STATUS_ACTION = "com.example.signuplogin.NETWORK_STATUS";
    public static final String NETWORK_STATUS_EXTRA = "networkStatus";

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        checkNetworkStatus();
        return START_NOT_STICKY;
    }

    private void checkNetworkStatus() {
        // Check network connection status
        String status = checkinternet.getNetworkInfo(this);

        // Send a broadcast with network status
        Intent broadcastIntent = new Intent(NETWORK_STATUS_ACTION);
        broadcastIntent.putExtra(NETWORK_STATUS_EXTRA, status);
        sendBroadcast(broadcastIntent);

        // Stop the service after checking network status
        stopSelf();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
