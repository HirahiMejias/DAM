package com.example.cifrado
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val botonEncriptar = findViewById<Button>(R.id.btEncriptar)
        val botonDesencrip = findViewById<Button>(R.id.btDesencriptar)
        val palabra = findViewById<EditText>(R.id.et1)
        val spinner = findViewById<Spinner>(R.id.spinner)
        val lista = arrayOf("Encriptado1", "Encriptado2", "Encriptado3")
        val adap = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lista)
        spinner.adapter = adap
        var selectedEncryptionMethod = ""
        botonEncriptar.setOnClickListener {
            val texto = palabra.text.toString()
            val textoEncriptado = when
                                          (spinner.selectedItem.toString()) {
                "Encriptado1" -> encriptarTexto(texto)
                "Encriptado2" -> encriptarTextoMetodo2(texto)
                "Encriptado3" -> encriptarTextoMetodo3(texto)
                else -> texto
            }
            val intentoEncrip = Intent(this, MainActivity2::class.java)
            intentoEncrip.putExtra("textoEncriptado", textoEncriptado)
            startActivity(intentoEncrip)
        }
        botonDesencrip.setOnClickListener() {
            val texto = palabra.text.toString()
            11
            val textoDesencrip = when (spinner.selectedItem.toString()) {
                "Encriptado1" -> desencriptarTextoMetodo1(texto)
                "Encriptado2" -> desencriptarTextoMetodo2(texto)
                "Encriptado3" -> desencriptarTextoMetodo3(texto)
                else -> texto // Por si no se selecciona ninguna opción
            }
            val intentoDesencrip = Intent(
                this,
                MainActivity2::class.java
            )
            intentoDesencrip.putExtra("textoDesencriptado", textoDesencrip)
            startActivity(intentoDesencrip)
        }
    }

    fun encriptarTexto(texto: String): String {
        val textoEncriptado = StringBuilder()
        val longitud = texto.length
        for (i in texto.indices) {
            val caracter = texto[i]
// Cálculo del desplazamiento basado en la posición (i) y el ASCII del carácter
            val desplazamiento = (i + 1) % 26 // Limitar eldesplazamiento entre 0 y 25 (alfabeto)
            val nuevoCaracter = if (caracter.isLetter()) {
// Mantener la capitalización (mayúscula o minúscula)
                val base = if (caracter.isUpperCase()) 'A' else 'a'
                12
                ((caracter.code - base.code + desplazamiento) % 26 +
                        base.code).toChar()
            } else {
                caracter // No cifrar caracteres no alfabéticos
            }
            textoEncriptado.append(nuevoCaracter)
        }
        return textoEncriptado.toString()
    }

    fun encriptarTextoMetodo2(texto: String): String {
        val textoEncriptado = StringBuilder()
        for ((index, caracter) in texto.withIndex()) {
// El desplazamiento aumenta a medida que avanza el texto
            val desplazamiento = index + 1 // Desplazamiento creciente
            val nuevoCaracter = (caracter.code + desplazamiento) % 256
// Mantiene el rango de caracteres ASCII
            textoEncriptado.append(nuevoCaracter.toChar())
        }
        return textoEncriptado.toString()
    }

    fun encriptarTextoMetodo3(texto: String): String {
        val bloque = 4 // Tamaño del bloque para la transposición
        val textoEncriptado = StringBuilder()
// Dividir el texto en bloques
        13
        for (i in 0 until texto.length step bloque) {
            val subTexto = texto.substring(
                i, kotlin.math.min(
                    i +
                            bloque, texto.length
                )
            )
// Invertir el orden de los caracteres en el bloque
            textoEncriptado.append(subTexto.reversed())
        }
        return textoEncriptado.toString()
    }

    fun desencriptarTextoMetodo1(textoEncriptado: String): String {
        val textoDesencriptado = StringBuilder()
        val longitud = textoEncriptado.length
        for (i in textoEncriptado.indices) {
            val caracter = textoEncriptado[i]
// Cálculo del desplazamiento basado en la posición (i)
            val desplazamiento = (i + 1) % 26 // Limitar eldesplazamiento entre 0 y 25
            val nuevoCaracter = if (caracter.isLetter()) {
// Mantener la capitalización (mayúscula o minúscula)
                val base = if (caracter.isUpperCase()) 'A' else 'a'
// Restar el desplazamiento y asegurarse de que el valor sea positivo
                ((caracter.code - base.code - desplazamiento + 26) % 26
                        + base.code).toChar()
            } else {
                caracter // No descifrar caracteres no alfabéticos
            }
            textoDesencriptado.append(nuevoCaracter)
            14
        }
        return textoDesencriptado.toString()
    }

    fun desencriptarTextoMetodo2(textoEncriptado: String): String {
        val textoDesencriptado = StringBuilder()
        for ((index, caracter) in textoEncriptado.withIndex()) {
// Restamos el desplazamiento que se aplicó durante la encriptación
            val desplazamiento = index + 1 // Desplazamiento creciente
            var nuevoCaracter = (caracter.code - desplazamiento) % 256
// Mantiene el rango de caracteres ASCII
            if (nuevoCaracter < 0) {
                nuevoCaracter += 256 // Asegura que sea positivo
            }
            textoDesencriptado.append(nuevoCaracter.toChar())
        }
        return textoDesencriptado.toString()
    }

    fun desencriptarTextoMetodo3(textoEncriptado: String): String {
        val bloque = 4
        val textoDesencriptado = StringBuilder()
// Revertir el proceso de transposición
        for (i in 0 until textoEncriptado.length step bloque) {
            val subTexto = textoEncriptado.substring(i, kotlin.math.min(i + bloque, textoEncriptado.length)
            )
            15
            textoDesencriptado.append(subTexto.reversed())
        }
        return textoDesencriptado.toString()
    }
}
