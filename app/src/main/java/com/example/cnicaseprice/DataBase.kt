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

        // Supply table
        private const val TABLE_SUPPLY = "Supply"
        private const val SUPPLY_COLUMN_NAME = "Name"
        private const val SUPPLY_COLUMN_QUANTITY = "Quantity"

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

        // Supply
        val createSupplyTable = "CREATE TABLE $TABLE_SUPPLY" +
                "($SUPPLY_COLUMN_NAME TEXT PRIMARY KEY, $SUPPLY_COLUMN_QUANTITY INTEGER)"
        db?.execSQL(createSupplyTable)
        db?.execSQL("INSERT INTO $TABLE_SUPPLY ($SUPPLY_COLUMN_NAME, $SUPPLY_COLUMN_QUANTITY) VALUES ('Insumo A', 0)")
        db?.execSQL("INSERT INTO $TABLE_SUPPLY ($SUPPLY_COLUMN_NAME, $SUPPLY_COLUMN_QUANTITY) VALUES ('Insumo B', 10)")
        db?.execSQL("INSERT INTO $TABLE_SUPPLY ($SUPPLY_COLUMN_NAME, $SUPPLY_COLUMN_QUANTITY) VALUES ('Insumo C', 25)")
        db?.execSQL("INSERT INTO $TABLE_SUPPLY ($SUPPLY_COLUMN_NAME, $SUPPLY_COLUMN_QUANTITY) VALUES ('Insumo D', 30)")
        db?.execSQL("INSERT INTO $TABLE_SUPPLY ($SUPPLY_COLUMN_NAME, $SUPPLY_COLUMN_QUANTITY) VALUES ('Insumo E', 40)")
        db?.execSQL("INSERT INTO $TABLE_SUPPLY ($SUPPLY_COLUMN_NAME, $SUPPLY_COLUMN_QUANTITY) VALUES ('Insumo F', 50)")
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
        if (cursor.count == 1) {
            cursor.moveToFirst()
            val patient = Paciente(
                cursor.getString(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3)
            )
            db.close()
            cursor.close()
            return patient
        } else {
            db.close()
            cursor.close()
            throw Exception("Paciente no encontrado")
        }
    }

    fun getSupplies():MutableList<Supply>{
        val bd = this.readableDatabase
        val list = mutableListOf<Supply>()
        val sql = "SELECT * FROM $TABLE_SUPPLY"
        val cursor = bd.rawQuery(sql, null)
        if(cursor.moveToFirst()){
            do {
                val supply = Supply(
                    cursor.getString(0),
                    cursor.getInt(1)
                )
                list.add(supply)
            }
                while(cursor.moveToNext())
            bd.close()
            cursor.close()
        }
        return list
    }

    fun updateSupplies(supplies: List<Supply>){
        val db = this.writableDatabase
        db.beginTransaction()
        try {
            for (supply in supplies) {
                val contentValues = ContentValues()
                contentValues.put(SUPPLY_COLUMN_QUANTITY, supply.quantity)
                db.update(TABLE_SUPPLY, contentValues, "$SUPPLY_COLUMN_NAME = ?", arrayOf(supply.name))
            }
            db.setTransactionSuccessful()
        } finally {
            db.endTransaction()
            db.close()
        }

    }
}