package com.example.noitecode

import DatabaseHelper
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.noitecode.model.Medicine

class MedicationPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medicationpage)
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

