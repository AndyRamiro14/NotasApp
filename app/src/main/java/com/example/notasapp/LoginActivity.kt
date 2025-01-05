package com.example.notasapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val etUsername = findViewById<EditText>(R.id.etUsername)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val tvRegisterLink = findViewById<TextView>(R.id.tvRegisterLink)


        // Acción del botón de inicio de sesión
        btnLogin.setOnClickListener {
            val username = etUsername.text.toString()
            val password = etPassword.text.toString()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Por favor ingresa tus datos", Toast.LENGTH_SHORT).show()
            } else {
                // Aquí puedes agregar la lógica de validación del usuario (por ejemplo, en memoria o en una base de datos)
                if (validateUser(username, password)) {
                    // Si las credenciales son correctas, ir a la pantalla principal
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()  // Para que no se pueda volver atrás a la pantalla de login
                } else {
                    Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
                }
            }
        }

        // Acción del enlace para ir a la pantalla de registro
        tvRegisterLink.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun validateUser(username: String, password: String): Boolean {
        // Aquí se puede agregar la lógica de validación de las credenciales
        // Ejemplo: Validar contra datos almacenados en memoria o base de datos
        return username == "usuario" && password == "contraseña"  // Aquí puedes cambiarlo por un check real
    }
}
