package com.upc.examen_zevallos.util

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SuperArteDB(context: Context):SQLiteOpenHelper(context,DATABASENAME,null,DATABASE_VERSION){
    companion object{
        private const val DATABASENAME = "superArte.db"
        private const val DATABASE_VERSION = 1
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        var sql = "CREATE TABLE IF NOT EXISTS paciente "+
                " (id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                " nombre TEXT NOT NULL, "+ " apellido TEXT NOT NULL, "+
                " dni INTEGER NOT NULL, "+ " edad INTEGER NOT NULL,"+
                " telefono INTEGER, "+ " email TEXT, "+" direccion TEXT, "+"fecNac TEXT NOT NULL)"
        p0?.execSQL(sql)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL("DROP TABLE IF EXISTS paciente")
        onCreate(p0)
    }

}