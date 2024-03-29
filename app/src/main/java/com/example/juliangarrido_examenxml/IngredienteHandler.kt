package com.example.juliangarrido_examenxml

import android.util.Log
import com.example.juliangarrido_examenxml.models.*
import org.xml.sax.Attributes
import org.xml.sax.SAXException
import org.xml.sax.helpers.DefaultHandler

class IngredienteHandler : DefaultHandler(){
    private val cadena = StringBuilder()
    private var ingrediente:Ingrediente? = null
    private var alimento: Alimento? = null
    private var proteina: Proteinas? = null
    private var grasas: Grasas? = null
    private var hidratos: Hidratos? = null

    var ingredientes: MutableList<Ingrediente> = mutableListOf()

    @Throws(SAXException::class)
    override fun startDocument() {
        cadena.clear()
        ingredientes = mutableListOf()
        Log.d("SAX", "abriendo el documento")
    }

    @Throws(SAXException::class)
    override fun startElement(uri: String, nombreLocal: String, nombre: String, attributes: Attributes) {
        cadena.setLength(0)
        if (nombre == "ingrediente") {
            ingrediente = Ingrediente()
            ingrediente?.nombre = attributes.getValue("nombre")
            Log.d("SAX", "abriendo etiqueta trabajador")
        }

        if (nombre == "proteinas") {
//            alimento = Alimento()
            proteina = Proteinas()
            proteina?.cantidad100g = attributes.getValue("cantidad100g").toInt()
            Log.d("SAX", "abriendo etiqueta trabajador")
        }
        if (nombre == "grasas") {
            grasas = Grasas()
            grasas?.cantidad100g = attributes.getValue("cantidad100g").toInt()
            Log.d("SAX", "abriendo etiqueta trabajador")
        }
        if (nombre == "hidratos") {
            hidratos = Hidratos()
            hidratos?.cantidad100g = attributes.getValue("cantidad100g").toFloat()
            Log.d("SAX", "abriendo etiqueta trabajador")
        }

        if ( nombre == "alimento"){
            alimento = Alimento()
        }

    }

    @Throws(SAXException::class)
    override fun characters(ch: CharArray, start: Int, length: Int) {
        cadena.append(ch, start, length)
        Log.d("SAX", "guardando en una cadena $cadena")
    }

    @Throws(SAXException::class)
    override fun endElement(uri: String, nombreLocal: String, nombre: String) {
        when (nombre) {

            "alimento" -> ingrediente?.alimento= alimento
            "cantidad" -> ingrediente?.cantidad = cadena.toString().toInt()
            "ingrediente" ->{
                ingredientes.add(ingrediente!!)
                Log.d("alimento", ingredientes.toString())
            }
        }

        Log.d("SAX", "cerrando elemento $nombre $nombreLocal")
    }

    @Throws(SAXException::class)
    override fun endDocument() {
        Log.d("alimento", ingredientes.toString())
        Log.d("SAX", "Documento Terminado")
    }

}