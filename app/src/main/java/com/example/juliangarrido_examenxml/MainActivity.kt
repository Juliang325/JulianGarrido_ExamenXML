package com.example.juliangarrido_examenxml

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.juliangarrido_examenxml.dao.DaoSAX
import javax.xml.parsers.SAXParserFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("XMLSAX", "probando SAX")
        var daoSAX= DaoSAX(applicationContext)
        daoSAX.procesarArchivoAssetsXMLSAX()
        Log.d("XMLSAX", "SAX terminado")
        procesarArchivoXMLSAX()
    }

    private fun procesarArchivoXMLSAX() {
        try {
            val factory = SAXParserFactory.newInstance()
            val parser = factory.newSAXParser()
            val handler = IngredienteHandler()

            val inputStream = assets.open("recetas.xml")
            parser.parse(inputStream, handler)


            handler.ingredientes.forEach {
                Log.d("SAX", "ingredientes: ${it.toString()}")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}
