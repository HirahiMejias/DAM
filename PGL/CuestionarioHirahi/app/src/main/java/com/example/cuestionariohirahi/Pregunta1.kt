package com.example.cuestionariohirahi

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Pregunta1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pregunta1)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btnSiguiente = findViewById<Button>(R.id.btnAvanzar)
        val spinner = findViewById<Spinner>(R.id.spinner1)
        val opcionesSpinner1 = arrayOf("París", "Alemania", "Italia", "España")
        val adaptadorSpinner1= ArrayAdapter(this, android.R.layout.simple_spinner_item, opcionesSpinner1)
        spinner.adapter = adaptadorSpinner1
        val respuestaCorrecta = "París"
        val bundle = intent.extras
        var nota = bundle?.getInt("Nota") ?:0
        var nombre = bundle?.getString("nombre")

        btnSiguiente.setOnClickListener{

            if (respuestaCorrecta == spinner.selectedItem.toString()) {
                // Incrementar la nota
                nota += 1
            }
                cambiarActividad(nota,nombre!!)
        }
    }
    fun cambiarActividad(nota:Int,nombre:String){
        val intento = Intent(this,Pregunta2::class.java)
        intento.putExtra("Nota",nota)
        intento.putExtra("nombre",nombre)
        startActivity(intento)
    }

}