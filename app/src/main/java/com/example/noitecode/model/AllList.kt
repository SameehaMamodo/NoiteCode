package com.example.noitecode.model

import DatabaseHelper
import android.content.Context

class AllList(context: Context) {

    private val medicineList: ArrayList<Medicine>
    private val userList: ArrayList<User>
    private val exerciseList: ArrayList<Exercise>
    private val reminderList: ArrayList<Reminder>
    private val tempUserList: ArrayList<TempUser>
    private val dbHelper: DatabaseHelper = DatabaseHelper(context)

    init {
        medicineList =dbHelper.getAllMedicine()
        exerciseList = dbHelper.getAllExercise()
        reminderList = dbHelper.getAllReminders()
        tempUserList = dbHelper.getLastUser()
        userList=dbHelper.getAllUser()

    }
    fun get_medicineList(): ArrayList<Medicine> {
        return medicineList
    }

    fun get_exerciseList(): ArrayList<Exercise> {
        return exerciseList
    }

    fun get_reminderList(): ArrayList<Reminder>{
        return reminderList
    }

    fun getTempUserList(): ArrayList<TempUser>{
        return tempUserList
    }


    fun get_userList(): ArrayList<User> {
        return userList
    }
}