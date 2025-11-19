package com.example.pokedexproyectocarloscaramecerero

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.pokedexproyectocarloscaramecerero.ui.theme.PokedexProyectoCarlosCarameCereroTheme

/**
 * Flujo del programa. Ejecutar aquí.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PokedexProyectoCarlosCarameCereroTheme {
                Pokedex()
            }
        }
    }
}

