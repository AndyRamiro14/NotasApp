package com.example.notasapp

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UsuarioDao {

    // Insertar un usuario en la base de datos
    @Insert
    suspend fun insertarUsuario(usuario: Usuario)

    // Obtener un usuario por su nombre de usuario
    @Query("SELECT * FROM usuarios WHERE username = :username LIMIT 1")
    suspend fun obtenerUsuarioPorUsername(username: String): Usuario?

    // Obtener todos los usuarios (opcional, dependiendo de lo que necesites)
    @Query("SELECT * FROM usuarios")
    suspend fun obtenerTodosUsuarios(): List<Usuario>

    // Eliminar un usuario por su id (opcional)
    @Query("DELETE FROM usuarios WHERE id = :id")
    suspend fun eliminarUsuario(id: Int)
}
