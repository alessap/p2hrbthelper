package com.alessap.p2hrbthelper;

import android.app.IntentService;
import android.app.PendingIntent;
import android.app.Service;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class PollService extends IntentService {
    private static final long POLL_PERIOD = 5000L;   // One minute

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

    public static void scheduleJob(Context context) {
        ComponentName serviceComponent = new ComponentName(context, PollJobService.class);
        JobInfo.Builder builder = new JobInfo.Builder(0, serviceComponent);
        builder.setMinimumLatency(POLL_PERIOD); // wait at least poll period
        builder.setOverrideDeadline(2 * POLL_PERIOD); // maximum delay
        //builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED); // require unmetered network
        //builder.setRequiresDeviceIdle(true); // device should be idle
        //builder.setRequiresCharging(false); // we don't care if the device is charging or not
        JobScheduler jobScheduler = context.getSystemService(JobScheduler.class);
        jobScheduler.schedule(builder.build());
    }
}

