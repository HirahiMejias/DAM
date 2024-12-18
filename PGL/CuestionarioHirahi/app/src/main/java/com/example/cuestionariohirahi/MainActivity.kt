package com.example.cuestionariohirahi

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var spinner1: Spinner
    private lateinit var spinner2: Spinner
    private lateinit var btnCorregir: Button
    private lateinit var tvResultado: TextView

    private lateinit var radioGroup2: RadioGroup
    private lateinit var radioGroup4: RadioGroup
    private lateinit var radioGroup5: RadioGroup
    private lateinit var radioGroup6: RadioGroup
    private lateinit var radioGroup7: RadioGroup
    private lateinit var radioGroup8: RadioGroup
    private lateinit var radioGroup9: RadioGroup
    private lateinit var radioGroup10: RadioGroup

    private lateinit var tvResultado1: TextView
    private lateinit var tvResultado2: TextView
    private lateinit var tvResultado3: TextView
    private lateinit var tvResultado4: TextView
    private lateinit var tvResultado5: TextView
    private lateinit var tvResultado6: TextView
    private lateinit var tvResultado7: TextView
    private lateinit var tvResultado8: TextView
    private lateinit var tvResultado9: TextView
    private lateinit var tvResultado10: TextView

    private val respuestasCorrectas = arrayOf(
        "Francia",         // Pregunta 1
        "1969",            // Pregunta 2
        "Mercurio",        // Pregunta 3
        "Guepardo",        // Pregunta 4
        "Pacífico",        // Pregunta 5
        "Rusia",           // Pregunta 6
        "Ártico", // Pregunta 7
        "Aluminio", // Pregunta 8
        "Asia",            // Pregunta 9
        "Yen" // Pregunta 10
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializar vistas
        spinner1 = findViewById(R.id.spinner1)
        spinner2 = findViewById(R.id.spinner1)
        btnCorregir = findViewById(R.id.btCorregir)
        tvResultado = findViewById(R.id.tvResultado)

        radioGroup2 = findViewById(R.id.radioGroup2)
        radioGroup4 = findViewById(R.id.radioGroup4)
        radioGroup5 = findViewById(R.id.radioGroup5)
        radioGroup6 = findViewById(R.id.radioGroup6)
        radioGroup7 = findViewById(R.id.radioGroup7)
        radioGroup8 = findViewById(R.id.radioGroup8)
        radioGroup9 = findViewById(R.id.radioGroup9)
        radioGroup10 = findViewById(R.id.radioGroup10)

        // TextViews para mostrar resultados de cada pregunta
        tvResultado1 = findViewById(R.id.tvResultado1)
        tvResultado2 = findViewById(R.id.tvResultado2)
        tvResultado3 = findViewById(R.id.tvResultado3)
        tvResultado4 = findViewById(R.id.tvResultado4)
        tvResultado5 = findViewById(R.id.tvResultado5)
        tvResultado6 = findViewById(R.id.tvResultado6)
        tvResultado7 = findViewById(R.id.tvResultado7)
        tvResultado8 = findViewById(R.id.tvResultado8)
        tvResultado9 = findViewById(R.id.tvResultado9)
        tvResultado10 = findViewById(R.id.tvResultado10)

        // Adaptadores para los Spinners
        val opcionesSpinner1 = arrayOf("Tenerife", "La Gomera", "Francia")
        val adaptadorSpinner1 =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, opcionesSpinner1)
        spinner2.adapter = adaptadorSpinner1

        val opcionesSpinner2 = arrayOf("Mercurio", "Venus", "Marte")
        val adaptadorSpinner2 =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, opcionesSpinner2)
        spinner1.adapter = adaptadorSpinner2

        btnCorregir.setOnClickListener {


            // Contador de aciertos
            var aciertos = 0

            // Pregunta 1
            if (spinner2.selectedItem.toString() == respuestasCorrectas[0]) {
                aciertos++
                tvResultado1.text = "Correcto"
            } else {
                tvResultado1.text = "Incorrecto. Respuesta correcta: ${respuestasCorrectas[0]}"
            }

            // Pregunta 2
            val checkedRadioButton2 = findViewById<RadioButton>(radioGroup2.checkedRadioButtonId)
            if (checkedRadioButton2 != null && checkedRadioButton2.text == respuestasCorrectas[1]) {
                aciertos++
                tvResultado2.text = "Correcto"
            } else {
                tvResultado2.text = "Incorrecto. Respuesta correcta: ${respuestasCorrectas[1]}"
            }

            // Pregunta 3
            if (spinner1.selectedItem.toString() == respuestasCorrectas[2]) {
                aciertos++
                tvResultado3.text = "Correcto"
            } else {
                tvResultado3.text = "Incorrecto. Respuesta correcta: ${respuestasCorrectas[2]}"
            }

            // Pregunta 4
            val checkedRadioButton4 = findViewById<RadioButton>(radioGroup4.checkedRadioButtonId)
            if (checkedRadioButton4 != null && checkedRadioButton4.text == respuestasCorrectas[3]) {
                aciertos++
                tvResultado4.text = "Correcto"
            } else {
                tvResultado4.text = "Incorrecto. Respuesta correcta: ${respuestasCorrectas[3]}"
            }

            // Pregunta 5
            val checkedRadioButton5 = findViewById<RadioButton>(radioGroup5.checkedRadioButtonId)
            if (checkedRadioButton5 != null && checkedRadioButton5.text == respuestasCorrectas[4]) {
                aciertos++
                tvResultado5.text = "Correcto"
            } else {
                tvResultado5.text = "Incorrecto. Respuesta correcta: ${respuestasCorrectas[4]}"
            }

            // Pregunta 6
            val checkedRadioButton6 = findViewById<RadioButton>(radioGroup6.checkedRadioButtonId)
            if (checkedRadioButton6 != null && checkedRadioButton6.text == respuestasCorrectas[5]) {
                aciertos++
                tvResultado6.text = "Correcto"
            } else {
                tvResultado6.text = "Incorrecto. Respuesta correcta: ${respuestasCorrectas[5]}"
            }

            // Pregunta 7
            val checkedRadioButton7 = findViewById<RadioButton>(radioGroup7.checkedRadioButtonId)
            if (checkedRadioButton7 != null && checkedRadioButton7.text == respuestasCorrectas[6]) {
                aciertos++
                tvResultado7.text = "Correcto"
            } else {
                tvResultado7.text = "Incorrecto. Respuesta correcta: ${respuestasCorrectas[6]}"
            }

            // Pregunta 8
            val checkedRadioButton8 = findViewById<RadioButton>(radioGroup8.checkedRadioButtonId)
            if (checkedRadioButton8 != null && checkedRadioButton8.text == respuestasCorrectas[7]) {
                aciertos++
                tvResultado8.text = "Correcto"
            } else {
                tvResultado8.text = "Incorrecto. Respuesta correcta: ${respuestasCorrectas[7]}"
            }

            // Pregunta 9
            val checkedRadioButton9 = findViewById<RadioButton>(radioGroup9.checkedRadioButtonId)
            if (checkedRadioButton9 != null && checkedRadioButton9.text == respuestasCorrectas[8]) {
                aciertos++
                tvResultado9.text = "Correcto"
            } else {
                tvResultado9.text = "Incorrecto. Respuesta correcta: ${respuestasCorrectas[8]}"
            }

            // Pregunta 10
            val checkedRadioButton10 = findViewById<RadioButton>(radioGroup10.checkedRadioButtonId)
            if (checkedRadioButton10 != null && checkedRadioButton10.text == respuestasCorrectas[9]) {
                aciertos++
                tvResultado10.text = "Correcto"
            } else {
                tvResultado10.text = "Incorrecto. Respuesta correcta: ${respuestasCorrectas[9]}"
            }

            // Mostrar total de aciertos
            tvResultado.text = "Aciertos: $aciertos/10"
        }
    }
}