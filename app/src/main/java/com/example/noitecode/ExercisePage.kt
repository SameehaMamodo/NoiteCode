package com.example.noitecode

import DatabaseHelper
import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.noitecode.model.Exercise

class   ExercisePage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercisepage)
        createChannelNotification()

    }

    fun saveExercise(view: View) {

        val dbHelper = DatabaseHelper(this)

        val exname = findViewById<EditText>(R.id.textViewAddEN).text.toString()
        val extime = findViewById<EditText>(R.id.editTxtETime).text.toString()


        var key = 0
        var user = dbHelper.getLastUser().last().Username
        var name = exname
        var timer = extime



        if(exname.isEmpty()){
            Toast.makeText(this, "Please enter an exercise name", Toast.LENGTH_SHORT).show()
        }

        else if(extime.isEmpty()){
            Toast.makeText(this, "Please enter an exercise time", Toast.LENGTH_SHORT).show()
        }

        else{
            var e = Exercise(user, name, timer, key)
            dbHelper.addExercise(e)
            Toast.makeText(this, "Exercise added", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, HomePage::class.java))
            val intent = Intent(this, ExerciseBroadcast::class.java)
            val pendingIntent = PendingIntent.getBroadcast(this, 1, intent, PendingIntent.FLAG_IMMUTABLE)
            val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
            val timeAtButtonClick = System.currentTimeMillis()
            val timeFrame = (dbHelper.getAllExercise().last().ExerciseTime.toInt() * 60000).toLong()
            alarmManager[AlarmManager.RTC_WAKEUP, timeAtButtonClick + timeFrame] = pendingIntent

        }
    }

    private fun createChannelNotification() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name: CharSequence = "Noite Exercise"
            val description = "Channel for Noite Reminder"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel("noitecode", name, importance)
            channel.description = description
            val notificationManager = getSystemService(
                NotificationManager::class.java
            )
            notificationManager.createNotificationChannel(channel)
        }
    }
}
