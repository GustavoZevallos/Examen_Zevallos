package com.upc.examen_zevallos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.upc.examen_zevallos.entidad.Paciente
import com.upc.examen_zevallos.modelo.PacienteDAO

class EditarUsuarioActivity : AppCompatActivity() {

    private lateinit var txtNombre: EditText
    private lateinit var txtApellido: EditText
    private lateinit var txtDNI: EditText
    private lateinit var txtEdad: EditText
    private lateinit var txtTelefono: EditText
    private lateinit var txtEmail: EditText
    private lateinit var txtDireccion: EditText
    private lateinit var txtFecNac: EditText
    private lateinit var btnActualizar: Button

    var pacienteDAO: PacienteDAO = PacienteDAO(this)
    private var modificar:Boolean = false
    private var id:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_usuario)
        asignarReferencias()
        recuperarDatos()
    }
    private fun mostrarCalendario() {
        val selecDia = MostrarCalendarioFragment { day, month, year -> onDateSelected(day, month, year)}
        selecDia.show(supportFragmentManager,"selecDia")
    }

    fun onDateSelected (day: Int, month: Int, year:Int){
        txtFecNac.setText("$day/$month/$year")
    }

    private fun recuperarDatos(){
        if(intent.hasExtra("id")){
            modificar = true
            id = intent.getStringExtra("id")?.toInt()?:0
            txtNombre.setText(intent.getStringExtra("nombre"))
            txtApellido.setText(intent.getStringExtra("apellido"))
            txtDNI.setText(intent.getStringExtra("dni"))
            txtEdad.setText(intent.getStringExtra("edad"))
            txtTelefono.setText(intent.getStringExtra("telefono"))
            txtEmail.setText(intent.getStringExtra("email"))
            txtDireccion.setText(intent.getStringExtra("direccion"))
            txtFecNac.setText(intent.getStringExtra("fecNac"))
        }
    }

    private fun asignarReferencias(){
        txtNombre = findViewById(R.id.txtNombre)
        txtApellido = findViewById(R.id.txtApellido)
        txtDNI = findViewById(R.id.txtDNI)
        txtEdad = findViewById(R.id.txtEdad)
        txtTelefono = findViewById(R.id.txtTelefono)
        txtEmail = findViewById(R.id.txtEmail)
        txtDireccion = findViewById(R.id.txtDireccion)
        txtFecNac = findViewById(R.id.txtFecNac)
        txtFecNac.setOnClickListener {
            mostrarCalendario()
        }
        btnActualizar = findViewById(R.id.btnActualizar)
        btnActualizar.setOnClickListener{
            actualizarPaciente()
        }
    }

    private fun actualizarPaciente(){
        val nombre = txtNombre.text.toString()
        val apellido = txtApellido.text.toString()
        val dni = txtDNI.text.toString()
        val edad = txtEdad.text.toString()
        val telefono = txtTelefono.text.toString()
        val email = txtEmail.text.toString()
        val direccion = txtDireccion.text.toString()
        val fecNacimiento = txtFecNac.text.toString()

        if(nombre.isEmpty() || apellido.isEmpty() || dni.isEmpty() || edad.isEmpty()){
            Toast.makeText(this,"Completar los campos obligatorios", Toast.LENGTH_LONG).show()
        }else{
            val pac = Paciente()
            if (modificar) {
                pac.id = id
            }
            pac.nombre = nombre
            pac.apellido = apellido
            pac.dni = dni.toInt()
            pac.edad = edad.toInt()
            pac.telefono = telefono.toInt()
            pac.email = email
            pac.direccion = direccion
            pac.fecNacimiento = fecNacimiento

            var mensaje = ""
            if (modificar){
                mensaje = pacienteDAO.actualizarPaciente(pac)
            }else{
                mensaje = pacienteDAO.registrarPaciente(pac)
            }
            mostrarMensaje(mensaje)

        }
    }

    private fun mostrarMensaje(mensaje:String){
        val ventana = AlertDialog.Builder(this)
        ventana.setTitle("Mensaje informativo")
        ventana.setMessage(mensaje)
        ventana.setPositiveButton("Aceptar"){ dialog, which ->
            val intent = Intent(this,ListarActivity::class.java)
            startActivity(intent)
        }
        ventana.create().show()
    }
}