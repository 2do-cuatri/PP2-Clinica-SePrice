package com.example.cnicaseprice

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataBase (context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        // General DB config
        private const val DATABASE_NAME = "SePrice"
        private const val DATABASE_VERSION = 1

        // User table
        private const val TABLE_USER = "User"
        private const val COLUMN_ID = "id"
        private const val COLUMN_USERNAME = "username"
        private const val COLUMN_PASSWORD = "password"
        private const val COLUMN_TYPE = "type"

        // Paciente table
        private const val TABLE_PACIENTE = "Patient"
        private const val COLUMN_DNI = "DNI"
        private const val COLUMN_OS = "OS"
        private const val COLUMN_NOMBRE = "Nombre"
        private const val COLUMN_APELLIDO = "Apellido"

        // Init values
        var loggedUser: Usuario? = null
    }


    override fun onCreate(db: SQLiteDatabase?) {
        // User
        val createUserTable = "CREATE TABLE $TABLE_USER" +
                  "($COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, $COLUMN_USERNAME TEXT, $COLUMN_PASSWORD TEXT, $COLUMN_TYPE TEXT)"

        db?.execSQL(createUserTable)
        db?.execSQL("INSERT INTO $TABLE_USER ($COLUMN_USERNAME, $COLUMN_PASSWORD, $COLUMN_TYPE) VALUES ('admin', 'admin', 'admin')")
        db?.execSQL("INSERT INTO $TABLE_USER ($COLUMN_USERNAME, $COLUMN_PASSWORD, $COLUMN_TYPE) VALUES ('medico', 'medico', 'medico')")

        // Paciente
        val createPacienteTable = "CREATE TABLE $TABLE_PACIENTE" +
                "($COLUMN_DNI TEXT PRIMARY KEY, $COLUMN_OS TEXT, $COLUMN_NOMBRE TEXT, $COLUMN_APELLIDO TEXT)"

        db?.execSQL(createPacienteTable)
        db?.execSQL("INSERT INTO $TABLE_PACIENTE ($COLUMN_DNI, $COLUMN_OS, $COLUMN_NOMBRE, $COLUMN_APELLIDO) VALUES ('35973905', '2200', 'Santiago', 'Rubio')")
   }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_USER")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_PACIENTE")
        onCreate(db)
    }

    fun insertUser(username: String, password: String, type: String):String{
        val bd = this.writableDatabase
        val container = ContentValues()

        container.put(COLUMN_USERNAME, username)
        container.put(COLUMN_PASSWORD, password)
        container.put(COLUMN_TYPE, type)

        val result = bd.insert(TABLE_USER, null, container)

        return if(result == (-1).toLong()){
            "Error al insertar el usuario"
        } else {
            "Insert exitoso"
        }
    }

    fun validateUser(username: String, password: String): Boolean {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_USER WHERE $COLUMN_USERNAME = '$username' AND $COLUMN_PASSWORD = '$password'", null)
        val isValid = cursor.count > 0
        if (isValid){
            cursor.moveToFirst()
            loggedUser = Usuario(cursor.getInt(0), username, password, cursor.getString(3))
        }

        cursor.close()
        db.close()
        return isValid
    }


    fun getAllUsers():MutableList<Usuario>{
        val bd = this.readableDatabase
        val list = mutableListOf<Usuario>()
        val sql = "SELECT * FROM $TABLE_USER"
        val cursor = bd.rawQuery(sql, null)

        if(cursor.moveToFirst()){
            do{
                val user = Usuario(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3))
                list.add(user)
            } while(cursor.moveToNext())
            bd.close()
            cursor.close()
        }
        return list
    }

    fun updateUser(id: Int, username: String, password: String, type: String):String{
        val bd = this.writableDatabase
        val container = ContentValues()
        container.put(COLUMN_USERNAME, username)
        container.put(COLUMN_PASSWORD, password)
        container.put(COLUMN_TYPE, type)

        val result = bd.update(TABLE_USER, container, "$COLUMN_ID = ?", arrayOf(id.toString()))
        return if(result == 0){
            "Error al actualizar el usuario"
        } else {
            "Actualizacion exitosa"
        }

    }

    fun deleteUser(id: Int):String{
        val bd = this.writableDatabase
        val result = bd.delete(TABLE_USER, "$COLUMN_ID = ?", arrayOf(id.toString()))
        return if(result == 0){
            "Error al borrar el usuario"
        } else {
            "Borrado exitoso"
        }
    }

    fun getPatient(dni: String?, os: String?): Paciente {
        val db = this.readableDatabase
        if (dni.isNullOrEmpty() && os.isNullOrEmpty()) throw Exception("Debe indicar DNI y/o Nro de Obra Social")

        val cursor = db.rawQuery("SELECT * FROM $TABLE_PACIENTE WHERE $COLUMN_DNI = ? OR $COLUMN_OS = ? LIMIT 1", arrayOf(dni, os))
        if (cursor.count === 1) {
            cursor.moveToFirst()
            val patient = Paciente(
                cursor.getString(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3)
            )
            return patient
        } else throw Exception("Paciente no encontrado")
    }
}