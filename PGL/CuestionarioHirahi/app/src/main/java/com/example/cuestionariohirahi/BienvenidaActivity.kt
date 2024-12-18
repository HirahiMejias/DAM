package com.example.cuestionariohirahi

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class BienvenidaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_bienvenida)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val tvResultadoNombre = findViewById<TextView>(R.id.tvNombreUsuario)
        val tvResultadoNota = findViewById<TextView>(R.id.tvNotaR)
        val btnComenzar = findViewById<Button>(R.id.btnComenzar)
        val Bundle = intent.extras
        val nota = Bundle?.getInt("Nota")
        val nombre = Bundle?.getString("nombre")
        tvResultadoNombre.text = nombre
        tvResultadoNota.text= nota.toString()

        btnComenzar.setOnClickListener{
            if (nota != null) {
                cambiarActivity(nota,nombre!!)
            }
        }
    }
    fun cambiarActivity(nota: Int,nombre:String){
        val intento = Intent(this,Pregunta1::class.java)
        intento.putExtra("Nota",nota)
        intento.putExtra("nombre",nombre)
        startActivity(intento)
    }
}