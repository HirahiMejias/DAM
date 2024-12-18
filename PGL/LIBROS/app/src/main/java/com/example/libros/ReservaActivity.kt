package com.example.libros

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ReservaActivity : AppCompatActivity() {
    private lateinit var isbnEditText: EditText
    private lateinit var reservarButton: Button
    private lateinit var resultadoTextView: TextView
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reserva)

        isbnEditText = findViewById(R.id.isbnEditText)
        reservarButton = findViewById(R.id.reservarButton)
        resultadoTextView = findViewById(R.id.resultadoTextView)
        dbHelper = DatabaseHelper(this)

        reservarButton.setOnClickListener {
            val isbn = isbnEditText.text.toString()

            // Comprobar si el ISBN está vacío
            if (isbn.isEmpty()) {
                Toast.makeText(this, "Por favor, introduce un ISBN.", Toast.LENGTH_SHORT).show()
            } else {
                // Consultar libro en la base de datos
                val libro = dbHelper.consultarLibro(isbn)
                if (libro != null) {
                    // Comprobar si se puede reservar el libro
                    if (libro.ejemplaresActual <= 0) {
                        Toast.makeText(this, "Error: No se puede reservar el libro, ya que no hay ejemplares disponibles.", Toast.LENGTH_SHORT).show()
                    } else {
                        // Actualizar ejemplares actuales y reservar libro
                        val nuevosEjemplaresActual = libro.ejemplaresActual - 1
                        val actualizado = dbHelper.actualizarLibroEjemplares(isbn, nuevosEjemplaresActual, if (nuevosEjemplaresActual > 0) "Disponible" else "No Disponible")

                        if (actualizado) {
                            Toast.makeText(this, "Libro reservado exitosamente.", Toast.LENGTH_SHORT).show()
                            resultadoTextView.text = "Libro reservado: ${libro.titulo}"
                        } else {
                            Toast.makeText(this, "Error al reservar el libro.", Toast.LENGTH_SHORT).show()
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

