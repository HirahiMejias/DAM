package com.example.proyectoandroid

import android.content.ActivityNotFoundException
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.speech.RecognizerIntent
import android.view.View

class AltaActivity : AppCompatActivity() {

    private lateinit var etFechaInicio: EditText
    private lateinit var etFechaFin: EditText
    private lateinit var etCentro: EditText
    private lateinit var etNumComensales: EditText
    private lateinit var spinnerServicio: Spinner
    private lateinit var dbHelper: AdminSQLiteOpenHelper
    private lateinit var buttonEnviar: Button
    private lateinit var btnSalir: Button
    private lateinit var buttonVozAlta: Button

    private lateinit var gestureDetector: GestureDetector

    private val RECOGNIZER_RESULT = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alta)

        // Inicializar componentes de la vista
        etFechaInicio = findViewById(R.id.etFechaInicio)
        etFechaFin = findViewById(R.id.etFechaFin)
        etCentro = findViewById(R.id.etNombreCentro)
        etNumComensales = findViewById(R.id.etNumComensales)
        spinnerServicio = findViewById(R.id.spTipoServicio)
        buttonEnviar = findViewById(R.id.btnEnviar)
        buttonVozAlta = findViewById(R.id.buttonVozAlta)

        // Inicializar la base de datos
        dbHelper = AdminSQLiteOpenHelper(this)

        // Configurar el Spinner de tipo de servicio
        val servicios = arrayOf("Desayuno", "Almuerzo", "Merienda", "Cena")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, servicios)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerServicio.adapter = adapter

        // Inicializar GestureDetector
        gestureDetector = GestureDetector(this, GestureListener(this))

        // Configurar el toque en la vista principal para detectar gestos
        val rootView = findViewById<View>(android.R.id.content)
        rootView.setOnTouchListener { v, event ->
            gestureDetector.onTouchEvent(event)
            true
        }

        // Configurar el botón de reconocimiento de voz
        buttonVozAlta.setOnClickListener {
            iniciarReconocimientoVoz()
        }

        // Configurar el botón para insertar servicio
        buttonEnviar.setOnClickListener {
            // Obtener los valores de los campos
            val fechaInicio = etFechaInicio.text.toString()
            val fechaFin = etFechaFin.text.toString()
            val tipoServicio = spinnerServicio.selectedItem.toString()
            val numComensales = etNumComensales.text.toString().toIntOrNull() ?: 0
            val centro = etCentro.text.toString()

            // Verificar que todos los campos están llenos
            if (fechaInicio.isNotEmpty() && fechaFin.isNotEmpty() && centro.isNotEmpty()) {
                // Insertar servicio en la base de datos (con idUsuario = 1 por ejemplo)
                val idUsuario = intent.getIntExtra("idUsuario", -1)  // Obtén el idUsuario pasado desde la actividad anterior

                val result = dbHelper.insertarServicio(idUsuario, fechaInicio, fechaFin, tipoServicio, numComensales, centro)

                if (result != -1L) {
                    Toast.makeText(this, "Servicio guardado correctamente", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Error al guardar el servicio", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }

        // Configurar el botón de salir
        btnSalir = findViewById(R.id.btnSalir)
        btnSalir.setOnClickListener {
            finish()
        }
    }

    // Método para iniciar el reconocimiento de voz
    private fun iniciarReconocimientoVoz() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Habla ahora")

        try {
            startActivityForResult(intent, RECOGNIZER_RESULT)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(this, "El reconocimiento de voz no está disponible", Toast.LENGTH_SHORT).show()
        }
    }

    // Manejar el resultado del reconocimiento de voz
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RECOGNIZER_RESULT && resultCode == RESULT_OK && data != null) {
            val resultados = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            val textoReconocido = resultados?.get(0) ?: ""

            // Rellenar los campos con el texto reconocido
            if (textoReconocido.isNotEmpty()) {
                etCentro.setText(textoReconocido)  // Suponiendo que la cuarta palabra es el número de comensales
            }
        }
    }

    // Clase interna para gestionar los gestos
    private class GestureListener(private val activity: AltaActivity) : GestureDetector.SimpleOnGestureListener() {
        override fun onFling(
            e1: MotionEvent?,
            e2: MotionEvent,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            if (Math.abs(velocityX) > Math.abs(velocityY)) {
                if (velocityX < 0) {
                    // Deslizar hacia la izquierda (volver a la actividad anterior)
                    activity.finish()  // Cierra la actividad
                }
            }
            return true
        }

        override fun onScroll(
            e1: MotionEvent?,
            e2: MotionEvent,
            distanceX: Float,
            distanceY: Float
        ): Boolean {
            // Detectar desplazamiento
            return super.onScroll(e1, e2, distanceX, distanceY)
        }
    }
}
