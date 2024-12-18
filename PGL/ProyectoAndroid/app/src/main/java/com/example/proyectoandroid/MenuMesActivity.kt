package com.example.proyectoandroid

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.proyectoandroid.R
import android.widget.ImageView

class MenuMesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_mes)  // Asegúrate de que esto esté al principio

        // Asegúrate de que el ImageView esté correctamente inicializado
        val menuImageView: ImageView = findViewById(R.id.menuImage)
        menuImageView.setImageResource(R.drawable.menu)  // Asegúrate de que la imagen exista en drawable
    }
}
