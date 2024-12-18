package com.example.cuestionariohirahi

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Pregunta3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pregunta3)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val bundle = intent.extras
        val nota = bundle?.getInt("Nota") ?: 0
        val nombre = bundle?.getString("nombre")
        val pregunta3texto = findViewById<TextView>(R.id.pregunta3)
        pregunta3texto.text = nota.toString()
        val btnSiguiente = findViewById<Button>(R.id.btnVolver)
        btnSiguiente.setOnClickListener {
            cambiarActividad(nota,nombre!!)
            EscribirNota(nombre,nota)
        }
    }
    fun cambiarActividad(nota: Int, nombre: String) {
        val intento = Intent(this, BienvenidaActivity::class.java)
        intento.putExtra("Nota", nota)
        intento.putExtra("nombre", nombre)
        startActivity(intento)
    }
    fun EscribirNota(nombre:String ,nota: Int){
        val admin = AdminSQLiteOpenHelper(this)
        val db = admin.writableDatabase
        val valores= ContentValues()
        valores.put("puntuacion",nota)
        val cursor = db.rawQuery("Select * from puntuacion where nombre=?",arrayOf(nombre))



        val can = db.update("puntuacion",valores,"nombre=?", arrayOf(nombre))
        if (can.toInt() == -1) {
            Toast.makeText(this, "Error al insertar", Toast.LENGTH_SHORT).show()
        }
        db.close()
    }
}