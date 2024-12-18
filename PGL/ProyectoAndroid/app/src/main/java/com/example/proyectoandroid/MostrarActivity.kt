package com.example.proyectoandroid

import android.os.Bundle
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent

class MostrarActivity : AppCompatActivity() {

    private lateinit var dbHelper: AdminSQLiteOpenHelper
    private lateinit var tablaServicios: TableLayout
    private lateinit var anyadirButton: Button
    private lateinit var volverButton: Button
    private var idUsuario: Int = 0
    private var scaleFactor = 1.0f  // Factor de escala inicial
    private lateinit var scaleGestureDetector: ScaleGestureDetector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostrar)

        // Inicializa las vistas
        anyadirButton = findViewById(R.id.anyadirButton)
        volverButton = findViewById(R.id.volverButton)
        tablaServicios = findViewById(R.id.tablaServicios)

        // Inicializa la base de datos
        dbHelper = AdminSQLiteOpenHelper(this)

        // Obtener el idUsuario pasado desde MenuActivity
        idUsuario = intent.getIntExtra("idUsuario", 0)

        // Obtener los servicios del usuario
        val servicios = dbHelper.obtenerServiciosPorUsuario(idUsuario)

        // Agregar las filas al TableLayout
        for (servicio in servicios) {
            val fila = TableRow(this)

            // Crear y agregar los TextViews a la fila
            val ubicacion = TextView(this).apply {
                text = servicio["ubicacion"]
                gravity = android.view.Gravity.LEFT
                setPadding(8, 8, 8, 8)
            }

            val fechaInicio = TextView(this).apply {
                text = servicio["fechaInicio"]
                gravity = android.view.Gravity.LEFT
                setPadding(8, 8, 8, 8)
            }

            val tipoServicio = TextView(this).apply {
                text = servicio["tipoServicio"]
                gravity = android.view.Gravity.LEFT
                setPadding(8, 8, 8, 8)
            }

            val comensales = TextView(this).apply {
                text = servicio["comensales"]
                gravity = android.view.Gravity.LEFT
                setPadding(8, 8, 8, 8)
            }

            // Agregar los TextViews a la fila
            fila.addView(ubicacion)
            fila.addView(fechaInicio)
            fila.addView(tipoServicio)
            fila.addView(comensales)

            // Agregar la fila al TableLayout
            tablaServicios.addView(fila)
        }

        // Inicializar ScaleGestureDetector para detectar el gesto de zoom
        scaleGestureDetector = ScaleGestureDetector(this, ScaleListener())

        // Configuración de los botones
        anyadirButton.setOnClickListener {
            val intent = Intent(this, AltaActivity::class.java)
            intent.putExtra("idUsuario", idUsuario)
            startActivity(intent)
        }

        volverButton.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }
    }

    // Escuchador de gestos de escala
    private inner class ScaleListener : ScaleGestureDetector.SimpleOnScaleGestureListener() {
        override fun onScale(detector: ScaleGestureDetector): Boolean {
            if (detector != null) {
                // Obtener el factor de escala
                scaleFactor *= detector.scaleFactor

                // Limitar el factor de escala (zoom máximo y mínimo)
                scaleFactor = scaleFactor.coerceIn(0.5f, 3.0f)

                // Aplicar la escala a todos los TextViews dentro de la tabla
                for (i in 0 until tablaServicios.childCount) {
                    val fila = tablaServicios.getChildAt(i) as TableRow
                    for (j in 0 until fila.childCount) {
                        val textView = fila.getChildAt(j) as TextView
                        textView.textSize = 14f * scaleFactor  // Escalar el tamaño del texto
                    }
                }
            }
            return true
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        // Pasar el evento al ScaleGestureDetector
        if (event != null) {
            scaleGestureDetector.onTouchEvent(event)
        }
        return super.onTouchEvent(event)
    }
}
