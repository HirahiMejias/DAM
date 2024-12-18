package com.example.libros

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnAltaLibro = findViewById<Button>(R.id.btnAltaLibro)
        val btnConsultarLibro = findViewById<Button>(R.id.btnConsultarLibro)
        val btnReservarLibro = findViewById<Button>(R.id.btnReservarLibro)
        val btnDevolverLibro = findViewById<Button>(R.id.btnDevolverLibro)

        btnAltaLibro.setOnClickListener {
            startActivity(Intent(this, AltaLibroActivity::class.java))
        }
        btnConsultarLibro.setOnClickListener {
            startActivity(Intent(this, ConsultaLibroActivity::class.java))
        }
        btnReservarLibro.setOnClickListener {
            startActivity(Intent(this, ReservaActivity::class.java))
        }
        btnDevolverLibro.setOnClickListener {
            startActivity(Intent(this, DevolverLibroActivity::class.java))
        }
    }
}
