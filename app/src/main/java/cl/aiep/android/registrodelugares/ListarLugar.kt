package cl.aiep.android.registrodelugares

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import cl.aiep.android.registrodelugares.adaptador.LugaresAdapter
import cl.aiep.android.registrodelugares.bd.Constantes
import cl.aiep.android.registrodelugares.bd.Constantes.FIREBASE
import cl.aiep.android.registrodelugares.bd.Constantes.KEY_DESCRIPCION
import cl.aiep.android.registrodelugares.bd.Constantes.KEY_LOCALIDAD
import cl.aiep.android.registrodelugares.bd.Constantes.KEY_NOMBRE
import cl.aiep.android.registrodelugares.bd.Constantes.KEY_PAIS
import cl.aiep.android.registrodelugares.bd.Constantes.KEY_REGION
import cl.aiep.android.registrodelugares.bd.Constantes.LOCAL
import cl.aiep.android.registrodelugares.bd.Constantes.TABLA_LUGARES
import cl.aiep.android.registrodelugares.classes.Lugares
import cl.aiep.android.registrodelugares.databinding.ActivityListarLugarBinding
import cl.aiep.android.registrodelugares.databinding.ActivityRegistrarLugarBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ListarLugar : AppCompatActivity() {

    lateinit var binding: ActivityListarLugarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Nueva forma de vincular la vista con el activity
        binding = ActivityListarLugarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Configurar la accion para los botones
        binding.btnCargarBDLocal.setOnClickListener {
            listarData(LOCAL)
        }

        binding.btnCargarBDFirebase.setOnClickListener {
            listarData(FIREBASE)
        }
    }
    //Funcion para listar la data
    private fun listarData(fuenteDatos: String) {
        //Generando una lista vacia sin data
        var listaData = ArrayList<Lugares>()
        //Aca se decide que fuente de datos se va a usar
        if (fuenteDatos== FIREBASE){
            //Crea la instancia de la base de datos de firebase
            val db = Firebase.firestore
            //Que tabla vamos a obtener
            db.collection(TABLA_LUGARES)
                .get()
                .addOnSuccessListener { result ->
                    //Si la data es correcta, vamos hacer un for para recorrerla
                    for (document in result) {
                        Log.d("Data", "${document.id} => ${document.data}")
                        //Se crea un objeto llamado lugar y se crea como objeto para agregarlo a la vista
                        val lugar = Lugares(
                            document.id,
                            document.data[KEY_NOMBRE].toString(),
                            document.data[KEY_LOCALIDAD].toString(),
                            document.data[KEY_REGION].toString(),
                            document.data[KEY_PAIS].toString(),
                            document.data[KEY_DESCRIPCION].toString()
                        )
                        //Se agrega la data a la vista
                        listaData.add(lugar)
                    }
                    //Configurar la lista
                    mostrarDataEnLista(listaData)
                }
                .addOnFailureListener { exception ->
                    Log.w("Data", "Error getting documents.", exception)
                }

        }else{
            //Basae de datos local
            mostrarDataEnLista(listaData)
        }
    }

    private fun mostrarDataEnLista(listaData: ArrayList<Lugares>) {
        //Creamos el adaptador con la data que recibimos
        val adaptadorDataLugares = LugaresAdapter(listaData)
        //Vamos a configurar la lista del RecyclerView
        binding.listaData.apply {
            // vertical layout
            layoutManager = LinearLayoutManager(applicationContext)

            //GridLayaout, el numero final indica la cantidad de columnas que mostrara
            //layoutManager = GridLayoutManager(applicationContext, 2)

            // set adapter
            adapter = adaptadorDataLugares
            //adaptadorDataLugares.notifyDataSetChanged()
        }
    }
}
