package cl.aiep.android.registrodelugares

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun irRegistro(view: View) {
        startActivity(Intent(this, RegistrarLugar::class.java))
    }
    fun mostrarLugares(view: View) {
        startActivity(Intent(this, ListarLugar::class.java))
    }
}