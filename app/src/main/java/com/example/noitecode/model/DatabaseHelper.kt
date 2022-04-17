import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteTableLockedException
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
                    eTime + " TEXT NOT NULL   " + e_ID + "INTEGER NOT NULL   )"

            db?.execSQL(sqlCreateStatement)

            sqlCreateStatement = "CREATE TABLE " + ReminderTableName +
                    " ( " + rUser + " TEXT , " + rName + " TEXT NOT NULL , " +
                    rTime + " TEXT NOT NULL" +  r_ID + " INTEGER)"

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
      //  db.close()
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

        cv.put(mName, userMedicine.MedicineName)
        cv.put(mTime, userMedicine.MedicineTime)
        cv.put(mDose, userMedicine.MedicineDose)

        val success = db.insert(MedicineTableName, null, cv)
       // db.close()
        return success != -1L
    }

    fun addExercise(userExercise: Exercise): Boolean {
        val db: SQLiteDatabase = this.writableDatabase
        val cv: ContentValues = ContentValues()

        cv.put(eName, userExercise.ExerciseName)
        cv.put(eTime, userExercise.ExerciseTime)

        val success = db.insert(ExerciseTableName, null, cv)
        //  db.close()
        return success != -1L
    }

    fun addReminder(userReminder: Reminder): Boolean {
        val db: SQLiteDatabase = this.writableDatabase
        val cv: ContentValues = ContentValues()

        cv.put(rName, userReminder.ReminderName)
        cv.put(rTime, userReminder.ReminderTime)

        val success = db.insert(ReminderTableName, null, cv)
        //  db.close()
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


//    fun getAllDetails(): ArrayList<Medicine>{
//        val db: SQLiteDatabase = this.readableDatabase
//        val cv: ContentValues = ContentValues()
//        val sqlStatement = "SELECT * FROM $MedicineTableName WHERE $User_ID == "
//
//    }

}
