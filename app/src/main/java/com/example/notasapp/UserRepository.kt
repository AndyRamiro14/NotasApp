package com.example.notasapp

object UserRepository {
    // Lista de usuarios predefinidos en memoria
    val usuarios = listOf(
        Usuario(
            "user1", "password1",
            password = TODO()
        ),
        Usuario(
            "user2", "password2",
            password = TODO()
        ),
        Usuario(
            "admin", "adminpass",
            password = TODO()
        )
    )

    // Metodo para validar las credenciales de inicio de sesión
    fun validarUsuario(username: String, password: String): Boolean {
        return usuarios.any { it.username == username && it.password == password }
    }
}
