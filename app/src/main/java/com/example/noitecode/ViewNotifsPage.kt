package com.example.noitecode

import DatabaseHelper
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.noitecode.model.*
import java.util.*
import kotlin.collections.ArrayList
import java.util.function.Consumer

class ViewNotifsPage : AppCompatActivity() {
    lateinit var allList: AllList

    lateinit var myMedicineList: ArrayList<Medicine>
    lateinit var topic1: ArrayList<Medicine>
    lateinit var uno: ArrayList<Medicine>

    lateinit var myExerciseList: ArrayList<Exercise>
    lateinit var topic2: ArrayList<Exercise>
    lateinit var tres: ArrayList<Exercise>

    lateinit var myReminderList: ArrayList<Reminder>
    lateinit var topic3: ArrayList<Reminder>
    lateinit var cinco: ArrayList<Reminder>

    lateinit var dbHelper: DatabaseHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewnotifspage)

                allList = AllList(this)
                dbHelper = DatabaseHelper(this)

        val username = allList.getTempUserList().last().Username


            myMedicineList = allList.get_medicineList()
                topic1 = ArrayList()
                uno = ArrayList()

                for (s in myMedicineList) {
                    when (s.User) {
                        username -> topic1.add(s)
                    }
                }

                var a = topic1.last()
                if (a.User == username)
                    uno.add(a)

                var one = uno.last().MedicineName
                var two = uno.last().MedicineTime
                var three = uno.last().MedicineDose
                var s = ("Medicine Name: " + one + "\nMedcine Time: " + two + "\nMedicine Dose: " + three)


                findViewById<TextView>(R.id.txtMedTitle4).text = s

                myExerciseList = allList.get_exerciseList()
                topic2 = ArrayList()
                tres = ArrayList()

                for (s in myExerciseList) {
                    when (s.Username) {
                        username -> topic2.add(s)
                    }
                }

                var b = topic2.last()
                if (b.Username == username)
                    tres.add(b)

                var four = tres.last().ExerciseName
                var five = tres.last().ExerciseTime
                var t = ("Exercise Name: " + four + "\nExercise Time: " + five)

                findViewById<TextView>(R.id.txtExerciseTitle).text = t

                myReminderList = allList.get_reminderList()
                topic3= ArrayList()
                cinco = ArrayList()

                for (s in myReminderList) {
                    when (s.Username) {
                        username -> topic3.add(s)
                    }
                }

                var c = topic3.last()
                if (c.Username == username)
                    cinco.add(c)

                var six = cinco.last().ReminderName
                var seven = cinco.last().ReminderTime
                var u = ("Reminder Name: " + six + "\nReminder Time: " + seven)

                findViewById<TextView>(R.id.txtReminderTitle).text = u
        //    }
        }
    fun cancelExerciseNotification(view: View) {
        val intent = Intent(this, ExerciseBroadcast::class.java)
        val am = getSystemService(ALARM_SERVICE) as AlarmManager
        val pendingIntent = PendingIntent.getBroadcast(this, 1, intent, PendingIntent.FLAG_IMMUTABLE)
        pendingIntent.cancel()
        am.cancel(pendingIntent)
        var b = allList.get_exerciseList().last()
        if (b.Username == allList.getTempUserList().last().Username){
            dbHelper.deleteLastExercise()
        }
    }
    fun cancelMedicineNotification(view: View) {
        val intent = Intent(this, MedicineBroadcast::class.java)
        val am = getSystemService(ALARM_SERVICE) as AlarmManager
        val pendingIntent =
            PendingIntent.getBroadcast(this, 2, intent, PendingIntent.FLAG_IMMUTABLE)
        pendingIntent.cancel()
        am.cancel(pendingIntent)
        var b = allList.get_medicineList().last()
        if (b.User == allList.getTempUserList().last().Username) {
            dbHelper.deleteLastMedication()
        }
    }
    fun cancelReminderNotification(view: View) {
        val intent = Intent(this, ReminderBroadcast::class.java)
        val am = getSystemService(ALARM_SERVICE) as AlarmManager
        val pendingIntent =
            PendingIntent.getBroadcast(this, 3, intent, PendingIntent.FLAG_IMMUTABLE)
        pendingIntent.cancel()
        am.cancel(pendingIntent)
        var b = allList.get_reminderList().last()
        if (b.Username == allList.getTempUserList().last().Username) {
            dbHelper.deleteLastReminder()
        }
    }


    fun doneView(view: View) {
                val intent = Intent(this, HomePage::class.java)
                startActivity(intent)
        }
    }

