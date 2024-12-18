package com.example.libros

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class ConsultaLibroActivity : AppCompatActivity() {
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consulta_libro)

        databaseHelper = DatabaseHelper(this)

        val isbnEditText = findViewById<EditText>(R.id.edtISBNConsulta)
        val consultarButton = findViewById<Button>(R.id.btnConsultar)
        val resultadoTextView = findViewById<TextView>(R.id.txtResultadoConsulta)

        consultarButton.setOnClickListener {
            val isbn = isbnEditText.text.toString()

            // Comprobar si el ISBN está vacío
            if (isbn.isEmpty()) {
                Toast.makeText(this, "Por favor, introduce un ISBN.", Toast.LENGTH_SHORT).show()
            } else {
                // Consultar libro en la base de datos
                val libro = databaseHelper.consultarLibro(isbn)
                if (libro != null) {
                    resultadoTextView.text = "Título: ${libro.titulo}\nAutor: ${libro.autor}\n" +
                            "Ejemplares Totales: ${libro.ejemplaresTotales}\n" +
                            "Ejemplares Actuales: ${libro.ejemplaresActual}\n" +
                            "Disponibilidad: ${libro.disponible}"
                } else {
                    // ISBN no encontrado
                    Toast.makeText(this, "Error: ISBN no encontrado.", Toast.LENGTH_SHORT).show()
                    resultadoTextView.text = ""
                }
            }
        }
    }
}