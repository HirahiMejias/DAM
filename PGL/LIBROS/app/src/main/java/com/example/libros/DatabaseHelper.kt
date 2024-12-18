package com.example.libros

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

data class Libro(val isbn: String, val titulo: String, val autor: String, val ejemplaresTotales: Int, val ejemplaresActual: Int, val disponible: String)

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, "biblioteca.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            "CREATE TABLE Libros (ISBN TEXT PRIMARY KEY, Titulo TEXT, Autor TEXT, EjemplaresTotales INTEGER, EjemplaresActual INTEGER, Disponible TEXT)"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS Libros")
        onCreate(db)
    }

    fun insertarLibro(libro: Libro): Boolean {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put("ISBN", libro.isbn)
        values.put("Titulo", libro.titulo)
        values.put("Autor", libro.autor)
        values.put("EjemplaresTotales", libro.ejemplaresTotales)
        values.put("EjemplaresActual", libro.ejemplaresActual)
        values.put("Disponible", libro.disponible)

        val result = db.insert("Libros", null, values)
        db.close()
        return result.toInt() != -1
    }

    fun existeLibro(isbn: String): Boolean {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM Libros WHERE ISBN = ?", arrayOf(isbn))
        val exists = cursor.moveToFirst() // Intenta mover el cursor a la primera fila
        cursor.close()
        db.close()
        return exists // Devuelve true si hay al menos un registro, false si no
    }

    fun consultarLibro(isbn: String): Libro? {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM Libros WHERE ISBN = ?", arrayOf(isbn))
        return if (cursor.moveToFirst()) {
            val titulo = cursor.getString(cursor.getColumnIndexOrThrow("Titulo"))
            val autor = cursor.getString(cursor.getColumnIndexOrThrow("Autor"))
            val ejemplaresTotales = cursor.getInt(cursor.getColumnIndexOrThrow("EjemplaresTotales"))
            val ejemplaresActual = cursor.getInt(cursor.getColumnIndexOrThrow("EjemplaresActual"))
            val disponible = cursor.getString(cursor.getColumnIndexOrThrow("Disponible"))
            cursor.close()
            Libro(isbn, titulo, autor, ejemplaresTotales, ejemplaresActual, disponible)
        } else {
            cursor.close()
            null
        }
    }

    fun actualizarLibroEjemplares(isbn: String, ejemplaresActual: Int, disponible: String): Boolean {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put("EjemplaresActual", ejemplaresActual)
        values.put("Disponible", disponible)

        val result = db.update("Libros", values, "ISBN = ?", arrayOf(isbn))
        db.close()
        return result > 0
    }
}
