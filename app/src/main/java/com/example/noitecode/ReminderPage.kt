package com.example.noitecode

import DatabaseHelper
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.noitecode.model.Reminder
import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager

import android.app.PendingIntent
import android.content.Context
import android.os.Build
import java.util.*


class ReminderPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reminderspage)
        createChannelNotification()

    }

    fun saveReminder(view: View) {

        val dbHelper = DatabaseHelper(this)

        val rname = findViewById<EditText>(R.id.textViewAddNN).text.toString()
        val rtime = findViewById<EditText>(R.id.textViewAddNT).text.toString()

        var one = 0
        var two = dbHelper.getLastUser().last().Username
        var three = rname
        var four = rtime


        if(rname.isEmpty()){
            Toast.makeText(this, "Please enter a reminder name", Toast.LENGTH_SHORT).show()
        }

        else if(rtime.isEmpty()){
            Toast.makeText(this, "Please enter a reminder time", Toast.LENGTH_SHORT).show()
        }

        else{
            var five = Reminder(two, three, four, one)
            dbHelper.addReminder(five)
            Toast.makeText(this, "Reminder added", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, HomePage::class.java))
            val intent = Intent(this, ReminderBroadcast::class.java)
            val pendingIntent = PendingIntent.getBroadcast(this, 3, intent, PendingIntent.FLAG_IMMUTABLE)
            val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
            val timeAtButtonClick = System.currentTimeMillis()
            val timeFrame = (dbHelper.getAllReminders().last().ReminderTime.toInt() * 6000).toLong()
            alarmManager[AlarmManager.RTC_WAKEUP, timeAtButtonClick + timeFrame] = pendingIntent

        }

    }


    private fun createChannelNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name: CharSequence = "NoiteReminder"
            val description = "Channel for Noite Reminder"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel("noitecode2", name, importance)
            channel.description = description
            val notificationManager = getSystemService(
                NotificationManager::class.java
            )
            notificationManager.createNotificationChannel(channel)
        }
    }
}

