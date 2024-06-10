package com.example.cnicaseprice

class Usuario(val id: Int, private val nombre: String, private val clave: String, val categoria: String) {

    override fun toString(): String {
        return "Usuario(id='$id', nombre='$nombre', clave='$clave', categoria='$categoria')"

    }
}