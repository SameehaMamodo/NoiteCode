package com.example.noitecode

import DatabaseHelper
import android.app.*
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.noitecode.model.Medicine


class MedicationPage : AppCompatActivity() {

    private val CHANNEL_ID = "channel_id_example_01"
    private val notificationId = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medicationpage)

        createChannelNotification()

    }

    private fun createChannelNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val name: CharSequence = "NoiteReminder"
            val description = "Channel for Noite Medication"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel("noitecode1", name, importance)
            channel.description = description
            val notificationManager = getSystemService(
                NotificationManager::class.java
            )
            notificationManager.createNotificationChannel(channel)
        }
    }


    fun saveMedication(view: View) {

        val dbHelper = DatabaseHelper(this)

        val medname = findViewById<EditText>(R.id.editTextMedicationName).text.toString()
        val medtime = findViewById<EditText>(R.id.editTextMedTime).text.toString()
        val meddose = findViewById<EditText>(R.id.editTextMedDose).text.toString()

        var uno = dbHelper.getLastUser().last().Username
        var two = medname
        var three = medtime
        var four = if(meddose == "") 0 else meddose.toInt()
        var five = 0


        if (medname.isEmpty()) {
            Toast.makeText(this, "Please enter a medicine name", Toast.LENGTH_SHORT).show()
        }
        else if
        (medtime.isEmpty()) {
            Toast.makeText(this, "Please enter a medicine time", Toast.LENGTH_SHORT).show()
        }
        else if (meddose == "") {
            Toast.makeText(this, "Please enter a medicine dose", Toast.LENGTH_SHORT).show()
        }

        else {
            val six = Medicine(uno, two, three, four, five)
            dbHelper.addMedicine(six)
            Toast.makeText(this, "Medicine added", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, HomePage::class.java)
            startActivity(intent)
            val intent1 = Intent(this, MedicineBroadcast::class.java)
            val pendingIntent = PendingIntent.getBroadcast(this, 2, intent1, PendingIntent.FLAG_IMMUTABLE)
            val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
            val timeAtButtonClick = System.currentTimeMillis()
            val timeFrame =
                (dbHelper.getAllMedicine().last().MedicineTime.toInt() * 60000).toLong()
            alarmManager[AlarmManager.RTC_WAKEUP, timeAtButtonClick + timeFrame] =
                pendingIntent

        }

    }


}