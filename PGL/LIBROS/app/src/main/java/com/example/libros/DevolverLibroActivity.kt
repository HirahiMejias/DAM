package com.example.libros

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class DevolverLibroActivity : AppCompatActivity() {

    private lateinit var isbnEditText: EditText
    private lateinit var devolverButton: Button
    private lateinit var resultadoTextView: TextView
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_devolver_libro)

        isbnEditText = findViewById(R.id.isbnEditText)
        devolverButton = findViewById(R.id.devolverButton)
        resultadoTextView = findViewById(R.id.resultadoTextView)
        dbHelper = DatabaseHelper(this)

        devolverButton.setOnClickListener {
            val isbn = isbnEditText.text.toString()

            // Comprobar si el ISBN está vacío
            if (isbn.isEmpty()) {
                Toast.makeText(this, "Por favor, introduce un ISBN.", Toast.LENGTH_SHORT).show()
            } else {
                // Consultar libro en la base de datos
                val libro = dbHelper.consultarLibro(isbn)
                if (libro != null) {
                    // Comprobar si se puede devolver el libro
                    if (libro.ejemplaresActual >= libro.ejemplaresTotales) {
                        Toast.makeText(this, "Error: No se puede devolver el libro, ya que tiene el máximo de ejemplares.", Toast.LENGTH_SHORT).show()
                    } else {
                        // Actualizar ejemplares actuales y devolver libro
                        val nuevosEjemplaresActual = libro.ejemplaresActual + 1
                        val actualizado = dbHelper.actualizarLibroEjemplares(isbn, nuevosEjemplaresActual, if (nuevosEjemplaresActual > 0) "Disponible" else "No Disponible")

                        if (actualizado) {
                            Toast.makeText(this, "Libro devuelto exitosamente.", Toast.LENGTH_SHORT).show()
                            resultadoTextView.text = "Libro devuelto: ${libro.titulo}"
                        } else {
                            Toast.makeText(this, "Error al devolver el libro.", Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    // ISBN no encontrado
                    Toast.makeText(this, "Error: ISBN no encontrado.", Toast.LENGTH_SHORT).show()
                    resultadoTextView.text = ""
                }
            }
        }
    }
}
