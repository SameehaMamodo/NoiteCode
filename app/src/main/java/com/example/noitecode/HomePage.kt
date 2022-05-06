package com.example.noitecode

import DatabaseHelper
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.noitecode.model.Reminder
import com.example.noitecode.model.User


class HomePage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homepage)


        var db = DatabaseHelper(this)
        var uno = db.getLastUser().last()

        findViewById<TextView>(R.id.txtViewUser).text = "Hi ${uno.Username}"

    }



    fun AddMedication(view: View) {

        val intent = Intent(this, MedicationPage::class.java)
        startActivity(intent)

    }

    fun AddExercise(view: View) {

        val intent = Intent(this, ExercisePage::class.java)
        startActivity(intent)
    }

    fun AddReminder(view: View) {
        val intent = Intent(this, ReminderPage::class.java)
        startActivity(intent)
    }

    fun ViewReminder(view: View) {
        val intent = Intent(this, ViewNotifsPage::class.java)
        startActivity(intent)

    }
}
