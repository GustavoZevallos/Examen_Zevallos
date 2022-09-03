package com.upc.examen_zevallos

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.upc.examen_zevallos.modelo.PacienteDAO

class ListarActivity : AppCompatActivity() {

    private lateinit var btnAgregar:FloatingActionButton
    private lateinit var rvPaciente:RecyclerView
    private var adaptador:AdaptadorPersonalizado?=null
    private var pacienteDAO:PacienteDAO = PacienteDAO(this)

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listar)
        asignarReferencias()
        mostrarPacientes()
    }

    private fun asignarReferencias(){
        btnAgregar = findViewById(R.id.btnAgregar)
        btnAgregar.setOnClickListener {
            val intent = Intent(baseContext,MainActivity::class.java)
            startActivity(intent)
        }
        rvPaciente = findViewById(R.id.rvPaciente)
        rvPaciente.layoutManager = LinearLayoutManager(this)
        adaptador = AdaptadorPersonalizado()
        rvPaciente.adapter = adaptador

        adaptador?.setOnClickDeleteItem {
            eliminar(it.id)
        }
    }

    fun eliminar(id:Int){
        if(id == null) return

        val ventana = AlertDialog.Builder(this)
        ventana.setMessage("Desea eliminar al paciente?")
        ventana.setCancelable(true)
        ventana.setNegativeButton("No",null)
        ventana.setPositiveButton("Si",){ dialog, _->
            var mensaje = pacienteDAO.eliminarPaciente(id)
            mostrarPacientes()
            dialog.dismiss()
            mostrarMensaje(mensaje)
        }
        ventana.create().show()
    }

    private fun mostrarMensaje (mensaje:String){
        val ventana = AlertDialog.Builder(this)
        ventana.setTitle("Mensaje informativo")
        ventana.setMessage(mensaje)
        ventana.setPositiveButton("Aceptar"){dialog, which ->
            val intent = Intent(this,ListarActivity::class.java)
            startActivity(intent)
        }
        ventana.create().show()
    }

    private fun mostrarPacientes(){
        val listaPacientes = pacienteDAO.cargarPaciente()
        adaptador?.contexto(this)
        adaptador?.addItem(listaPacientes)
    }
}