package com.example.juliangarrido_examenxml.dao

import android.content.Context
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.juliangarrido_examenxml.models.Ingrediente
import com.example.juliangarrido_examenxml.models.Receta
import org.simpleframework.xml.core.Persister
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader

class DaoSimpleXML (private val context: Context) {
    var receta = mutableListOf<Ingrediente>()

    fun procesarArchivoAssetsXML() {
        val serializer = Persister()
        var inputStream: InputStream? = null
        var reader: InputStreamReader? = null

        try {
            inputStream = context.assets.open("recetas.xml")//importante pasar el context
            reader = InputStreamReader(inputStream)
            val trabajadoresListType = serializer.read(Receta::class.java, reader, false)
            receta.addAll(trabajadoresListType.ingrediente)
            Log.d("simple", receta.toString())


        } catch (e: Exception) {
            // Manejo de errores
            e.printStackTrace()
            Log.d("error", e.toString())
        } finally {
            // Cerrar inputStream y reader
            try {
                reader?.close()
                inputStream?.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

}
//    fun copiarArchivoDesdeAssets() {
//        val nombreArchivo = "trabajadores.xml"
//        val archivoEnAssets = context.assets.open(nombreArchivo)
//        val archivoInterno = context.openFileOutput(
//            nombreArchivo,
//            AppCompatActivity.MODE_PRIVATE
//        )//tener en cuenta el context y AppCompatActivity
//
//        archivoEnAssets.copyTo(archivoInterno)
//        archivoEnAssets.close()
//        archivoInterno.close()
//
//    }
//
//    fun addTrabajador(trabajador: Trabajador) {
//        try {
//            val serializer = Persister()
//            trabajadoresTr.add(trabajador)
//            val trabajadoresList = Trabajadores(trabajadoresTr)
//            val outputStream = context.openFileOutput(
//                "trabajadores.xml",
//                AppCompatActivity.MODE_PRIVATE
//            )//***Añadir context
//            serializer.write(trabajadoresList, outputStream)
//            outputStream.close() // Asegúrate de cerrar el outputStream después de escribir
//        } catch (e: Exception) {
//            e.printStackTrace() // Manejo de errores adecuado
//        }
//    }
//    fun addBecario(becario: Becario) {
//        try {
//            val serializer = Persister()
//            trabajadoresBec.add(becario)
//            val becariosList = Trabajadores(trabajadoresTr,trabajadoresBec)
//            val outputStream = context.openFileOutput(
//                "trabajadores.xml",
//                AppCompatActivity.MODE_PRIVATE
//            )//***Añadir context
//            serializer.write(becariosList, outputStream)
//            outputStream.close() // Asegúrate de cerrar el outputStream después de escribir
//        } catch (e: Exception) {
//            e.printStackTrace() // Manejo de errores adecuado
//        }
//    }
//
//    fun ProcesarArchivoXMLInterno() {
//        val nombreArchivo = "trabajadores.xml"
//        val serializer = Persister()
//
//        try {
//            // Abrir el archivo para lectura
//            val file = File(context.filesDir, nombreArchivo)//***IMP context
//            val inputStream = FileInputStream(file)
//            val trabajadoresList = serializer.read(Trabajadores::class.java, inputStream)
//            trabajadoresTr.clear() //evitar duplicar datos, limpiamos lista de trabajadores, no el archivo interno
//            trabajadoresBec.clear()
//            trabajadoresTr.addAll(trabajadoresList.trabajador)
//            trabajadoresTr.forEach(){
//                Log.d("assetsXML-interno", it.toString())
//            }
//            trabajadoresBec.addAll(trabajadoresList.becario)
//            trabajadoresBec.forEach(){
//                Log.d("assetsXML-interno", it.toString())
//            }
//            inputStream.close()
//
//        } catch (e: Exception) {
//
//            e.printStackTrace()
//        }
//    }
//
//    fun vaciarArchivoInterno() { //vaciamos el archivo interno
//        val nombreArchivo = "trabajadores.xml"
//
//        // Abre el archivo interno para escritura (esto eliminará el contenido existente)
//        val archivoInternoEscritura =
//            context.openFileOutput(nombreArchivo, AppCompatActivity.MODE_PRIVATE)
//        archivoInternoEscritura.write("".toByteArray())
//
//        // Cierra el archivo
//        archivoInternoEscritura.close()
//    }
//}