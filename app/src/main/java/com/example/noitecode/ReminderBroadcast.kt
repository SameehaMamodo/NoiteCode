package com.example.noitecode

import DatabaseHelper
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import com.example.noitecode.R
import androidx.core.app.NotificationManagerCompat


class ReminderBroadcast : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val dbHelper = DatabaseHelper(context)

            val builder = NotificationCompat.Builder(context, "noitecode2")
                .setSmallIcon(R.drawable.noitelogo)
                .setContentTitle("Reminder")
                .setContentText(dbHelper.getAllReminders().last().ReminderName)
                .setPriority(NotificationCompat.PRIORITY_MAX)
            builder.setChannelId("noitecode2")
            val notificationManager = NotificationManagerCompat.from(context)
            notificationManager.notify(300, builder.build())
        }
    }
}
