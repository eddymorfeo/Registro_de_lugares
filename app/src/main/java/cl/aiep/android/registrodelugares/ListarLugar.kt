package cl.aiep.android.registrodelugares

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import cl.aiep.android.registrodelugares.bd.Constantes
import cl.aiep.android.registrodelugares.bd.Constantes.FIREBASE
import cl.aiep.android.registrodelugares.bd.Constantes.LOCAL
import cl.aiep.android.registrodelugares.databinding.ActivityListarLugarBinding
import cl.aiep.android.registrodelugares.databinding.ActivityRegistrarLugarBinding

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

    private fun listarData(fuenteDatos: String) {

    }
}