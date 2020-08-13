package com.example.kotlinthemsuaxoa.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class Data:SQLiteOpenHelper {
    val context:Context
    val table :String = "Create table luc (id text primary key ,name text )"

    constructor(context: Context):super(context,"data1.db",null,1){
      this.context = context
  }


    override fun onCreate(db: SQLiteDatabase?) {
       db?.execSQL(table)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

}