package com.example.proyectoandroid

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.util.Locale

class MenuActivity : AppCompatActivity() {

    private val SPEECH_REQUEST_CODE = 1
    private lateinit var tvcomando: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        // Obtener el idUsuario del Intent
        val idUsuario = intent.getIntExtra("idUsuario", 0)

        val buttonAltaServicio: Button = findViewById(R.id.buttonAltaServicio)
        val buttonMostrarServicios: Button = findViewById(R.id.buttonMostrarServicios)
        val buttonMostrarMenu: Button = findViewById(R.id.buttonMostrarMenu)
        val buttonPerfil: ImageButton = findViewById(R.id.buttonPerfil)
        val buttonVoz: Button = findViewById(R.id.buttonVoz) // Botón para activar la voz

        tvcomando = findViewById(R.id.tvComando)

        // Navegación con botones
        buttonAltaServicio.setOnClickListener {
            val intent = Intent(this, AltaActivity::class.java)
            intent.putExtra("idUsuario", idUsuario) // Pasar el idUsuario a la siguiente actividad
            startActivity(intent)
        }

        buttonMostrarServicios.setOnClickListener {
            val intent = Intent(this, MostrarActivity::class.java)
            intent.putExtra("idUsuario", idUsuario) // Pasar el idUsuario a la siguiente actividad
            startActivity(intent)
        }

        buttonMostrarMenu.setOnClickListener {
            val intent = Intent(this, MenuMesActivity::class.java)
            intent.putExtra("idUsuario", idUsuario) // Pasar el idUsuario a la siguiente actividad
            startActivity(intent)
        }

        buttonPerfil.setOnClickListener {
            val intent = Intent(this, PerfilActivity::class.java)
            intent.putExtra("idUsuario", idUsuario) // Pasar el idUsuario a la siguiente actividad
            startActivity(intent)
        }

        // Reconocimiento de voz
        buttonVoz.setOnClickListener {
            startSpeechToText()
        }
    }

    private fun isSpeechRecognitionAvailable(): Boolean {
        val packageManager = packageManager
        val activity = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        val activities = packageManager.queryIntentActivities(activity, PackageManager.MATCH_DEFAULT_ONLY)
        return activities.isNotEmpty()
    }

    private fun startSpeechToText() {
        if (isSpeechRecognitionAvailable()) {
            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
                putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
                putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
                putExtra(RecognizerIntent.EXTRA_PROMPT, "Habla ahora para transcribir tu voz")
            }

            try {
                startActivityForResult(intent, SPEECH_REQUEST_CODE)
            } catch (e: Exception) {
                Toast.makeText(this, "El reconocimiento de voz no está disponible", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "El reconocimiento de voz no está disponible en este dispositivo", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == SPEECH_REQUEST_CODE && resultCode == RESULT_OK) {
            val result = data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            result?.let {
                tvcomando.text = it[0] // Mostramos el primer resultado
                procesarComandoVoz(it[0])  // Procesamos el comando de voz
            }
        }
    }

    private fun procesarComandoVoz(comando: String) {
        val idUsuario = intent.getIntExtra("idUsuario", 0) // Obtenemos el idUsuario

        when {
            comando.contains("perfil", true) -> {
                val intent = Intent(this, PerfilActivity::class.java)
                intent.putExtra("idUsuario", idUsuario)
                startActivity(intent)
            }
            comando.contains("alta servicio", true) -> {
                val intent = Intent(this, AltaActivity::class.java)
                intent.putExtra("idUsuario", idUsuario)
                startActivity(intent)
            }
            comando.contains("mostrar servicios", true) -> {
                val intent = Intent(this, MostrarActivity::class.java)
                intent.putExtra("idUsuario", idUsuario)
                startActivity(intent)
            }
            comando.contains("menú mensual", true) -> {
                val intent = Intent(this, MenuMesActivity::class.java)
                intent.putExtra("idUsuario", idUsuario)
                startActivity(intent)
            }
            else -> Toast.makeText(this, "Comando no reconocido: $comando", Toast.LENGTH_SHORT).show()
        }
    }
}
