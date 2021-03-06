package com.example.noitecode

import DatabaseHelper
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.noitecode.model.TempUser


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun SignUpButton(view: View) {

        val intent = Intent(this, SignUpPage::class.java)
        startActivity(intent)
    }

    fun loginButton(view: View) {

        val dbHelper = DatabaseHelper(this)

        val username = findViewById<EditText>(R.id.editTextUserName).text.toString()
        val password = findViewById<EditText>(R.id.editTextTextPassword).text.toString()



        if (dbHelper.login(username, password)) {
            Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
            val i = Intent(this, HomePage::class.java)
            startActivity(i)
            dbHelper.clearTempUser()
            val tUser= TempUser(username, 0)
            dbHelper.AddTemporaryUser(tUser)

        } else {
            Toast.makeText(this, "Login Unsuccessful", Toast.LENGTH_SHORT).show()
        }

    }

}
