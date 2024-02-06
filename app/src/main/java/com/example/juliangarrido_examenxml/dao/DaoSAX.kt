package com.example.juliangarrido_examenxml.dao

import android.content.Context
import android.util.Log
import com.example.juliangarrido_examenxml.IngredienteHandler
import javax.xml.parsers.SAXParserFactory

class DaoSAX(private val context: Context)  {
    fun procesarArchivoAssetsXMLSAX() {
        try {
            val factory = SAXParserFactory.newInstance()
            val parser = factory.newSAXParser()
            val handler = IngredienteHandler()
            val inputStream = context.assets.open("recetas.xml")

            parser.parse(inputStream, handler)

            handler.ingredientes.forEach {
                // Imprime información sobre cada trabajador en el archivo XML
                Log.d("XMLSAX", it.toString())
            }

            /*//edad media de trabajadores
            var edadMedia=0
            var cont=0
            handler.trabajadoresTr.forEach {
                edadMedia+=it.edad
                cont++
            }
            edadMedia=edadMedia/cont
            Log.d("EdadMedia","Edad Media Trabajadores: $edadMedia" )*/

        } catch (e: Exception) {
            // Maneja las excepciones imprimiendo la traza en la consola
            e.printStackTrace()
            Log.d("error", e.toString());
        }
    }
}