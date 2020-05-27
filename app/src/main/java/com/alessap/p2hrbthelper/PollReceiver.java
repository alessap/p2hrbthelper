package com.alessap.p2hrbthelper;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class PollReceiver extends BroadcastReceiver {
    public static final int REQUEST_CODE = 10001;
    public static final String ACTION = "com.alessap.p2hrbthelper.alarm";

    // Triggered by AlarmManager periodically
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i = new Intent(context, PollService.class);
        i.putExtra("poll", "pebble");   // Not needed?
        context.startService(i);
    }
}
