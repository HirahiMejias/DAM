package com.example.cuestionariohirahi

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val etnombreUsuario=findViewById<EditText>(R.id.etNombreUsuario)
        val etcontrasenya = findViewById<EditText>(R.id.etContrasenya)
        val botonLogin = findViewById<Button>(R.id.btnLogin)
        val botonSingIn= findViewById<Button>(R.id.btnSignIn)

        botonLogin.setOnClickListener{
            val nombre=etnombreUsuario.text.toString()
            val contrasenya=etcontrasenya.text.toString()
            iniciarSesion(nombre,contrasenya)


        }
        botonSingIn.setOnClickListener{
            val nombre=etnombreUsuario.text.toString()
            val contrasenya=etcontrasenya.text.toString()
            Registro(nombre,contrasenya)
        }


    }
    fun cambiarActividad(nombre: String){
        val intento = Intent(this,BienvenidaActivity::class.java)
        intento.putExtra("nombre",nombre)
        intento.putExtra("Nota",0)
        startActivity(intento)
    }
    fun Registro(nombre:String ,contrasenya: String){
        val admin = AdminSQLiteOpenHelper(this)
        val db = admin.writableDatabase
        val valores= ContentValues()
        val cursor = db.rawQuery("Select * from usuarios where usuario=?",arrayOf(nombre))
        if (cursor.count>0){
            Toast.makeText(this,"usuario ya existe",Toast.LENGTH_SHORT).show()
        }else{
            valores.put("usuario",nombre)
            valores.put("contrasenya",contrasenya)
            db.insert("usuarios",null,valores)
            Toast.makeText(this,"usuario Registrado",Toast.LENGTH_SHORT).show()
        }


    }

    fun iniciarSesion(nombre:String ,contrasenya: String){
        val admin = AdminSQLiteOpenHelper(this)
        val db = admin.readableDatabase
        val cursor = db.rawQuery(" Select * from usuarios where usuario=? and contrasenya=?",arrayOf(nombre,contrasenya))
        if(cursor.moveToFirst()){
            cambiarActividad(nombre)
            Toast.makeText(this,"Incio Sesion Exito",Toast.LENGTH_SHORT).show()
        }else {
            Toast.makeText(this,"Informacion Incorrecta",Toast.LENGTH_SHORT).show()
        }
        cursor.close()
        db.close()
    }
}