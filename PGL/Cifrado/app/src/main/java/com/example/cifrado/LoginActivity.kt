package com.example.cifrado

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoginActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val nombreUsuario = findViewById<EditText>(R.id.etNombreUsuario)
        val contrasenya = findViewById<EditText>(R.id.etContrasenya)
        val botonLogin = findViewById<Button>(R.id.btnLogin)
        val botonSignIn = findViewById<Button>(R.id.btnSignIn)


        botonLogin.setOnClickListener {
            val admin = AdminSQLiteOpenHelper(this, "usuarios", null, 1)
            val db = admin.readableDatabase
            val usuario = nombreUsuario.text.toString().trim()
            val password = contrasenya.text.toString().trim()
            if (usuario.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Por favor, introduce el usuario y la contraseña", Toast.LENGTH_SHORT).show()
            } else {
                val cursor = db.rawQuery(
                    "SELECT * FROM USUARIOS WHERE nombreUsuario = ? AND contrasenya = ?",
                    arrayOf(usuario, password)
                )

                if (cursor.moveToFirst()) {
                    Toast.makeText(this, "Login exitoso", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
                }

                cursor.close()
            }
            db.close()
        }

        botonSignIn.setOnClickListener {
            val admin = AdminSQLiteOpenHelper(this, "usuarios", null, 1)
            val db = admin.writableDatabase
            val usuario = nombreUsuario.text.toString().trim()
            val password = contrasenya.text.toString().trim()

            if (usuario.isEmpty() || password.isEmpty()) {
                Toast.makeText(
                    this,
                    "Por favor, introduce el usuario y la contraseña",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                val cursor = db.rawQuery(
                    "SELECT * FROM USUARIOS WHERE nombreUsuario = ?",
                    arrayOf(usuario)
                )
                if (cursor.moveToFirst()) {
                    Toast.makeText(this, "El nombre de usuario ya está en uso", Toast.LENGTH_SHORT)
                        .show()
                } else if (password.length < 8 || !password.matches(".*[A-Z].*".toRegex()) || !password.matches(
                        ".*[0-9].*".toRegex()
                    )
                ) {
                    Toast.makeText(
                        this,
                        "La contraseña debe tener al menos 8 caracteres, incluir letras, números y al menos una mayúscula",
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    val registro = ContentValues().apply {
                        put("nombreUsuario", usuario)
                        put("contrasenya", password)
                    }

                    db.insert("USUARIOS", null, registro)
                    Toast.makeText(this, "Usuario registrado con éxito", Toast.LENGTH_SHORT).show()

                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }

                cursor.close()
            }
            db.close()
        }
    }

}



