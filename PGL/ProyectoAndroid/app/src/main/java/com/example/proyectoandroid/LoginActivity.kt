package com.example.proyectoandroid

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val usuarioField: EditText = findViewById(R.id.usuarioField)
        val contrasenyaField: EditText = findViewById(R.id.contraseñaField)
        val loginButton: Button = findViewById(R.id.loginButton)
        val registrarButton: Button = findViewById(R.id.registrarButton)

        val dbHelper = AdminSQLiteOpenHelper(this)

        loginButton.setOnClickListener {
            val usuario = usuarioField.text.toString()
            val contrasenya = contrasenyaField.text.toString()

            // Validar los campos
            if (usuario.isEmpty() || contrasenya.isEmpty()) {
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
            } else {
                // Verificar si las credenciales son correctas usando el método de AdminSQLiteOpenHelper
                val idUsuario = dbHelper.verificarLogin(usuario, contrasenya)

                if (idUsuario != 0) {
                    // Si el login es exitoso, pasamos el idUsuario a la siguiente actividad
                    val intent = Intent(this, MenuActivity::class.java)
                    intent.putExtra("idUsuario", idUsuario)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
                }
            }
        }

        registrarButton.setOnClickListener {
            val intent = Intent(this, RegistrarseActivity::class.java)
            startActivity(intent)
        }
    }
}
