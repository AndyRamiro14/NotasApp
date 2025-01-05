package com.example.notasapp
import androidx.room.PrimaryKey
import androidx.room.Entity

// Usa tu propio paquete aquí

// La clase Usuario que representa las credenciales del usuario
@Entity(tableName = "usuarios")
data class Usuario(
    @PrimaryKey
    val id: String = 0.toString(),
    val username: String, // Nombre de usuario
    val password: String  // Contraseña
)
