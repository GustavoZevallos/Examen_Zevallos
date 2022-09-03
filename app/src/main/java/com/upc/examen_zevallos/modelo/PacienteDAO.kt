package com.upc.examen_zevallos.modelo

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import com.upc.examen_zevallos.entidad.Paciente
import com.upc.examen_zevallos.util.SuperArteDB
import kotlin.collections.ArrayList

class PacienteDAO(context: Context) {
    private var superArteDB:SuperArteDB = SuperArteDB(context)

    fun registrarPaciente(paciente: Paciente):String {
        var respuesta = ""
        val db = superArteDB.writableDatabase
        try {
            val valores = ContentValues()
            valores.put("nombre", paciente.nombre)
            valores.put("apellido", paciente.apellido)
            valores.put("dni", paciente.dni)
            valores.put("edad", paciente.edad)
            valores.put("telefono", paciente.telefono)
            valores.put("email", paciente.email)
            valores.put("direccion", paciente.direccion)
            valores.put("fecNac", paciente.fecNacimiento)
            var resultado = db.insert("paciente", null, valores)
            if (resultado == -1L) {
                respuesta = "Error al registrar paciente"
            } else {
                respuesta = "Paciente registrado correctamente"
            }
        } catch (e: Exception) {
            respuesta = e.message.toString()
        } finally {
            db.close()
        }
        return respuesta
    }

    fun actualizarPaciente (paciente: Paciente):String{
        var respuesta = ""
        val db = superArteDB.writableDatabase
        try {
            val valores = ContentValues()
            valores.put("nombre", paciente.nombre)
            valores.put("apellido", paciente.apellido)
            valores.put("dni", paciente.dni)
            valores.put("edad", paciente.edad)
            valores.put("telefono", paciente.telefono)
            valores.put("email", paciente.email)
            valores.put("direccion", paciente.direccion)
            valores.put("fecNac", paciente.fecNacimiento)
            var resultado = db.update("paciente", valores,"id="+paciente.id,null)
            if (resultado == -1) {
                respuesta = "Error al actualizar paciente"
            } else {
                respuesta = "Paciente actualizado correctamente"
            }
        } catch (e: Exception) {
            respuesta = e.message.toString()
        } finally {
            db.close()
        }
        return respuesta
    }

    fun cargarPaciente ():ArrayList<Paciente>{
        val listaPaciente:ArrayList<Paciente> = ArrayList()
        val query= "Select * from paciente"
        val db = superArteDB.readableDatabase
        val cursor:Cursor

        try{
            cursor= db.rawQuery(query,null)
            if (cursor.count > 0){
                cursor.moveToFirst()
                do {
                    val id:Int = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
                    val nombre:String = cursor.getString(cursor.getColumnIndexOrThrow("nombre"))
                    val apellido:String = cursor.getString(cursor.getColumnIndexOrThrow("apellido"))
                    val dni:Int = cursor.getInt(cursor.getColumnIndexOrThrow("dni"))
                    val edad:Int = cursor.getInt(cursor.getColumnIndexOrThrow("edad"))
                    val telefono:Int = cursor.getInt(cursor.getColumnIndexOrThrow("telefono"))
                    val email:String = cursor.getString(cursor.getColumnIndexOrThrow("email"))
                    val direccion:String = cursor.getString(cursor.getColumnIndexOrThrow("direccion"))
                    val fecNacimiento:String = cursor.getString(cursor.getColumnIndexOrThrow("fecNac"))

                    val paciente = Paciente()
                    paciente.id = id
                    paciente.nombre = nombre
                    paciente.apellido = apellido
                    paciente.dni = dni
                    paciente.edad = edad
                    paciente.telefono = telefono
                    paciente.email = email
                    paciente.direccion = direccion
                    paciente.fecNacimiento = fecNacimiento

                    listaPaciente.add(paciente)
                }while (cursor.moveToNext())
            }
        }catch (e:Exception){

        }finally {
            db.close()
        }
        return listaPaciente
    }

    fun eliminarPaciente(id:Int):String {
        var respuesta = ""
        val db = superArteDB.writableDatabase
        try{
            val res = db.delete("paciente","id="+id,null)
            if (res == -1){
                respuesta = "Error al eliminar paciente"
            }else{
                respuesta = "Paciente eliminado correctamente"
            }
        }catch (e:Exception){

        }finally {
            db.close()
        }
        return respuesta
    }
}