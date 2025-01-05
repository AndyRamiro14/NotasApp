package com.example.notasapp

import android.annotation.SuppressLint
import android.database.Cursor
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private lateinit var dbHelper: NotasDatabaseHelper
    private lateinit var listView: ListView
    private lateinit var adapter: ArrayAdapter<String>
    private val notasList = mutableListOf<String>()
    private var usuarioId: Int = 0 // Se debe asignar después de iniciar sesión

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbHelper = NotasDatabaseHelper(this)

        listView = findViewById(R.id.listView)
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, notasList)
        listView.adapter = adapter

        cargarNotas()

        val btnAgregar = findViewById<Button>(R.id.btnAgregar)
        btnAgregar.setOnClickListener {
            val titulo = findViewById<EditText>(R.id.edtTitulo).text.toString()
            val nota = findViewById<EditText>(R.id.edtNota).text.toString()
            if (titulo.isNotEmpty() && nota.isNotEmpty()) {
                dbHelper.agregarNota(titulo, nota, usuarioId)
                cargarNotas()
            }
        }

        listView.setOnItemClickListener { _, _, position, _ ->
            val cursor: Cursor = dbHelper.obtenerNotas(usuarioId)
            cursor.moveToPosition(position)
            val id = cursor.getInt(cursor.getColumnIndex(NotasDatabaseHelper.COLUMN_ID))
            dbHelper.eliminarNota(id)
            cargarNotas()
        }
    }

    @SuppressLint("Range")
    private fun cargarNotas() {
        notasList.clear()
        val cursor: Cursor = dbHelper.obtenerNotas(usuarioId)
        if (cursor.moveToFirst()) {
            do {
                val titulo = cursor.getString(cursor.getColumnIndex(NotasDatabaseHelper.COLUMN_TITULO))
                val nota = cursor.getString(cursor.getColumnIndex(NotasDatabaseHelper.COLUMN_NOTA))
                notasList.add("$titulo: $nota")
            } while (cursor.moveToNext())
        }
        adapter.notifyDataSetChanged()
    }
}
