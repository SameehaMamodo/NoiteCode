package com.example.noitecode

import DatabaseHelper
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.noitecode.model.Exercise

class ExercisePage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercisepage)

      //  findViewById<EditText>(R.id.editTxtETime)
    }

    fun saveExercise(view: View) {

        val extras: Bundle? = intent.extras
        if (extras != null) {

            val username = getIntent().getStringExtra("username2")

//        var db = DatabaseHelper(this)
//        var uno = db.getLastUser().last().Username.//.size.get(0)//.Username.toString()
//       var abc = uno.toString()

            // findViewById<TextView>(R.id.txtViewUser).text = "Hi ${uno.Username}"

            val dbHelper = DatabaseHelper(this)

            val exname = findViewById<EditText>(R.id.textViewAddEN).text.toString()
            val extime = findViewById<EditText>(R.id.editTxtETime).text.toString()

//            if(findViewById<EditText>(R.id.editTextUserName).text.toString() != "" ||
//                    findViewById<EditText>(R.id.textViewaddU).text.toString() != " "){

            var one = 0
            var two = username.toString() //username.toString()
            var three = exname
            var four = extime

            var five = Exercise(two, three, four, one)

            if (dbHelper.addExercise(five)) {
                Toast.makeText(this, "Exercise added", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, HomePage::class.java))
            } else {
                Toast.makeText(this, "Exercise not added", Toast.LENGTH_SHORT).show()
                //  }
            }
        }
    }

}
