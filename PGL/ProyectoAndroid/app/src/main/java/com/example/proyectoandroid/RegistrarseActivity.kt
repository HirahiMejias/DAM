package com.example.proyectoandroid

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class RegistrarseActivity : AppCompatActivity() {

    private lateinit var dbHelper: AdminSQLiteOpenHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrarse)

        // Inicializar la base de datos
        dbHelper = AdminSQLiteOpenHelper(this)

        // Referencias a las vistas
        val usernameInput = findViewById<EditText>(R.id.usernameInput)
        val passwordInput = findViewById<EditText>(R.id.passwordInput)
        val confirmPasswordInput = findViewById<EditText>(R.id.confirmPasswordInput)
        val registerButton = findViewById<Button>(R.id.registerButton)
        val cancelButton = findViewById<Button>(R.id.cancelButton)

        // Configurar botón de registro
        registerButton.setOnClickListener {
            val username = usernameInput.text.toString().trim()
            val password = passwordInput.text.toString().trim()
            val confirmPassword = confirmPasswordInput.text.toString().trim()

            // Validaciones
            if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password != confirmPassword) {
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Verificar si el usuario ya existe
            if (dbHelper.usuarioExiste(username)) {
                Toast.makeText(this, "El usuario ya existe", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val userId = dbHelper.registrarUsuario(username, password)
            if (userId > 0) {
                Toast.makeText(this, "Usuario registrado exitosamente", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Error al registrar usuario", Toast.LENGTH_SHORT).show()
            }
        }

        // Configurar botón de cancelar
        cancelButton.setOnClickListener {
            finish()  // Cerrar la actividad de registro y volver atrás
        }
    }
}
