package com.example.libros

import android.content.ContentValues
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class AltaLibroActivity : AppCompatActivity() {

    private lateinit var isbnEditText: EditText
    private lateinit var tituloEditText: EditText
    private lateinit var autorEditText: EditText
    private lateinit var ejemplaresTotalesEditText: EditText
    private lateinit var ejemplaresActualEditText: EditText
    private lateinit var disponibleRadioButton: RadioButton
    private lateinit var noDisponibleRadioButton: RadioButton
    private lateinit var guardarButton: Button
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alta_libro)

        isbnEditText = findViewById(R.id.edtISBN)
        tituloEditText = findViewById(R.id.edtTitulo)
        autorEditText = findViewById(R.id.edtAutor)
        ejemplaresTotalesEditText = findViewById(R.id.edtEjemplaresTotales)
        ejemplaresActualEditText = findViewById(R.id.edtEjemplaresActual)
        disponibleRadioButton = findViewById(R.id.radioDisponible)
        noDisponibleRadioButton = findViewById(R.id.radioNoDisponible)
        guardarButton = findViewById(R.id.btnAgregar)

        dbHelper = DatabaseHelper(this)

        guardarButton.setOnClickListener {
            val isbn = isbnEditText.text.toString()
            val titulo = tituloEditText.text.toString()
            val autor = autorEditText.text.toString()
            val ejemplaresTotales = ejemplaresTotalesEditText.text.toString().toIntOrNull() ?: 0
            val ejemplaresActual = ejemplaresActualEditText.text.toString().toIntOrNull() ?: 0
            val disponibilidad = if (disponibleRadioButton.isChecked) "Disponible" else "No Disponible"

            // Comprobar si el ISBN ya existe en la base de datos
            if (dbHelper.existeLibro(isbn)) {
                Toast.makeText(this, "Error: El ISBN ya existe en la base de datos.", Toast.LENGTH_SHORT).show()
            } else if ((ejemplaresActual > 0 && disponibilidad == "No Disponible") ||
                (ejemplaresActual == 0 && disponibilidad == "Disponible")) {
                // Validación de coherencia de disponibilidad
                Toast.makeText(this, "Error: La disponibilidad no es coherente con el número de ejemplares actuales.", Toast.LENGTH_SHORT).show()
            } else {
                // Crear objeto Libro y agregarlo en la base de datos
                val libro = Libro(isbn, titulo, autor, ejemplaresTotales, ejemplaresActual, disponibilidad)
                val insertado = dbHelper.insertarLibro(libro)

                if (insertado) {
                    Toast.makeText(this, "Libro agregado exitosamente.", Toast.LENGTH_SHORT).show()
                    limpiarCampos()
                } else {
                    Toast.makeText(this, "Error al agregar el libro.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    // Método para limpiar los campos después de la inserción exitosa
    private fun limpiarCampos() {
        isbnEditText.text.clear()
        tituloEditText.text.clear()
        autorEditText.text.clear()
        ejemplaresTotalesEditText.text.clear()
        ejemplaresActualEditText.text.clear()
        disponibleRadioButton.isChecked = true
    }
}
