import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.noitecode.model.*


/* Database Config*/
private val DataBaseName = "devproj.db"
private val ver : Int = 1


class DatabaseHelper(context: Context) : SQLiteOpenHelper(context,DataBaseName,null ,ver) {

    //    User Table
    public val UserTableName ="UserTbl"
    public val User_ID = "Username"
    public val UserPassword ="Password"

    //    Medicine table
    public val MedicineTableName = "MedicineTbl"
    public val mUser ="User"
    public val mName = "MedicineName"
    public val mTime = "MedicineTime"
    public val mDose = "MedicineDose"
    public val m_ID = "MedicineID"

    //    Exercise Table
    public val ExerciseTableName = "ExerciseTbl"
    public val eUser = "Username"
    public val eName = "ExerciseName"
    public val eTime = "ExerciseTime"
    public val e_ID = "ExerciseID"

    //    Reminder Table
    public val ReminderTableName = "ReminderTbl"
    public val rUser = "Username"
    public val rName = "ReminderName"
    public val rTime = "ReminderTime"
    public val r_ID = "ReminderID"

    public val TempUserTableName = "TempUserTbl"
    public val sUser = "Username"
    public val sNumber = "Number"


    override fun onCreate(db: SQLiteDatabase?) {

        try {
            var sqlCreateStatement: String = "CREATE TABLE " + UserTableName + " ( " + User_ID +
                    " TEXT , " + UserPassword + " TEXT NOT NULL )"

            db?.execSQL(sqlCreateStatement)

            sqlCreateStatement = "CREATE TABLE " + MedicineTableName + " ( " + mUser +
                    " TEXT , " + mName + " TEXT , " +
                    mTime + " TEXT ," + mDose + " INTEGER ," + m_ID + " INTEGER NOT NULL )"

            db?.execSQL(sqlCreateStatement)

            sqlCreateStatement = "CREATE TABLE " + ExerciseTableName +
                    " ( "+ eUser + " TEXT , " + eName + " TEXT NOT NULL , " +
                    eTime + " TEXT NOT NULL   " + e_ID + " INTEGER PRIMARY KEY AUTOINCREMENT )"



            db?.execSQL(sqlCreateStatement)

            sqlCreateStatement = "CREATE TABLE " + ReminderTableName +
                    " ( " + rUser + " TEXT , " + rName + " TEXT NOT NULL , " +
                    rTime + " TEXT NOT NULL ," +  r_ID + " INTEGER)"

            db?.execSQL(sqlCreateStatement)

            sqlCreateStatement = "CREATE TABLE " + TempUserTableName +
                    " ( " + sUser + " TEXT , " + sNumber + " INTEGER)"

            db?.execSQL(sqlCreateStatement)


        }
        catch (e: SQLException) { }

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }


    fun addUserPass(userPassword: User): Boolean {
        val db: SQLiteDatabase = this.writableDatabase
        val cv: ContentValues = ContentValues()

        cv.put(User_ID, userPassword.Username)
        cv.put(UserPassword, userPassword.Password)

        val success = db.insert(UserTableName, null, cv)
        db.close()
        return success != -1L
    }

    fun ifNameExists(name: String): Boolean {
        val db: SQLiteDatabase = this.readableDatabase
        var bool = false
        val whereClause = "$User_ID=?"
        val whereArgs: String = name
        val cursor: Cursor = db.query(UserTableName,null,whereClause,
            arrayOf(whereArgs),null,null, null)

        if (cursor.count > 0) {
            bool = true;
        }
        cursor.close()
        return bool
    }

    fun login(username: String, password: String): Boolean {
        val db: SQLiteDatabase = this.readableDatabase
        val mCursor: Cursor = db.rawQuery(
            "SELECT * FROM $UserTableName WHERE $User_ID=? AND $UserPassword=?",
            arrayOf(username, password)

        )
        if (mCursor.count > 0) {
            return true
        }
        return false
    }

    fun addMedicine(userMedicine: Medicine): Boolean {
        val db: SQLiteDatabase = this.writableDatabase
        val cv: ContentValues = ContentValues()

        cv.put(mUser, userMedicine.User)
        cv.put(mName, userMedicine.MedicineName)
        cv.put(mTime, userMedicine.MedicineTime)
        cv.put(mDose, userMedicine.MedicineDose)

        val success = db.insert(MedicineTableName, null, cv)
        db.close()
        return success != -1L
    }

    fun addExercise(userExercise: Exercise): Boolean {
        val db: SQLiteDatabase = this.writableDatabase
        val cv: ContentValues = ContentValues()

        cv.put(eUser, userExercise.Username)
        cv.put(eName, userExercise.ExerciseName)
        cv.put(eTime, userExercise.ExerciseTime)

        val success = db.insert(ExerciseTableName, null, cv)
        db.close()
        return success != -1L
    }

    fun addReminder(userReminder: Reminder): Boolean {
        val db: SQLiteDatabase = this.writableDatabase
        val cv: ContentValues = ContentValues()

        cv.put(rUser, userReminder.Username)
        cv.put(rName, userReminder.ReminderName)
        cv.put(rTime, userReminder.ReminderTime)

        val success = db.insert(ReminderTableName, null, cv)
        db.close()
        return success != -1L
    }

    fun AddTemporaryUser(tempUser: TempUser): Boolean {
        val db: SQLiteDatabase = this.writableDatabase
        val cv: ContentValues = ContentValues()

        cv.put(sUser, tempUser.Username)

        val success = db.insert(TempUserTableName, null, cv)
        db.close()
        return success != -1L
    }

    fun getAllMedicine(): ArrayList<Medicine> {

        val medicineList = ArrayList<Medicine>()
        val db: SQLiteDatabase = this.readableDatabase
        val sqlStatement = "SELECT * FROM $MedicineTableName"
        //WHERE $mUser == $s"

        val cursor: Cursor = db.rawQuery(sqlStatement, null)
        if (cursor.moveToFirst())
            do {
                val user: String = cursor.getString(0)
                val med: String = cursor.getString(1)
                val medT: String = cursor.getString(2)
                val medD: Int = cursor.getInt(3)
                val medId: Int = cursor.getInt(4)

                val m = Medicine(user, med, medT,medD,medId)

                medicineList.add(m)
            } while (cursor.moveToNext())
            cursor.close()
        db.close()

        return medicineList
    }

    fun getAllExercise(): ArrayList<Exercise> {

        val exerciseList = ArrayList<Exercise>()
        val db: SQLiteDatabase = this.readableDatabase
        val sqlStatement = "SELECT * FROM $ExerciseTableName"

        val cursor: Cursor = db.rawQuery(sqlStatement, null)
        if (cursor.moveToFirst())
            do {
                val user: String = cursor.getString(0)
                val xname: String = cursor.getString(1)
                val xT: String = cursor.getString(2)
                val xId: Int = cursor.getInt(3)

                val x = Exercise(user, xname, xT, xId)
                exerciseList.add(x)

            } while (cursor.moveToNext())

        cursor.close()
        db.close()

        return exerciseList
    }

    fun getAllReminders(): ArrayList<Reminder> {

        val reminderList = ArrayList<Reminder>()
        val db: SQLiteDatabase = this.readableDatabase
        val sqlStatement = "SELECT * FROM $ReminderTableName"

        val cursor: Cursor = db.rawQuery(sqlStatement, null)
        if (cursor.moveToFirst())
            do {
                val user: String = cursor.getString(0)
                val rname: String = cursor.getString(1)
                val rT: String = cursor.getString(2)
                val rId: Int = cursor.getInt(3)

                val x = Reminder(user, rname, rT, rId)
                reminderList.add(x)

            } while (cursor.moveToNext())

        cursor.close()
        db.close()

        return reminderList
    }

    fun getAllUser(): ArrayList<User> {

        val userList = ArrayList<User>()
        val db: SQLiteDatabase = this.readableDatabase
        val sqlStatement = "SELECT * FROM $MedicineTableName"

        val cursor: Cursor = db.rawQuery(sqlStatement, null)
        if (cursor.moveToFirst())
            do {
                val user: String = cursor.getString(0)
                val password: String = cursor.getString(1)

                val m = User(user, password)

                userList.add(m)
            } while (cursor.moveToNext())
        cursor.close()
        db.close()

        return userList
    }
    fun getLastUser(): ArrayList<TempUser> {

        val userList = ArrayList<TempUser>()
        val db: SQLiteDatabase = this.readableDatabase
        val sqlStatement = "SELECT *FROM $TempUserTableName"

        val cursor: Cursor = db.rawQuery(sqlStatement, null)
        if (cursor.moveToFirst())
            do {
                val user: String = cursor.getString(0)
                val number: Int = cursor.getInt(1)

                val m = TempUser(user, number)

                userList.add(m)
            } while (cursor.moveToNext())
        cursor.close()
        db.close()

        return userList
    }

    fun clearTempUser(){
        val db: SQLiteDatabase = this.writableDatabase
        db.delete(TempUserTableName, null, null)
    }

    fun deleteLastExercise(){
        val db: SQLiteDatabase = this.writableDatabase
        db.execSQL("DELETE FROM $ExerciseTableName WHERE $e_ID = (SELECT MAX($e_ID) FROM $ExerciseTableName)")
    }

    fun deleteLastMedication(){
        val db: SQLiteDatabase = this.writableDatabase
        db.execSQL("DELETE FROM $MedicineTableName WHERE $m_ID = (SELECT MAX($m_ID) FROM $MedicineTableName)")
    }

    fun deleteLastReminder(){
        val db: SQLiteDatabase = this.writableDatabase
        db.execSQL("DELETE FROM $ReminderTableName WHERE $r_ID = (SELECT MAX($r_ID) FROM $ReminderTableName)")
    }

}

