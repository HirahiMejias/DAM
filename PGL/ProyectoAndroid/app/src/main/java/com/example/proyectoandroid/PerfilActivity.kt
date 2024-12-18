package com.example.proyectoandroid

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso

class PerfilActivity : AppCompatActivity() {

    private lateinit var dbHelper: AdminSQLiteOpenHelper
    private var userId: Int = 1 // El ID del usuario (por ejemplo, el ID del usuario logueado)
    private var selectedImageUri: String = "" // URI de la imagen seleccionada

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)

        // Inicializar el helper de la base de datos
        dbHelper = AdminSQLiteOpenHelper(this)

        // Referencias a los elementos de la UI
        val headerText = findViewById<TextView>(R.id.headerText)
        val nameInput = findViewById<EditText>(R.id.nameInput)
        val surnameInput = findViewById<EditText>(R.id.surnameInput)
        val genderInput = findViewById<EditText>(R.id.genderInput)
        val ageInput = findViewById<EditText>(R.id.ageInput)
        val profileImageView = findViewById<ImageView>(R.id.profileImageView)
        val chooseImageButton = findViewById<Button>(R.id.chooseImageButton)
        val saveButton = findViewById<Button>(R.id.saveButton)

        // Cargar los datos del perfil del usuario desde la base de datos
        val userProfile = dbHelper.obtenerPerfilUsuario(userId)

        if (userProfile != null) {
            // Asignar los datos del perfil a los campos de entrada
            nameInput.setText(userProfile["nombre"])
            surnameInput.setText(userProfile["apellido"])
            genderInput.setText(userProfile["genero"])
            ageInput.setText(userProfile["edad"])

            // Cargar la imagen del perfil usando Picasso
            val profileImageUrl = userProfile["foto_perfil"] ?: ""
            if (profileImageUrl.isNotEmpty()) {
                Picasso.get()
                    .load(profileImageUrl)  // Cargar imagen desde URL
                    .placeholder(R.drawable.ic_profile_placeholder)  // Imagen de carga
                    .error(R.drawable.ic_profile_placeholder)  // Imagen en caso de error
                    .into(profileImageView)
            }
        } else {
            Toast.makeText(this, "No se encontraron los datos del perfil", Toast.LENGTH_SHORT).show()
        }

        // Botón para elegir foto
        chooseImageButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 1) // Código de solicitud 1 para identificar esta acción
        }

        // Botón para guardar los cambios
        saveButton.setOnClickListener {
            val nombre = nameInput.text.toString()
            val apellido = surnameInput.text.toString()
            val genero = genderInput.text.toString()
            val edad = ageInput.text.toString().toIntOrNull() ?: 0
            val fotoPerfil = selectedImageUri // Usamos la URI de la imagen seleccionada

            // Verificar si se deben insertar o actualizar los datos en la base de datos
            val userProfileExist = dbHelper.obtenerPerfilUsuario(userId)
            if (userProfileExist != null) {
                // Actualizar el perfil existente
                dbHelper.actualizarPerfilUsuario(userId, nombre, apellido, genero, edad, fotoPerfil)
            } else {
                // Insertar nuevo perfil si no existe
                dbHelper.insertarPerfilUsuario(userId, nombre, apellido, genero, edad, fotoPerfil)
            }

            Toast.makeText(this, "Perfil guardado", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == RESULT_OK) {
            data?.data?.let { uri ->
                try {
                    // Crear un InputStream desde la URI
                    val inputStream = contentResolver.openInputStream(uri)

                    // Decodificar la imagen a un Bitmap
                    val bitmap = BitmapFactory.decodeStream(inputStream)

                    // Obtener la orientación utilizando ExifInterface
                    val fileDescriptor = contentResolver.openFileDescriptor(uri, "r")?.fileDescriptor
                    val exif = fileDescriptor?.let { ExifInterface(it) }
                    val orientation = exif?.getAttributeInt(
                        ExifInterface.TAG_ORIENTATION,
                        ExifInterface.ORIENTATION_UNDEFINED
                    )

                    // Rotar la imagen según la orientación
                    val rotatedBitmap = when (orientation) {
                        ExifInterface.ORIENTATION_ROTATE_90 -> rotateImage(bitmap, 90f)
                        ExifInterface.ORIENTATION_ROTATE_180 -> rotateImage(bitmap, 180f)
                        ExifInterface.ORIENTATION_ROTATE_270 -> rotateImage(bitmap, 270f)
                        else -> bitmap
                    }

                    // Mostrar la imagen corregida en el ImageView
                    findViewById<ImageView>(R.id.profileImageView).setImageBitmap(rotatedBitmap)

                    // Guardar la URI como String
                    selectedImageUri = uri.toString()

                } catch (e: Exception) {
                    e.printStackTrace()
                    Toast.makeText(this, "Error al procesar la imagen", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    // Función para rotar la imagen
    private fun rotateImage(source: Bitmap, angle: Float): Bitmap {
        val matrix = Matrix()
        matrix.postRotate(angle)
        return Bitmap.createBitmap(source, 0, 0, source.width, source.height, matrix, true)
    }

}

