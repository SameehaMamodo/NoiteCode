
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

//        findViewById<EditText>(R.id.editTextUserName).getText()
//        findViewById<EditText>(R.id.editTextTextPassword).getText()

  //      findViewById<TextView>(R.id.txtViewUser).text = findViewById<EditText>(R.id.editTextUserName).getText()

        var db = DatabaseHelper(this)
        var uno = db.getLastUser().last()

        findViewById<TextView>(R.id.txtViewUser).text = "Hi ${uno.Username}"

        val extras: Bundle? = intent.extras
        if (extras != null) {

            val username = getIntent().getStringExtra("username4")

//            intent.putExtra("username1", username)
//            startActivity(intent)
        }

        }



    fun AddMedication(view: View) {

        val extras: Bundle? = intent.extras
        if(extras != null){
            val username = extras.getString("username")

            val intent = Intent(this, MedicationPage::class.java)
            intent.putExtra("username1", username)
            startActivity(intent)
        }

    }

    fun AddExercise(view: View) {

        val extras: Bundle? = intent.extras
        if(extras != null){
            val username = extras.getString("username")

            val intent = Intent(this, ExercisePage::class.java)
            intent.putExtra("username2", username)
            startActivity(intent)
        }
    }


    fun AddReminder(view: View) {
        val extras: Bundle? = intent.extras
        if(extras != null){
            val username = extras.getString("username")

            val intent = Intent(this, ReminderPage::class.java)
            intent.putExtra("username3", username)
            startActivity(intent)
        }
    }

    fun ViewReminder(view: View) {
        val extras: Bundle? = intent.extras

        if(extras != null) {
            val intent = Intent(this, ViewNotifsPage::class.java)
            val username = extras.getString("username")

            intent.putExtra("username4", username)
            startActivity(intent)

        }
    }
}
