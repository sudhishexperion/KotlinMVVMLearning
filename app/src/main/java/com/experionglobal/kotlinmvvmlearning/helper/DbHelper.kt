package com.experionglobal.kotlinmvvmlearning.helper

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import com.experionglobal.kotlinmvvmlearning.model.User
import java.util.*
import kotlin.collections.ArrayList

class DbHelper(context: Context) : SQLiteOpenHelper(context,DATABASE_NAME,null, DATABASE_VERSION) {



    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }

    companion object {

        val DATABASE_VERSION = 1
        val DATABASE_NAME = "UserDetails.db"

        private val SQL_CREATE_ENTRIES = "CREATE TABLE " + DbDetails.UserDetails.TABLE_NAME + " (" +
                DbDetails.UserDetails.COLUMN_NAME + " TEXT," +
                DbDetails.UserDetails.COLUMN_AGE + " NUMBER," +
                DbDetails.UserDetails.COLUMN_SEX + " TEXT)"

        private val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS" + DbDetails.UserDetails.TABLE_NAME

    }

    @Throws(SQLiteConstraintException::class)
    fun insertUserDetails(user: User): Boolean{
         val db = writableDatabase

        val values = ContentValues()
        values.put(DbDetails.UserDetails.COLUMN_NAME, user.userName)
        values.put(DbDetails.UserDetails.COLUMN_AGE,user.userAge)
        values.put(DbDetails.UserDetails.COLUMN_SEX,user.userSex)

        val newRowId = db.insert(DbDetails.UserDetails.TABLE_NAME,null,values)

        return true

    }

    fun searchFromDb(): ArrayList<User> {

        val users =ArrayList<User>()
        val db = writableDatabase
        var cursor: Cursor? = null
        try{

            cursor = db.rawQuery("select * from " + DbDetails.UserDetails.TABLE_NAME, null)

        }catch(e: SQLiteException){

            db.execSQL(SQL_CREATE_ENTRIES)
            return ArrayList()
        }

        var userName: String
        var age: Int
        var sex: String
        if (cursor!!.moveToFirst()) {
            while (cursor.isAfterLast == false) {
                userName = cursor.getString(cursor.getColumnIndex(DbDetails.UserDetails.COLUMN_NAME))
                age = cursor.getInt(cursor.getColumnIndex(DbDetails.UserDetails.COLUMN_AGE))
                sex = cursor.getString(cursor.getColumnIndex(DbDetails.UserDetails.COLUMN_SEX))

                users.add(User(userName,age,sex))
                cursor.moveToNext()
            }
        }
        return users
    }

    fun searchFromDb(value: String): ArrayList<User> {

        val users =ArrayList<User>()
        val db = writableDatabase
        var cursor: Cursor? = null
        try{

            cursor = db.rawQuery("select * from " + DbDetails.UserDetails.TABLE_NAME + " WHERE " + DbDetails.UserDetails.COLUMN_NAME + "='" + value + "'", null)

        }catch(e: SQLiteException){

            db.execSQL(SQL_CREATE_ENTRIES)
            return ArrayList()
        }

        var userName: String
        var age: Int
        var sex: String
        if (cursor!!.moveToFirst()) {
            while (cursor.isAfterLast == false) {
                userName = cursor.getString(cursor.getColumnIndex(DbDetails.UserDetails.COLUMN_NAME))
                age = cursor.getInt(cursor.getColumnIndex(DbDetails.UserDetails.COLUMN_AGE))
                sex = cursor.getString(cursor.getColumnIndex(DbDetails.UserDetails.COLUMN_SEX))

                users.add(User(userName,age,sex))
                cursor.moveToNext()
            }
        }
        return users
    }

}