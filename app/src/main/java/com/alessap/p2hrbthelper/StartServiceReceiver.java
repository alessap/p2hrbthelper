package com.alessap.p2hrbthelper;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Receives the boot-time broadcast and starts the scheduled job.
 */
public class StartServiceReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (MainActivity.ENABLE_START_AT_BOOT) {
            PollService.scheduleJob(context);
            MainActivity.startForegroundService(context);
        }
    }
}
