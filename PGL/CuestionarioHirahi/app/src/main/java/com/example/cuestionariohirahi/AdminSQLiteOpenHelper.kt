package com.example.cuestionariohirahi

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteDatabase.CursorFactory
import android.database.sqlite.SQLiteOpenHelper

class AdminSQLiteOpenHelper (context:Context) : SQLiteOpenHelper(context,"usuariosDB",null,2){
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("create table usuarios(usuario text primary key,contrasenya text,puntuacion integer)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion:Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}