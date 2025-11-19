package com.example.pokedexproyectocarloscaramecerero

/**
 * Data class que define un Pokémon.
 */
data class Pokemon(
    val nombre: String,
    val tipo1: TIPO,
    val tipo2: TIPO?,
    val habilidades: List<String>,
    val descripcion: String,
    val imagen: Int
)