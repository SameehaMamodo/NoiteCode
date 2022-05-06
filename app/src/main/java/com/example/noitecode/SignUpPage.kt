package com.example.noitecode

import DatabaseHelper
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.noitecode.model.User

class SignUpPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signuppage)
    }

        fun buttonSave(view: View) {


            val username = findViewById<EditText>(R.id.textViewaddU).text.toString()
            val password = findViewById<EditText>(R.id.textViewaddP).text.toString()
            val dbHelper = DatabaseHelper(this)

            val n = username
            val p = password
            val u = User(n, p)

            if (dbHelper.ifUserExists(n)) {
                Toast.makeText(this, "User already exists", Toast.LENGTH_LONG).show()
            } else {
                dbHelper.addUser(u)
                Toast.makeText(this, "User added", Toast.LENGTH_LONG).show()
            }
        }

}