package cl.aiep.android.registrodelugares

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import cl.aiep.android.registrodelugares.bd.BaseDatos
import cl.aiep.android.registrodelugares.bd.Constantes
import cl.aiep.android.registrodelugares.bd.Constantes.KEY_DESCRIPCION
import cl.aiep.android.registrodelugares.bd.Constantes.KEY_LOCALIDAD
import cl.aiep.android.registrodelugares.bd.Constantes.KEY_NOMBRE
import cl.aiep.android.registrodelugares.bd.Constantes.KEY_PAIS
import cl.aiep.android.registrodelugares.bd.Constantes.KEY_REGION
import cl.aiep.android.registrodelugares.bd.Constantes.TABLA_LUGARES
import cl.aiep.android.registrodelugares.bd.Constantes.VERSIONBD
import cl.aiep.android.registrodelugares.classes.Lugares
import cl.aiep.android.registrodelugares.databinding.ActivityRegistrarLugarBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RegistrarLugar : AppCompatActivity() {

    private lateinit var binding: ActivityRegistrarLugarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Nueva forma de vincular la vista con el activity
        binding = ActivityRegistrarLugarBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //Vamos a asignar una acción al onclick de guardar
        binding.btnGuardar.setOnClickListener {
            guardarDatos()
        }
        //Vamos a guardar datos en Firebase
        binding.btnGuardarFirebase.setOnClickListener {
            guardarDatosFirebase()
        }

    }

    //Metodo para guardar datos en base de datos local
    private fun guardarDatos() {
        //Declarar el objeto que voy a llenar de informacion
        //val datoLugar:Lugares
        //datoLugar.nombre=binding.txtNombre.editText!!.text.toString()
        val nombre =binding.etNombre.editText?.text.toString()
        val localidad =binding.etLocalidad.editText?.text.toString()
        val region =binding.etRegion.editText?.text.toString()
        val pais =binding.etPais.editText?.text.toString()
        val descripcion =binding.etDescripcion.editText?.text.toString()

        val datoLugar = Lugares(null,nombre, localidad, region, pais, descripcion)
        var baseDeDatos = BaseDatos(this, KEY_NOMBRE, null, VERSIONBD)
        val status = baseDeDatos.agregarLugares(datoLugar)
        if (status > -1){
            Toast.makeText(this, "Dato guardado con éxito ($status)", Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(this, "Error al guardar el dato ($status) ", Toast.LENGTH_LONG).show()
        }
    }

    //Metodo para guardar datos en base de datos en Firebase
    private fun guardarDatosFirebase() {
        // Access a Cloud Firestore instance from your Activity
        val db = Firebase.firestore
        //Vamos a byscar la informacion que agrego el usuario
        val nombre =binding.etNombre.editText?.text.toString()
        val localidad =binding.etLocalidad.editText?.text.toString()
        val region =binding.etRegion.editText?.text.toString()
        val pais =binding.etPais.editText?.text.toString()
        val descripcion =binding.etDescripcion.editText?.text.toString()
        // Create a new user with a first and last name
        //Create new hashMapOf with a data
        val lugarAGuardar = hashMapOf(
            KEY_NOMBRE to nombre,
            KEY_LOCALIDAD to localidad,
            KEY_REGION to region,
            KEY_PAIS to pais,
            KEY_DESCRIPCION to descripcion
        )

        // Add a new document with a generated ID
        db.collection(TABLA_LUGARES)
            .add(lugarAGuardar)
            .addOnSuccessListener { documentReference ->
                Toast.makeText(this, "Dato guardado con éxito (${documentReference.id})", Toast.LENGTH_LONG).show()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Error al guardar el dato (${e.message}) ", Toast.LENGTH_LONG).show()
            }
    }

}