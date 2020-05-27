package com.alessap.p2hrbthelper;

import android.app.IntentService;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class PollService extends IntentService {
    private static final int NOTIF_ID = 1;
    public static final String NOTIF_CHANNEL_ID = "P2HRBT_Channel_Id";

    public PollService() {
        super("PollService");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.d("PollService", "onHandleIntent: ");
        ForgetPebble.forgetIt();
    }

    // XXX Might not need the rest of this, unless having a persistent notification is useful
    // to quickly access the manual button.  Setting ENABLE_FOREGROUND_SERVICE to FALSE will
    // disable this function.
    @Override
    public int onStartCommand(Intent intent, int flags, int startId){

        // do your jobs here

        Log.d("PollService", "onStartCommand: ");

        if (MainActivity.ENABLE_FOREGROUND_SERVICE) {
            startForeground();
        }

        return super.onStartCommand(intent, flags, startId);
    }

    private void startForeground() {
        Intent notificationIntent = new Intent(this, MainActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                notificationIntent, 0);

        startForeground(NOTIF_ID, new NotificationCompat.Builder(this,
                NOTIF_CHANNEL_ID) // don't forget create a notification channel first
                .setOngoing(true)
//                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle(getString(R.string.app_name))
                .setContentText("Service is monitoring your Pebble")
                .setContentIntent(pendingIntent)
                .build());
    }
}

