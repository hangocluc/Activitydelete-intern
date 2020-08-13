package com.example.kotlinthemsuaxoa.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.core.content.contentValuesOf
import com.example.kotlinthemsuaxoa.them.Student

class DataDAO {
    private var data : Data
    constructor(context: Context){
      data = Data(context)

    }
    fun insert(student: Student): Long {
        val sql:SQLiteDatabase =data.writableDatabase
        val  contentValue:ContentValues = ContentValues()
        contentValue.put("id",student.id)
        contentValue.put("name",student.name)
        val  resutl = sql.insert("luc",null,contentValue)
        sql.close()
     return resutl
    }
    fun getAll():List<Student>{
        var list: ArrayList<Student> = ArrayList()
        val sql:SQLiteDatabase = data.readableDatabase
        val select:String = "Select * from luc"
        val cursor:Cursor = sql.rawQuery(select,null)

        if(cursor.moveToFirst()){
            do{
             var  id=cursor.getInt(cursor.getColumnIndex("id"))
             var  name= cursor.getString(cursor.getColumnIndex("name"))
                var student:Student = Student(id,name)
               list.add(student)
            }while (cursor.moveToNext());
            cursor.close()
        }
        sql.close()
        return list
    }

    fun update(student: Student): Int {
        val sql:SQLiteDatabase = data.writableDatabase
        var contentValues:ContentValues = ContentValues()
        contentValues.put("name",student.name)
        var resutl = sql.update("luc",contentValues,"id"+"=?", arrayOf(student.id.toString()))
        sql.close()
        return resutl



    }
    fun delete(student: Student): Int {
        val sql:SQLiteDatabase = data.writableDatabase
        val resutl = sql.delete("luc","id "+"=?", arrayOf(student.id.toString()))
        sql.close()
        println("${student.id} $resutl")
        return resutl

    }
}