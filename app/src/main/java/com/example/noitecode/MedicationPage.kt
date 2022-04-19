package com.example.noitecode

import DatabaseHelper
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.noitecode.model.Medicine
import android.widget.Button
import android.support.v4.app.RemoteActionCompatParcelizer
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat


class MedicationPage : AppCompatActivity() {

    private val CHANNEL_ID = "channel_id_example_01"
    private val notificationId = 101
   // private val btn_button = findViewById<Button>(R.id.btn_button)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medicationpage)

        createNotificationChannel()
//        btn_button.setOnClickListener {
//            sendNotification()
//        }

    }

    private fun createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val name = "Notification Title"
            val descriptionText = "Notification Description"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name,importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    //private
     fun sendNotification(){

        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.noitelogo)
            .setContentTitle("Example Title")
            .setContentText("Example Description")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        with(NotificationManagerCompat.from(this)){
            notify(notificationId, builder.build())
        }
    }


    fun saveMedication(view: View) {
        val extras: Bundle? = intent.extras
        if (extras != null) {

            val username = getIntent().getStringExtra("username1")

            val dbHelper = DatabaseHelper(this)

            val medname = findViewById<EditText>(R.id.editTextMedicationName).text.toString()
            val medtime = findViewById<EditText>(R.id.editTextMedTime).text.toString()
            val meddose = findViewById<EditText>(R.id.editTextMedDose).text.toString()

//            if(findViewById<EditText>(R.id.editTextUserName).text.toString() != "" ||
//                    findViewById<EditText>(R.id.textViewaddU).text.toString() != " "){

            var uno = username.toString()
            var two = medname
            var three = medtime
            var four = meddose.toInt()
            var five = 0

            var six = Medicine(uno, two, three, four, five)


            if (dbHelper.addMedicine(six)) {
                Toast.makeText(this, "Medicine added", Toast.LENGTH_SHORT).show()
                sendNotification()
                startActivity(Intent(this, HomePage::class.java))


            } else {
                Toast.makeText(this, "Medicine not added", Toast.LENGTH_SHORT).show()
            }

//            } else {
//                Toast.makeText(this, "Medicine not added", Toast.LENGTH_SHORT).show()
//
//            }
        }
    }
}

