package com.example.noitecode

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class ViewNotifsPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewnotifspage)
    }

    fun doneView(view: View) {

        val intent = Intent(this, HomePage::class.java)
        startActivity(intent)
    }
}