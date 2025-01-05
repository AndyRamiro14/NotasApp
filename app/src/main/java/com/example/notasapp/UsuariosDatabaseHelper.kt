package com.example.notasapp

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.Cursor
import android.content.ContentValues
import android.database.sqlite.SQLiteOpenHelper

class UsuariosDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "usuarios.db"
        private const val DATABASE_VERSION = 1
        const val TABLE_NAME = "usuarios"
        const val COLUMN_ID = "id"
        const val COLUMN_USUARIO = "usuario"
        const val COLUMN_CONTRASENA = "contrasena"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = """
            CREATE TABLE $TABLE_NAME (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_USUARIO TEXT,
                $COLUMN_CONTRASENA TEXT
            )
        """
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun agregarUsuario(usuario: String, contrasena: String): Long {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_USUARIO, usuario)
            put(COLUMN_CONTRASENA, contrasena)
        }
        return db.insert(TABLE_NAME, null, values)
    }

    fun verificarUsuario(usuario: String, contrasena: String): Boolean {
        val db = readableDatabase
        val cursor: Cursor = db.query(
            TABLE_NAME, arrayOf(COLUMN_ID),
            "$COLUMN_USUARIO = ? AND $COLUMN_CONTRASENA = ?",
            arrayOf(usuario, contrasena),
            null, null, null
        )
        return cursor.count > 0
    }
}
