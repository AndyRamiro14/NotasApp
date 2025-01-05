package com.example.notasapp

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.Cursor
import android.content.ContentValues
import android.database.sqlite.SQLiteOpenHelper

class NotasDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "notas.db"
        private const val DATABASE_VERSION = 1
        const val TABLE_NAME = "notas"
        const val COLUMN_ID = "id"
        const val COLUMN_TITULO = "titulo"
        const val COLUMN_NOTA = "nota"
        const val COLUMN_USUARIO_ID = "usuario_id"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = """
            CREATE TABLE $TABLE_NAME (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_TITULO TEXT,
                $COLUMN_NOTA TEXT,
                $COLUMN_USUARIO_ID INTEGER,
                FOREIGN KEY($COLUMN_USUARIO_ID) REFERENCES usuarios(id)
            )
        """
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun agregarNota(titulo: String, nota: String, usuarioId: Int): Long {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_TITULO, titulo)
            put(COLUMN_NOTA, nota)
            put(COLUMN_USUARIO_ID, usuarioId)
        }
        return db.insert(TABLE_NAME, null, values)
    }

    fun obtenerNotas(usuarioId: Int): Cursor {
        val db = readableDatabase
        return db.query(
            TABLE_NAME, null,
            "$COLUMN_USUARIO_ID = ?",
            arrayOf(usuarioId.toString()),
            null, null, null
        )
    }

    fun eliminarNota(id: Int): Int {
        val db = writableDatabase
        return db.delete(TABLE_NAME, "$COLUMN_ID = ?", arrayOf(id.toString()))
    }

    fun actualizarNota(id: Int, titulo: String, nota: String): Int {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_TITULO, titulo)
            put(COLUMN_NOTA, nota)
        }
        return db.update(TABLE_NAME, values, "$COLUMN_ID = ?", arrayOf(id.toString()))
    }
}
