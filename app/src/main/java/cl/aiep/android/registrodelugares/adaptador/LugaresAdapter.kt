package cl.aiep.android.registrodelugares.adaptador

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import cl.aiep.android.registrodelugares.R
import cl.aiep.android.registrodelugares.classes.Lugares

class LugaresAdapter(val listaLugar: List<Lugares>) : RecyclerView.Adapter<LugaresAdapter.ViewHolder>() {
    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtNombre: TextView
        val txtLocalidad: TextView
        val txtDescripcion: TextView
        val btnBorrar: Button

        init {
            // Define click listener for the ViewHolder's View.
            txtNombre = view.findViewById(R.id.txtNombre)
            txtLocalidad = view.findViewById(R.id.txtLocalidad)
            txtDescripcion = view.findViewById(R.id.txtDescripcion)
            btnBorrar = view.findViewById(R.id.btnBorrarRegistro)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_lista, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        //Nombre
        holder.txtNombre.text = listaLugar.get(position).nombre
        //Localidad
        holder.txtLocalidad.text = listaLugar.get(position).localidad
        //Descripcion
        holder.txtDescripcion.text = listaLugar.get(position).descripcion
        //Accion al boton Borrar
        holder.btnBorrar.setOnClickListener {
            Toast.makeText(holder.btnBorrar.context,"Click en la Posición $position", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return listaLugar.size
    }
}