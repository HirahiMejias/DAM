package com.example.cuestionariohirahi

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Pregunta2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pregunta2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val Bundle = intent.extras
        var nota = Bundle?.getInt("Nota")?:0
        val btnSiguiente = findViewById<Button>(R.id.btnAvanzar2)
        val respuestaCorrecta = "1969"
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup2)
        val nombre = Bundle?.getString("nombre")


        btnSiguiente.setOnClickListener{
            val checkedRadioButton = findViewById<RadioButton>(radioGroup.checkedRadioButtonId)
            val respuesta = checkedRadioButton.text.toString()
            if (respuestaCorrecta == respuesta) {
                // Incrementar la nota
                nota += 1
            }
            cambiarActividad(nota,nombre!!)
        }



    }
    fun cambiarActividad(nota:Int,nombre:String){
        val intento = Intent(this,Pregunta3::class.java)
        intento.putExtra("Nota",nota)
        intento.putExtra("nombre",nombre)
        startActivity(intento)
    }
}