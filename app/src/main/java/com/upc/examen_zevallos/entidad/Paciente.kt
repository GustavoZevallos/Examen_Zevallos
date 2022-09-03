package com.upc.examen_zevallos.entidad

import java.sql.Date

class Paciente {
    var id:Int = 0
    lateinit var nombre:String
    lateinit var apellido:String
    var dni:Int = 0
    var edad:Int = 0
    var telefono:Int = 0
    lateinit var email:String
    lateinit var direccion:String
    lateinit var fecNacimiento:String
}