package com.example.cnicaseprice

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Build
import java.text.SimpleDateFormat
import java.util.Optional
import java.util.Date
import java.util.Locale

class DataBase (context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        // General DB config
        private const val DATABASE_NAME = "SePrice"
        private const val DATABASE_VERSION = 4

        // Generic columns
        private const val COLUMN_ID = "id"
        private const val COLUMN_TYPE = "type"

        // User table
        private const val TABLE_USER = "User"
        private const val COLUMN_USERNAME = "username"
        private const val COLUMN_PASSWORD = "password"

        // Paciente table
        private const val TABLE_PACIENTE = "Patient"
        private const val COLUMN_DNI = "DNI"
        private const val COLUMN_OS = "OS"
        private const val COLUMN_NOMBRE = "Nombre"
        private const val COLUMN_APELLIDO = "Apellido"

        // Appointments table
        private const val TABLE_APPOINTMENTS = "Appointment"
        private const val COLUMN_PATIENT_DNI = "patient_dni"
        private const val COLUMN_DATE = "date"
        private const val COLUMN_STUDY_ID = "study_id"

        // Studies table
        private const val TABLE_STUDIES = "Studies"
        private const val COLUMN_NAME = "name"


        // Supply table
        private const val TABLE_SUPPLY = "Supply"
        private const val SUPPLY_COLUMN_NAME = "Name"
        private const val SUPPLY_COLUMN_QUANTITY = "Quantity"

        // Medical History table
        private const val TABLE_MH = "MedicalHistory"
        private const val MH_COLUMN_ID = "ID"
        private const val MH_COLUMN_DNI = "DNI"
        private const val MH_COLUMN_DATE = "Date"
        private const val MH_COLUMN_DOCTOR = "Doctor"
        private const val MH_COLUMN_DETAIL = "Detail"


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

        // Studies
        val createStudiesTable = "CREATE TABLE $TABLE_STUDIES" +
                "($COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, $COLUMN_TYPE TEXT, $COLUMN_NAME TEXT)"
        db?.execSQL(createStudiesTable)
        db?.execSQL("INSERT INTO $TABLE_STUDIES ($COLUMN_TYPE, $COLUMN_NAME) VALUES ('laboratorio', 'Analisis de sangre')")
        db?.execSQL("INSERT INTO $TABLE_STUDIES ($COLUMN_TYPE, $COLUMN_NAME) VALUES ('laboratorio', 'Analisis de orina')")
        db?.execSQL("INSERT INTO $TABLE_STUDIES ($COLUMN_TYPE, $COLUMN_NAME) VALUES ('clinica', 'Consulta pediatria')")
        db?.execSQL("INSERT INTO $TABLE_STUDIES ($COLUMN_TYPE, $COLUMN_NAME) VALUES ('clinica', 'Consulta dermatologia')")
        db?.execSQL("INSERT INTO $TABLE_STUDIES ($COLUMN_TYPE, $COLUMN_NAME) VALUES ('clinica', 'Consulta general')")
        db?.execSQL("INSERT INTO $TABLE_STUDIES ($COLUMN_TYPE, $COLUMN_NAME) VALUES ('imagen', 'Radiografia')")
        db?.execSQL("INSERT INTO $TABLE_STUDIES ($COLUMN_TYPE, $COLUMN_NAME) VALUES ('imagen', 'Tomografia')")

        // Appointments
        val createAppointmentsTable = "CREATE TABLE $TABLE_APPOINTMENTS" +
                "($COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, $COLUMN_PATIENT_DNI TEXT, $COLUMN_DATE TEXT, $COLUMN_STUDY_ID  INTEGER, FOREIGN KEY($COLUMN_PATIENT_DNI) REFERENCES $TABLE_PACIENTE($COLUMN_DNI), FOREIGN KEY($COLUMN_STUDY_ID) REFERENCES $TABLE_STUDIES($COLUMN_ID))"
        db?.execSQL(createAppointmentsTable)

        db?.execSQL("INSERT INTO $TABLE_PACIENTE ($COLUMN_DNI, $COLUMN_OS, $COLUMN_NOMBRE, $COLUMN_APELLIDO) VALUES ('1', '2200', 'Santiago', 'Rubio')")

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

        // Medical History
        val createMHTable = "CREATE TABLE $TABLE_MH" +
                "($MH_COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, $MH_COLUMN_DNI TEXT, $MH_COLUMN_DATE TEXT, $MH_COLUMN_DOCTOR TEXT, $MH_COLUMN_DETAIL TEXT)"
        db?.execSQL(createMHTable)


        db?.execSQL("INSERT INTO $TABLE_MH ($MH_COLUMN_DNI, $MH_COLUMN_DATE, $MH_COLUMN_DOCTOR, $MH_COLUMN_DETAIL) VALUES ('1','10/05/2023','Javier Rodriguez','Lo revisé y estaba todo bien.')")
        db?.execSQL("INSERT INTO $TABLE_MH ($MH_COLUMN_DNI, $MH_COLUMN_DATE, $MH_COLUMN_DOCTOR, $MH_COLUMN_DETAIL) VALUES ('1','10/06/2023','Javier Rodriguez','Lo revisé y le recomendé que duerma más.')")

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_USER")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_PACIENTE")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_APPOINTMENTS")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_STUDIES")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_SUPPLY")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_MH")
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

    fun getPatient(dni: String?, os: String ?= ""): Paciente {
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

    fun createPatient(dni: String, os: String?, nombre: String, apellido: String): Paciente {
        val db = this.writableDatabase
        val container = ContentValues()

        container.put(COLUMN_DNI, dni)
        if(!os.isNullOrEmpty()) container.put(COLUMN_OS, os)
        container.put(COLUMN_NOMBRE, nombre)
        container.put(COLUMN_APELLIDO, apellido)

        val result = db.insert(TABLE_PACIENTE, null, container)
        return if(result == (-1).toLong()){
            throw Exception("Error al crear paciente")
        } else {
            return Paciente(dni, os.toString(), nombre, apellido)
        }
    }

    fun createAppointment(studyId: String, date: String): Long {
        val f = SimpleDateFormat("dd/MM/yy HH:mm")
        val db = this.writableDatabase
        val container = ContentValues()
        container.put(COLUMN_STUDY_ID, studyId.toInt())
        container.put(COLUMN_DATE, f.parse(date).toString())

        val result = db.insert(TABLE_APPOINTMENTS, null, container)
        return if(result == (-1).toLong()){
            throw Exception("Error al crear turno")
        } else {
            result
        }
    }

    fun assignAppointmentToPatient(appointmentId: Int, dni: String?, os: String?, nombre: String?, apellido: String?): Int {
        var p: Paciente
        try {
            p = getPatient(dni, os)
        } catch(e: Exception) {
            if (dni.isNullOrEmpty() || nombre.isNullOrEmpty() || apellido.isNullOrEmpty()) throw Exception("Datos de paciente insuficientes")
            val pData = createPatient(dni, os, nombre, apellido)
            p = getPatient(pData.DNI, pData.OS)
        }
        val db = this.writableDatabase
        val container = ContentValues()
        container.put(COLUMN_PATIENT_DNI, p.DNI)
        val result = db.update(TABLE_APPOINTMENTS, container, "$COLUMN_ID = ?", arrayOf(appointmentId.toString()))
        return if(result == 0){
            throw Exception("Error al asignar turno")
        } else {
            result
        }
    }

    fun getPatientAppointments(dni: String?): MutableList<Appointment> {
        if (dni.isNullOrEmpty()) throw Exception("Datos de paciente insuficientes");
        val db = this.readableDatabase;
        val list = mutableListOf<Appointment>()
        val cursor = db.rawQuery("SELECT $TABLE_STUDIES.$COLUMN_ID, $COLUMN_PATIENT_DNI, $COLUMN_DATE, $COLUMN_TYPE, $COLUMN_NAME FROM $TABLE_APPOINTMENTS INNER JOIN $TABLE_STUDIES ON $TABLE_APPOINTMENTS.$COLUMN_STUDY_ID = $TABLE_STUDIES.$COLUMN_ID WHERE $COLUMN_PATIENT_DNI = ?", arrayOf(dni))
        if(cursor.moveToFirst()){
            do{
                val appointment = Appointment(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4)
                )
                list.add(appointment)
            } while(cursor.moveToNext())
            db.close()
            cursor.close()
        }
        return list
    }

    fun getAllStudies(type: String? = null): MutableList<Study>{
        val bd = this.readableDatabase
        val list = mutableListOf<Study>()
        var sql = "SELECT * FROM $TABLE_STUDIES"
        if (!type.isNullOrEmpty()) {
            sql += " WHERE $COLUMN_TYPE = '$type'"
        }
        val cursor = bd.rawQuery(sql, null)

        if(cursor.moveToFirst()){
            do{
                val study = Study(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                )
                list.add(study)
            } while(cursor.moveToNext())
            bd.close()
            cursor.close()
        }
        return list
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

    fun getAllMH(dni: String): MutableList<MHEntry> {
        val bd = this.readableDatabase
        val list = mutableListOf<MHEntry>()
        val sql = "SELECT * FROM $TABLE_MH WHERE $MH_COLUMN_DNI = ?"
        val cursor = bd.rawQuery(sql, arrayOf(dni))
        if(cursor.moveToFirst()){
            do {
                val entry = MHEntry(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4)
                )
                list.add(entry)
                }
                while(cursor.moveToNext())
            bd.close()
            cursor.close()

        }
        return list
    }

    fun addMHEntry(dni: String, doctor: String, detail: String){
        //adds new entry to the database
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(MH_COLUMN_DNI, dni)
        contentValues.put(MH_COLUMN_DATE, SimpleDateFormat("dd/MM/yy", Locale.getDefault()).format(Date()))
        contentValues.put(MH_COLUMN_DOCTOR, doctor)
        contentValues.put(MH_COLUMN_DETAIL, detail)
        db.insert(TABLE_MH, null, contentValues)
        db.close()


    }
}