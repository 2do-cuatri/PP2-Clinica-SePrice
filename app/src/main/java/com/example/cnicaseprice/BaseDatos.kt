package com.example.cnicaseprice

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

const val BD = "SePrice"
class BaseDatos (contexto: Context): SQLiteOpenHelper(contexto, BD, null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val sql = "CREATE TABLE Usuario" +
                  "(id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, clave TEXT, categoria TEXT)"
        db?.execSQL(sql)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun insertarUsuario(nombre: String, clave: String, categoria: String):String{
        val bd = this.writableDatabase
        val contenedor = ContentValues()

        contenedor.put("nombre", nombre)
        contenedor.put("clave", clave)
        contenedor.put("categoria", categoria)

        val resultado = bd.insert("Usuario", null, contenedor)

        return if(resultado == (-1).toLong()){
            "Error al insertar el usuario"
        } else {

            "Insert exitoso"
        }
    }

    fun traerDatosUsuario():MutableList<Usuario>{
        val bd = this.readableDatabase
        val lista = mutableListOf<Usuario>()
        val sql = "SELECT * FROM Usuario"
        val cursor = bd.rawQuery(sql, null)

        if(cursor.moveToFirst()){
            do{
                val usuario = Usuario(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3))
                lista.add(usuario)
            } while(cursor.moveToNext())
            bd.close()
            cursor.close()
        }
        return lista
    }

    fun actualizarDatosUsuario(id: Int, nombre: String, clave: String, categoria: String):String{
        val bd = this.writableDatabase
        val contenedor = ContentValues()
        contenedor.put("nombre", nombre)
        contenedor.put("clave", clave)
        contenedor.put("categoria", categoria)

        val resultado = bd.update("Usuario", contenedor, "id = ?", arrayOf(id.toString()))
        return if(resultado == 0){
            "Error al actualizar el usuario"
        } else {
            "Actualizacion exitosa"
        }

    }

    fun borrarUsuario(id: Int):String{
        val bd = this.writableDatabase
        val resultado = bd.delete("Usuario", "id = ?", arrayOf(id.toString()))
        return if(resultado == 0){
            "Error al borrar el usuario"
        } else {
            "Borrado exitoso"
        }
    }
}