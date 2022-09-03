package com.upc.examen_zevallos

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.upc.examen_zevallos.entidad.Paciente

class AdaptadorPersonalizado : RecyclerView.Adapter<AdaptadorPersonalizado.AdaptadorVewHolder>() {

    private var listaPaciente:List<Paciente> = ArrayList()
    private lateinit var context: Context
    private var onClickDeleteItem:((Paciente)-> Unit)? = null

    fun setOnClickDeleteItem(callback:(Paciente)-> Unit){
        this.onClickDeleteItem = callback
    }

    fun contexto (context: Context){
        this.context = context
    }

    fun addItem(item:List<Paciente>){
        this.listaPaciente = item
    }

    class AdaptadorVewHolder (var view: View):RecyclerView.ViewHolder(view){
        private var nombre = view.findViewById<TextView>(R.id.pacNombre)
        private var apellido = view.findViewById<TextView>(R.id.pacApellido)
        private var edad = view.findViewById<TextView>(R.id.pacEdad)
        private var telefono = view.findViewById<TextView>(R.id.pacTelefono)
        private var email = view.findViewById<TextView>(R.id.pacEmail)

        var pacEditar = view.findViewById<ImageButton>(R.id.pacEditar)
        var pacEliminar = view.findViewById<ImageButton>(R.id.pacEliminar)

        fun bindView(paciente: Paciente){
            nombre.text = paciente.nombre
            apellido.text = paciente.apellido
            edad.text = paciente.edad.toString()
            telefono.text = paciente.telefono.toString()
            email.text = paciente.email

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= AdaptadorVewHolder (
        LayoutInflater.from(parent.context).inflate(R.layout.fila,parent,false)
        )

    override fun onBindViewHolder(holder: AdaptadorVewHolder, position: Int) {
        val pacienteItem = listaPaciente[position]
        holder.bindView(pacienteItem)
        holder.pacEditar.setOnClickListener {
            val intent = Intent(context,MainActivity::class.java)
            intent.putExtra("id",listaPaciente[position].id.toString())
            intent.putExtra("nombre",listaPaciente[position].nombre)
            intent.putExtra("apellido",listaPaciente[position].apellido)
            intent.putExtra("dni",listaPaciente[position].dni.toString())
            intent.putExtra("edad",listaPaciente[position].edad.toString())
            intent.putExtra("telefono",listaPaciente[position].telefono.toString())
            intent.putExtra("email",listaPaciente[position].email)
            intent.putExtra("direccion",listaPaciente[position].direccion)
            context.startActivity(intent)
        }
        holder.pacEliminar.setOnClickListener {
            onClickDeleteItem?.invoke(pacienteItem)
        }
    }

    override fun getItemCount(): Int {
        return listaPaciente.size
    }

}