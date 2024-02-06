package com.example.juliangarrido_examenxml.models

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root


@Root(name = "receta")
data class Receta(
    @field:ElementList(inline = true, entry = "ingrediente")
    var ingrediente: List<Ingrediente> = mutableListOf(),
)

@Root(name = "ingrediente")
data class Ingrediente(
    @field:Element(name = "alimento")
    var alimento: Alimento? = null,
    @field:Element(name = "cantidad")
    var cantidad: Int = 0,

    @field:Attribute(name = "nombre", required = false)
    var nombre: String? = null,
    )

@Root(name = "alimento")
data class Alimento(
    @field:Element(name = "proteinas")
    var proteinas: Int? = 0,

    @field:Element(name = "grasas")
    var grasas:Int? = 0,

    @field:Element(name = "hidratos")
    var hidratos: Float = 0f,
)