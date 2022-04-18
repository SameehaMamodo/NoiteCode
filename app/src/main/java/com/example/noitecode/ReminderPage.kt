package com.example.noitecode

import DatabaseHelper
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.noitecode.model.Reminder

class ReminderPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reminderspage)
    }

    fun saveReminder(view: View) {

        val extras: Bundle? = intent.extras
        if (extras != null) {

            val username = getIntent().getStringExtra("username3")

//        val intent = Intent(this, HomePage::class.java)
//        when {
//            findViewById<EditText>(R.id.textViewAddNN).text.toString() == "" -> {
//                Toast.makeText(this, "Please enter a reminder name", Toast.LENGTH_LONG).show()
//            }
//            findViewById<EditText>(R.id.textViewAddNT).text.toString() == "" -> {
//                Toast.makeText(this, "Please enter a reminder time", Toast.LENGTH_LONG).show()
//            }
//            else -> startActivity(intent)
//        }
            val dbHelper = DatabaseHelper(this)

            val rname = findViewById<EditText>(R.id.textViewAddNN).text.toString()
            val rtime = findViewById<EditText>(R.id.textViewAddNT).text.toString()

//            if(findViewById<EditText>(R.id.editTextUserName).text.toString() != "" ||
//                    findViewById<EditText>(R.id.textViewaddU).text.toString() != " "){

            var one = 0
            var two = username.toString()
            var three = rname
            var four = rtime

            var five = Reminder(two, three, four, one)

            if (dbHelper.addReminder(five)) {
                Toast.makeText(this, "Reminder added", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, HomePage::class.java))
            } else {
                Toast.makeText(this, "Reminder not added", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

