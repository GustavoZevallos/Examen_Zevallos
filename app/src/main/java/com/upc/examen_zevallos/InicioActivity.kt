package com.upc.examen_zevallos

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity



class InicioActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(androidx.appcompat.R.style.Theme_AppCompat)
        super.onCreate(savedInstanceState)
        val intent = Intent(this, ListarActivity::class.java)
        startActivity(intent)
        finish() // evitar regresar a éste activity
    }
}