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


class ExerciseBroadcast : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        val dbHelper = DatabaseHelper(context)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val builder = NotificationCompat.Builder(context, "noitecode")
                .setSmallIcon(R.drawable.noitelogo)
                .setContentTitle("Exercise Reminder")
                .setContentText(dbHelper.getAllExercise().last().ExerciseName)
                .setPriority(NotificationCompat.PRIORITY_MAX)
            builder.setChannelId("noitecode")
            val notificationManager = NotificationManagerCompat.from(context)

            notificationManager.notify(200, builder.build())
        }
    }
}

