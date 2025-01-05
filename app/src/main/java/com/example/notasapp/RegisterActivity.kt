package com.example.notasapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent as Intent1

class RegisterActivity : AppCompatActivity() {

    private lateinit var dbHelper: UsuariosDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        dbHelper = UsuariosDatabaseHelper(this)

        val edtUsuario = findViewById<EditText>(R.id.edtUsuario)
        val edtContrasena = findViewById<EditText>(R.id.edtContrasena)
        val btnRegistrar = findViewById<Button>(R.id.btnRegistrar)
        val btnIngresar = findViewById<Button>(R.id.btnIngresar)

        btnRegistrar.setOnClickListener {
            val usuario = edtUsuario.text.toString()
            val contrasena = edtContrasena.text.toString()



            if (usuario.isNotEmpty() && contrasena.isNotEmpty()) {
                val id = dbHelper.agregarUsuario(usuario, contrasena)
                if (id > 0) {
                    Toast.makeText(this, "Usuario registrado", Toast.LENGTH_SHORT).show()
                    val intent = Intent1(this, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Error al registrar el usuario", Toast.LENGTH_SHORT).show()
                    finish() // Finaliza la actividad y vuelve a la pantalla de inicio de sesión.
                }
            } else {
                Toast.makeText(this, "Por favor, ingresa un nombre de usuario y una contraseña", Toast.LENGTH_SHORT).show()


            }
            btnIngresar.setOnClickListener {
                val intent = Intent1(this, LoginActivity::class.java)
                startActivity(intent)

            }

        }

    }

}
