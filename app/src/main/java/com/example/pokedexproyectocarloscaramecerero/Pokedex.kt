package com.example.pokedexproyectocarloscaramecerero

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle.Companion.Italic
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pokedexproyectocarloscaramecerero.ui.theme.listaPokemon

@Composable
fun Pokedex() {

    // ESTADO QUE CAMBIA LA VISTA
    var selectedView by remember { mutableStateOf(PokedexView.COLUMN) }

    // ESTADO PARA EL COLOR DEL TOPBAR
    val topBarColor = when(selectedView) {
        PokedexView.COLUMN -> Color.Red
        PokedexView.GRID -> Color.Blue
        PokedexView.STICKY -> Color(0xFF8000FF) // morado
    }

    Column(Modifier.fillMaxSize()) {

        // TOP BAR DINÁMICO
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(topBarColor)
                .border(BorderStroke(4.dp, Color.Black)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "POKEDEX",
                color = Color.Yellow,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = Italic
            )
        }

        // CONTENIDO DINÁMICO SEGÚN LA VISTA
        Box(Modifier.weight(1f)) {
            when (selectedView) {
                PokedexView.COLUMN -> PokedexColumn()
                PokedexView.GRID -> PokedexGrid()
                PokedexView.STICKY -> PokedexStickyHeader()
            }
        }

        // BOTTOM BAR
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .background(Color.White)
                .border(BorderStroke(4.dp, Color.Black)),
        ){
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(24.dp, Alignment.CenterHorizontally)
            ) {

                // Botón – LazyColumn (ROJO)
                Image(
                    modifier = Modifier
                        .size(80.dp)
                        .clickable { selectedView = PokedexView.COLUMN },
                    painter = painterResource(id = R.drawable.pokeball),
                    contentDescription = "Pokeball",
                )

                // Botón – Grid (AZUL)
                Image(
                    modifier = Modifier
                        .size(80.dp)
                        .clickable { selectedView = PokedexView.GRID },
                    painter = painterResource(id = R.drawable.superball),
                    contentDescription = "SuperBall",
                )

                // Botón – StickyHeader (MORADO)
                Image(
                    modifier = Modifier
                        .size(80.dp)
                        .clickable { selectedView = PokedexView.STICKY },
                    painter = painterResource(id = R.drawable.masterball),
                    contentDescription = "MasterBall",
                )
            }
        }
    }
}


/**
 * Columna que almacena todos las cards de los pokemon
 */
@Composable
fun PokedexColumn(){
    LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        items(listaPokemon) { pokemon ->
            PokemonCard(pokemon = pokemon)
        }
    }
}

/**
 * Mismo caso que el column pero con celdas
 */
@Composable
fun PokedexGrid(){
    LazyVerticalGrid(columns = GridCells.Fixed(2), verticalArrangement = Arrangement.spacedBy(8.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)){
        items(listaPokemon) { pokemon ->
            PokemonCardVertical(pokemon = pokemon)
        }
    }
}

/**
 * Aqui deberia de hacer lo siguiente:
 *
 * Ordenar las cards en sticky headers segun el tipo, si los pokemon tienen doble tipo estos estaran en ambos headers, si el pokemon es Multitipo este estará en todos los
 * headers
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PokedexStickyHeader(){
    val allTypes = TIPO.entries.filter { it != TIPO.MULTITIPO }
val multitipoPokemon = listaPokemon.filter { it.tipo1 == TIPO.MULTITIPO}

    val pokemonByTipo = allTypes.associateWith { tipo ->
        listaPokemon.filter { pokemon ->
            pokemon.tipo1 == tipo || pokemon.tipo2 == tipo
        } + multitipoPokemon
    }.toSortedMap(compareBy { it.name })


    LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)){
        pokemonByTipo.forEach { (tipoEnum, pokemonList) ->
            // No mostrar el header si la lista está vacía (aunque con Multitipo, no debería pasar)
            if (pokemonList.isEmpty()) return@forEach

            val tipo = tipoEnum.name
            stickyHeader {
                Text(text = tipo,
                    modifier = Modifier
                        .background(
                            when (tipoEnum) {
                                TIPO.PLANTA -> {
                                    Color(0xFF6CFF5F)
                                }
                                TIPO.FUEGO -> {
                                    Color(0xFFFF2626)
                                }
                                TIPO.ELECTRICO -> {
                                    Color(0xFFFFE054)
                                }
                                TIPO.AGUA -> {
                                    Color(0xFF5577FF)
                                }
                                TIPO.ROCA -> {
                                    Color(0xffAB9975)
                                }
                                TIPO.TIERRA -> {
                                    Color(0xFFD29C31)
                                }
                                TIPO.VENENO -> {
                                    Color(0xFFC333F4)
                                }
                                TIPO.PSIQUICO -> {
                                    Color(0xFFFF1AB3)
                                }
                                TIPO.SINIESTRO -> {
                                    Color(0xFF382532)
                                }
                                TIPO.FANTASMA -> {
                                    Color(0xFF4700A9)
                                }
                                TIPO.ACERO -> {
                                    Color(0xFFBCADB5)
                                }
                                TIPO.HADA -> {
                                    Color(0xFFFFACC8)
                                }
                                TIPO.DRAGON -> {
                                    Color(0xFFA86BFF)
                                }
                                TIPO.NORMAL -> {
                                    Color(0xFFF0F0F0)
                                }
                                TIPO.LUCHA -> {
                                    Color(0xFFFFA600)
                                }
                                TIPO.VOLADOR -> {
                                    Color(0xFF8BF7FA)
                                }
                                TIPO.BICHO -> {
                                    Color(0xFFB4FF9D)}
                                TIPO.HIELO -> {
                                    Color(0xFFA7FFE9)
                                }
                                else -> { Color.Transparent }
                            }
                    )
                        .padding(8.dp)
                        .fillMaxWidth()
                )
            }
            items(pokemonList.distinct()) { pokemonItem ->
                PokemonCard(pokemon = pokemonItem)
            }
        }
    }
}

/**
 * Aqui se definen las cards de los pokemon
 */
@Composable
fun PokemonCard(pokemon: Pokemon) {
    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text(text = pokemon.nombre) },
            text = { Text(text = pokemon.descripcion) },
            confirmButton = {
                Button(onClick = { showDialog = false }) {
                    Text("Cerrar")
                }
            }
        )
    }

    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
        .clickable { showDialog = true },
        border = BorderStroke(2.dp, Color.Black),
        colors = CardDefaults.cardColors(containerColor = Color.Red)
    ) {
        Row (modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
            Image(painter = painterResource(id = pokemon.imagen),
                contentDescription = pokemon.nombre,
                modifier = Modifier.size(100.dp))
            Box(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .background(Color(0xFFA6E9FF)) // Light Blue
                    .border(BorderStroke(2.dp, Color.Black))
                    .padding(8.dp)
                    .fillMaxWidth()
            ) {
                Column {
                    Text(text = "Nombre: ${pokemon.nombre}", fontWeight = FontWeight.Bold)
                    Text(text = "Tipo 1: ${pokemon.tipo1.name}")
                    pokemon.tipo2?.let { Text(text = "Tipo 2: ${it.name}") }
                    Text(text = "Habilidades: ${pokemon.habilidades.joinToString()}")
                }
            }
        }
    }
}

@Composable
fun PokemonCardVertical(pokemon: Pokemon) {
    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text(text = pokemon.nombre) },
            text = { Text(text = pokemon.descripcion) },
            confirmButton = {
                Button(onClick = { showDialog = false }) {
                    Text("Cerrar")
                }
            }
        )
    }

    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
        .clickable { showDialog = true },
        border = BorderStroke(2.dp, Color.Black),
        colors = CardDefaults.cardColors(containerColor = Color.Red)
    ) {
        Column (modifier = Modifier.padding(8.dp), horizontalAlignment = Alignment.CenterHorizontally , verticalArrangement = Arrangement.Center) {
            Image(painter = painterResource(id = pokemon.imagen),
                contentDescription = pokemon.nombre,
                modifier = Modifier.size(100.dp))
            Box(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .background(Color(0xFFA6E9FF)) // Light Blue
                    .border(BorderStroke(2.dp, Color.Black))
                    .padding(8.dp)
                    .fillMaxWidth()
            ) {
                Column {
                    Text(text = "Nombre: ${pokemon.nombre}", fontWeight = FontWeight.Bold)
                    Text(text = "Tipo 1: ${pokemon.tipo1.name}")
                    pokemon.tipo2?.let { Text(text = "Tipo 2: ${it.name}") }
                    Text(text = "Habilidades: ${pokemon.habilidades.joinToString()}")
                }
            }
        }
    }
}

@Preview
@Composable
fun PokedexPreview() {
    Pokedex()
}

