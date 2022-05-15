package com.example.mygoals.alarm;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.mygoals.R;

public class AlarmReceiver extends BroadcastReceiver {
    String contentTitle;

    @Override
    public void onReceive(Context context, Intent intent) {

        contentTitle = intent.getStringExtra("contentTitle");

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "My Goal Reminder").setSmallIcon(R.drawable.wish).setContentTitle("My goal reminder").setContentText(contentTitle).setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(100, builder.build());
    }
}
