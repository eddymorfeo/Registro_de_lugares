package cl.aiep.android.registrodelugares.classes

class Lugares(
    var id: String?,
    val nombre: String,
    val localidad: String,
    val region: String,
    val pais: String,
    val descripcion: String,
) {
    fun registroYPais(): String{
        return "$region - $pais"
    }
}