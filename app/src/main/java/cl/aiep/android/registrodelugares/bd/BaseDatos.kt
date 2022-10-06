package cl.aiep.android.registrodelugares.bd

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import cl.aiep.android.registrodelugares.bd.Constantes.KEY_DESCRIPCION
import cl.aiep.android.registrodelugares.bd.Constantes.KEY_ID
import cl.aiep.android.registrodelugares.bd.Constantes.KEY_LOCALIDAD
import cl.aiep.android.registrodelugares.bd.Constantes.KEY_NOMBRE
import cl.aiep.android.registrodelugares.bd.Constantes.KEY_PAIS
import cl.aiep.android.registrodelugares.bd.Constantes.KEY_REGION
import cl.aiep.android.registrodelugares.bd.Constantes.TABLA_LUGARES
import cl.aiep.android.registrodelugares.classes.Lugares

class BaseDatos(
    context: Context,
    nombre: String,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) : SQLiteOpenHelper(
    context, nombre, factory, version
) {
    //Hay que declarar dos variables


    override fun onCreate(db: SQLiteDatabase?) {
        val createTable: String =
            "CREATE TABLE $TABLA_LUGARES ($KEY_ID INTEGER PRIMARY KEY AUTOINCREMENT, $KEY_NOMBRE TEXT, $KEY_LOCALIDAD TEXT, $KEY_REGION TEXT, $KEY_PAIS TEXT, $KEY_DESCRIPCION TEXT)"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS " + TABLA_LUGARES)
        onCreate(db)
    }

    //Método para insertar la información
    fun agregarLugares(datoRecibido:Lugares) : Long{
        //Declaramos la base de datos en modo de escritura
        val db = this.writableDatabase
        //Declaramos un contenedor de datos que vamos a guardar en la base de datos
        val contentValues = ContentValues()
        //Le asignamos los datos al contenedor con sus respectivas keys
        contentValues.put(KEY_NOMBRE, datoRecibido.nombre)
        contentValues.put(KEY_LOCALIDAD, datoRecibido.localidad)
        contentValues.put(KEY_REGION, datoRecibido.region)
        contentValues.put(KEY_PAIS, datoRecibido.pais)
        contentValues.put(KEY_DESCRIPCION, datoRecibido.descripcion)
        //Insertar los datos
        val success = db.insert(TABLA_LUGARES, null, contentValues)
        db.close()
        return success
    }
}