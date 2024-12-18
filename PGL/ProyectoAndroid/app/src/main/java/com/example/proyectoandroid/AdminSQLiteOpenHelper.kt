package com.example.proyectoandroid

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class AdminSQLiteOpenHelper(context: Context) : SQLiteOpenHelper(context, "usuariosDB", null, 3) {

    override fun onCreate(db: SQLiteDatabase) {
        try {
            // Crear la tabla de usuarios
            db.execSQL(
                "CREATE TABLE usuarios (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "usuario TEXT UNIQUE, " +
                        "contrasenya TEXT)"
            )

            // Crear la tabla de servicios
            db.execSQL(
                "CREATE TABLE servicios (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "id_usuario INTEGER, " +
                        "fecha_inicio TEXT, " +
                        "fecha_fin TEXT, " +
                        "tipo_servicio TEXT, " +
                        "num_comensales INTEGER, " +
                        "centro TEXT, " +
                        "FOREIGN KEY(id_usuario) REFERENCES usuarios(id))"
            )

            // Crear la tabla de perfil de usuario
            db.execSQL(
                "CREATE TABLE perfil_usuario (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "id_usuario INTEGER, " +
                        "nombre TEXT, " +
                        "apellido TEXT, " +
                        "genero TEXT, " +
                        "edad INTEGER, " +
                        "foto_perfil TEXT, " + // URI de la imagen
                        "FOREIGN KEY(id_usuario) REFERENCES usuarios(id))"
            )

            Log.d("DB", "Tablas creadas correctamente")
        } catch (e: Exception) {
            Log.e("DB", "Error al crear las tablas: ${e.message}")
        }
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS usuarios")
        db.execSQL("DROP TABLE IF EXISTS servicios")
        db.execSQL("DROP TABLE IF EXISTS perfil_usuario") // Eliminar la tabla de perfil de usuario
        onCreate(db)
    }

    // Función para insertar un nuevo servicio
    fun insertarServicio(idUsuario: Int, fechaInicio: String, fechaFin: String, tipoServicio: String, numComensales: Int, centro: String): Long {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("id_usuario", idUsuario)
            put("fecha_inicio", fechaInicio)
            put("fecha_fin", fechaFin)
            put("tipo_servicio", tipoServicio)
            put("num_comensales", numComensales)
            put("centro", centro)
        }

        // Inserta el servicio en la base de datos y retorna el ID del nuevo registro
        return db.insert("servicios", null, values)
    }




    // Función para obtener los servicios de un usuario
    fun obtenerServiciosPorUsuario(idUsuario: Int): List<Map<String, String>> {
        val db = readableDatabase
        val cursor = db.rawQuery(
            "SELECT * FROM servicios WHERE id_usuario = ?",
            arrayOf(idUsuario.toString())
        )

        val servicios = mutableListOf<Map<String, String>>()

        if (cursor.moveToFirst()) {
            do {
                val servicio = mapOf(
                    "ubicacion" to cursor.getString(cursor.getColumnIndex("centro")),
                    "fechaInicio" to cursor.getString(cursor.getColumnIndex("fecha_inicio")),
                    "tipoServicio" to cursor.getString(cursor.getColumnIndex("tipo_servicio")),
                    "comensales" to cursor.getString(cursor.getColumnIndex("num_comensales")),
                )
                servicios.add(servicio)
            } while (cursor.moveToNext())
        }

        cursor.close()
        return servicios
    }

    // Función para insertar los datos del perfil de usuario
    fun insertarPerfilUsuario(idUsuario: Int, nombre: String, apellido: String, genero: String, edad: Int, fotoPerfil: String): Long {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("id_usuario", idUsuario)
            put("nombre", nombre)
            put("apellido", apellido)
            put("genero", genero)
            put("edad", edad)
            put("foto_perfil", fotoPerfil)
        }

        // Inserta los datos del perfil en la base de datos
        return db.insert("perfil_usuario", null, values)
    }

    // Función para obtener los datos del perfil de usuario
    fun obtenerPerfilUsuario(idUsuario: Int): Map<String, String>? {
        val db = readableDatabase
        val cursor = db.rawQuery(
            "SELECT * FROM perfil_usuario WHERE id_usuario = ?",
            arrayOf(idUsuario.toString())
        )

        var perfil: Map<String, String>? = null

        if (cursor.moveToFirst()) {
            perfil = mapOf(
                "nombre" to cursor.getString(cursor.getColumnIndex("nombre")),
                "apellido" to cursor.getString(cursor.getColumnIndex("apellido")),
                "genero" to cursor.getString(cursor.getColumnIndex("genero")),
                "edad" to cursor.getInt(cursor.getColumnIndex("edad")).toString(),
                "foto_perfil" to cursor.getString(cursor.getColumnIndex("foto_perfil"))
            )
        }

        cursor.close()
        return perfil
    }
    fun actualizarPerfilUsuario(idUsuario: Int, nombre: String, apellido: String, genero: String, edad: Int, fotoPerfil: String) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("nombre", nombre)
            put("apellido", apellido)
            put("genero", genero)
            put("edad", edad)
            put("foto_perfil", fotoPerfil)
        }
        db.update(
            "perfil_usuario", values, "id_usuario = ?",
            arrayOf(idUsuario.toString())
        )
    }

    // Función para verificar si el usuario ya existe
    fun usuarioExiste(usuario: String): Boolean {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT COUNT(*) FROM usuarios WHERE usuario = ?", arrayOf(usuario))
        val existe = if (cursor.moveToFirst()) cursor.getInt(0) > 0 else false
        cursor.close()
        return existe
    }

    // Función para registrar un nuevo usuario
    fun registrarUsuario(usuario: String, contrasenya: String): Long {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("usuario", usuario)
            put("contrasenya", contrasenya) // Contraseña tal cual
        }
        return db.insert("usuarios", null, values) // Insertar el usuario en la base de datos
    }

    fun verificarLogin(usuario: String, contrasenya: String): Int {
        val db = readableDatabase
        val cursor = db.rawQuery(
            "SELECT id FROM usuarios WHERE usuario = ? AND contrasenya = ?",
            arrayOf(usuario, contrasenya)
        )

        // Si encuentra un usuario con esas credenciales, devuelve el id
        return if (cursor.moveToFirst()) {
            cursor.getInt(cursor.getColumnIndex("id"))
        } else {
            0 // Si no hay coincidencias, devuelve 0
        }.also {
            cursor.close()
        }
    }



}
