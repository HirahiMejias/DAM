package com.example.cifrado
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)
        val botonVolver = findViewById<Button>(R.id.btVolver)
        val palabraEncriptada =
            findViewById<TextView>(R.id.tvResultEncriptado)
        val palabraDesencriptada =
            findViewById<TextView>(R.id.tvResultDesencriptado)
        val bundle = intent.extras
        val textoEncriptado = bundle?.getString("textoEncriptado")
        palabraEncriptada.text = textoEncriptado;
        val textoDesencriptado =
            bundle?.getString("textoDesencriptado")
        palabraDesencriptada.text=textoDesencriptado
        botonVolver.setOnClickListener(){
            val volver = Intent(this, MainActivity::class.java)
            startActivity(volver)
        }
    }
}
