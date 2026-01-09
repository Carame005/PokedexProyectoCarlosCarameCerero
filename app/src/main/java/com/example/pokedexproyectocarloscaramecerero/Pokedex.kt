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
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PaintingStyle.Companion.Stroke
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle.Companion.Italic
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pokedexproyectocarloscaramecerero.ui.theme.listaPokemon

@Composable
/**
 * Composable principal que construye la interfaz de la Pokédex.
 * Gestiona el estado de la vista seleccionada (columna, grid o sticky header) y actualiza la UI en consecuencia.
 */
fun Pokedex() {

    // ESTADO QUE CAMBIA LA VISTA
    // Al iniciar la app, mostramos la vista de login
    var selectedView by remember { mutableStateOf(PokedexView.LOGIN) }

    // Estado del usuario logueado (null = no hay sesión)
    var loggedUser by remember { mutableStateOf<Usuario?>(null) }

    // Estado para mostrar el diálogo cuando se intenta acceder al admin sin permisos
    var showAccessDeniedDialog by remember { mutableStateOf(false) }

    // ESTADO PARA EL COLOR DEL TOPBAR
    val topBarColor = when(selectedView) {
        PokedexView.COLUMN -> Color.Red
        PokedexView.GRID -> Color.Blue
        PokedexView.STICKY -> Color(0xFF8000FF) // morado
        PokedexView.LOGIN -> Color.Green
        PokedexView.ADMIN -> Color.Yellow
    }

    //  Envolvemos TODO en un Box con la imagen de fondo
    Box(
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding()
    ) {

        //  FONDO (siempre fijo)
        Image(
            painter = painterResource(id = R.drawable.fondo),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Si estamos en LOGIN mostramos solo el login (pantalla independiente)
        if (selectedView == PokedexView.LOGIN) {
            Box(modifier = Modifier.fillMaxSize()) {
                Login(onLogin = { usuario ->
                    loggedUser = usuario
                    selectedView = if (usuario.admin) PokedexView.ADMIN else PokedexView.COLUMN
                })
            }
            return
        }

        //  INTERFAZ (encima del fondo) - sólo se muestra cuando no estamos en LOGIN
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

            // CONTENIDO DINÁMICO
            Box(Modifier.weight(1f)) {
                when (selectedView) {
                    PokedexView.COLUMN -> PokedexColumn()
                    PokedexView.GRID -> PokedexGrid()
                    PokedexView.STICKY -> PokedexStickyHeader()
                    PokedexView.ADMIN -> Admin(currentUser = loggedUser, onLogout = {
                        // Cerrar sesión y volver a la vista de LOGIN (pantalla independiente)
                        loggedUser = null
                        selectedView = PokedexView.LOGIN
                    })
                    else -> { /* no aplica */ }
                }
            }

            // Dialogo global para acceso denegado (se muestra cuando se pulsa el icono admin sin permisos)
            if (showAccessDeniedDialog) {
                AlertDialog(
                    onDismissRequest = { showAccessDeniedDialog = false },
                    title = { Text("Acceso denegado") },
                    text = { Text("No tienes permisos de administrador.") },
                    confirmButton = {
                        Button(onClick = { showAccessDeniedDialog = false }) {
                            Text("Aceptar")
                        }
                    }
                )
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
                    Image(
                        modifier = Modifier
                            .size(80.dp)
                            .clickable { selectedView = PokedexView.COLUMN },
                        painter = painterResource(id = R.drawable.pokeball),
                        contentDescription = "Pokeball",
                    )
                    Image(
                        modifier = Modifier
                            .size(80.dp)
                            .clickable { selectedView = PokedexView.GRID },
                        painter = painterResource(id = R.drawable.superball),
                        contentDescription = "SuperBall",
                    )
                    Image(
                        modifier = Modifier
                            .size(80.dp)
                            .clickable { selectedView = PokedexView.STICKY },
                        painter = painterResource(id = R.drawable.masterball),
                        contentDescription = "MasterBall",
                    )
                    // Imagen placeholder para acceder al panel de admin
                    Image(
                        modifier = Modifier
                            .size(80.dp)
                            .clickable {
                                // Si hay usuario y es admin, navegar; si no, mostrar dialogo de acceso denegado
                                if (loggedUser != null && loggedUser!!.admin) {
                                    selectedView = PokedexView.ADMIN
                                } else {
                                    showAccessDeniedDialog = true
                                }
                            },
                        painter = painterResource(id = R.drawable.pc),
                        contentDescription = "Admin",
                    )
                }
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
 * Ordenar las cards en sticky headers según el tipo.
 * Si un Pokémon tiene dos tipos, aparecerá en las secciones de ambos tipos.
 * Si un Pokémon es de tipo MULTITIPO, aparecerá en todas las secciones.
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PokedexStickyHeader(){
    val allTypes = TIPO.entries.filter { it != TIPO.MULTITIPO }
val multitipoPokemon = listaPokemon.filter { it.tipo1 == TIPO.MULTITIPO}

    /**
     * Agrupa los Pokémon por tipo.
     * Cada tipo se asocia con una lista de Pokémon que coincide con ese tipo (ya sea tipo1 o tipo2).
     * Además, los Pokémon de tipo MULTITIPO se añaden a cada lista.
     * El mapa resultante se ordena alfabéticamente por el nombre del tipo.
     */

    val pokemonByTipo = allTypes.associateWith { tipo ->
        listaPokemon.filter { pokemon ->
            pokemon.tipo1 == tipo || pokemon.tipo2 == tipo
        } + multitipoPokemon
    }.toSortedMap(compareBy { it.name })

    // Muestra la lista de Pokémon con encabezados fijos (sticky headers) para cada tipo.
    LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)){
        pokemonByTipo.forEach { (tipoEnum, pokemonList) ->
            // No muestra el encabezado si la lista de Pokémon para ese tipo está vacía.
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
                                    Color(0xFFdad7cd)
                                }
                                TIPO.FANTASMA -> {
                                    Color(0xFFcdb4db)
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
            // Muestra las tarjetas de los Pokémon para el tipo actual.
            }
            items(pokemonList.distinct()) { pokemonItem ->
                PokemonCard(pokemon = pokemonItem)
            }
        }
    }
}

/**
 * Define la apariencia de una tarjeta de Pokémon en formato de fila (horizontal).
 * Muestra un diálogo con la descripción del Pokémon al hacer clic.
 */
@Composable
fun PokemonCard(pokemon: Pokemon) {
    var showDialog by remember { mutableStateOf(false) }

    // Muestra un AlertDialog si showDialog es verdadero.
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

    // Diseño de la tarjeta del Pokémon.
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
                    Text(text = pokemon.nombre, fontWeight = FontWeight.Bold)
                    Text(text = pokemon.tipo1.desc)
                    pokemon.tipo2?.let { Text(text = it.desc) }
                    Text(text = pokemon.habilidades.joinToString())
                }
            }
        }
    }
}

/**
 * Define la apariencia de una tarjeta de Pokémon en formato de columna (vertical), usada en la vista de Grid.
 * Muestra un diálogo con la descripción del Pokémon al hacer clic.
 */
@Composable
fun PokemonCardVertical(pokemon: Pokemon) {
    var showDialog by remember { mutableStateOf(false) }

    // Muestra un AlertDialog si showDialog es verdadero.
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

    // Diseño de la tarjeta del Pokémon en formato vertical.
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
                    Text(text = pokemon.nombre, fontWeight = FontWeight.Bold)
                    Text(text = pokemon.tipo1.desc)
                    pokemon.tipo2?.let { Text(text = it.desc) }
                    Text(text = pokemon.habilidades.joinToString())
                }
            }
        }
    }
}

/**
 * Login que contará con tres campos: usuario,correo y contraseña con verificacion de datos y manejo de errores
 * una vez iniciado sesion se abrirá la vista de la pokedex y dependiendo de si está registrado cómo admin este
 * tendrá permisos (admin = true)
 *
 * onLogin: callback que recibe el Usuario creado al iniciar sesión correctamente.
 */
@Composable
fun Login(onLogin: (Usuario) -> Unit) {
    // Campos del formulario
    var usuario by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }

    // Mensaje de error / confirmación
    var errorMsg by remember { mutableStateOf<String?>(null) }

    // Dialogo de confirmación (opcional)
    var showSuccess by remember { mutableStateOf(false) }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        ) {
        Text(
            text = "POKEDEX",
            color = Color.Black,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = Italic
        
        )

        TextField(
            value = usuario,
            onValueChange = { usuario = it },
            label = { Text("Usuario") },
            modifier = Modifier.fillMaxWidth().padding(top = 12.dp)
        )

        TextField(
            value = correo,
            onValueChange = { correo = it },
            label = { Text("Correo") },
            modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
        )

        TextField(
            value = contrasena,
            onValueChange = { contrasena = it },
            label = { Text("Contraseña") },
            modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
        )

        errorMsg?.let { Text(text = it, color = Color.Red, modifier = Modifier.padding(top = 8.dp)) }

        Row(modifier = Modifier.padding(top = 12.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {

            Image(
                modifier = Modifier
                    .size(80.dp)
                    .clickable {
                        // Validaciones bássicas
                        when {
                            usuario.isBlank() -> errorMsg = "El usuario no puede estar vacío"
                            correo.isBlank() || !correo.contains("@") -> errorMsg = "Introduce un correo válido"
                            contrasena.length < 4 -> errorMsg = "La contraseña debe tener al menos 4 caracteres"
                            else -> {
                                errorMsg = null
                                // Credenciales de ejemplo para administrador (hardcoded)
                                val esAdmin = correo.trim().lowercase() == "admin@pokedex.com" && contrasena == "admin123"
                                val nuevoUsuario = Usuario(usuario = usuario.trim(), correo = correo.trim(), contrasena = contrasena, admin = esAdmin)
                                showSuccess = true
                                // Llamar al callback para notificar inicio de sesión
                                onLogin(nuevoUsuario)
                            } }},
                painter = painterResource(id = R.drawable.ball),
                contentDescription = "SuperBall",
            )
        }

        if (showSuccess) {
            AlertDialog(
                onDismissRequest = { showSuccess = false },
                title = { Text("Sesión iniciada") },
                text = { Text("Has iniciado sesión como $usuario") },
                confirmButton = {
                    Button(onClick = { showSuccess = false }) {
                        Text("Aceptar")
                    }
                }
            )
        }
    }
}

/***
 * Vista que te permite editar/crear una entrada en la pokedex, accesible unicamente por un usuario con permisos de admin
 *
 * currentUser: usuario que intenta acceder
 * onLogout: callback para cerrar sesión
 */
@Composable
fun Admin(currentUser: Usuario?, onLogout: () -> Unit) {
    // Si no hay usuario o no es admin, mostramos mensaje
    if (currentUser == null || !currentUser.admin) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            Text("Acceso denegado. Se requiere permisos de administrador.", color = Color.Red)
            // Botón para volver al login
            Button(onClick = onLogout, modifier = Modifier.padding(top = 12.dp)) {
                Text("Ir a Login")
            }
        }
        return
    }

    // Campos para crear/editar entrada (no persistente en lista global por simplicidad)
    var nombre by remember { mutableStateOf("") }
    var tipo by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        Text(text = "Panel de administración", fontWeight = FontWeight.Bold)
        Text(text = "Usuario: ${currentUser.usuario}", modifier = Modifier.padding(top = 8.dp))

        TextField(value = nombre, onValueChange = { nombre = it }, label = { Text("Nombre del Pokémon") }, modifier = Modifier.fillMaxWidth().padding(top = 12.dp))
        TextField(value = tipo, onValueChange = { tipo = it }, label = { Text("Tipo (texto)") }, modifier = Modifier.fillMaxWidth().padding(top = 8.dp))
        TextField(value = descripcion, onValueChange = { descripcion = it }, label = { Text("Descripción") }, modifier = Modifier.fillMaxWidth().padding(top = 8.dp))
        Row(modifier = Modifier.padding(top = 12.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = {
                // Acción de "guardar" — aquí no se modifica lista global, sólo mostramos confirmación
                if (nombre.isNotBlank()) {
                    showDialog = true
                    nombre = ""
                    tipo = ""
                    descripcion = ""
                }
            }) {
                Text("Guardar entrada")
            }
        }

        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text("Entrada creada") },
                text = { Text("Se ha creado la entrada (no persistente en esta demo).") },
                confirmButton = {
                    Button(onClick = { showDialog = false }) {
                        Text("OK")
                    }
                }
            )
        }
    }
}


/**
 * Preview de la pagina principal
 */
@Preview
@Composable
fun PokedexPreview() {
    Pokedex()
}
