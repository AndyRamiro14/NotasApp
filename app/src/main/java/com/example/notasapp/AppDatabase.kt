package com.example.notasapp

import android.content.Context
import  androidx.room.Room
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Usuario::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun usuarioDao(): UsuarioDao
}
object DatabaseClient {

    private var INSTANCE: AppDatabase? = null

    // Método para obtener la instancia de la base de datos
    fun getDatabase(context: Context): AppDatabase {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java, "app_database"
            )
                .fallbackToDestructiveMigration() // Si necesitas migrar, usa esta opción
                .build()
        }
        return INSTANCE!!
    }
}
