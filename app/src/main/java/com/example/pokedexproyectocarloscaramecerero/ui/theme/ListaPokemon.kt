package com.example.pokedexproyectocarloscaramecerero.ui.theme

import com.example.pokedexproyectocarloscaramecerero.R
import com.example.pokedexproyectocarloscaramecerero.Pokemon
import com.example.pokedexproyectocarloscaramecerero.TIPO

/**
 * Lista de los pokemon usando la dataclass
 */
val listaPokemon = listOf<Pokemon>(
    Pokemon(
        "Bulbasaur", TIPO.PLANTA, TIPO.VENENO,
        habilidades = listOf(
            "Espesura",
            "Clorofila"
        ),
        descripcion = "Este Pokémon nace con una semilla en el lomo, que brota con el paso del tiempo.",
        imagen = R.drawable.bulbasaur,
    ),
    Pokemon(
        "Cyndaquil", TIPO.FUEGO, null,
        habilidades = listOf(
            "Mar Llamas",
            "Absorbe Fuego"
        ),
        descripcion = "Es tímido y siempre se enrosca como una pelota. Si se le asusta, el fuego de su lomo arde con más fuerza.",
        imagen = R.drawable.cyndaquil
    ),
    Pokemon(
        "Popplio", TIPO.AGUA, null,
        habilidades = listOf(
            "Torrente",
            "Voz Fluida"
        ),
        descripcion = "Este Pokémon puede crear globos de agua por la nariz y usarlos en combate. Es famoso por su carácter diligente.",
        imagen = R.drawable.popplio
    ),
    Pokemon(
        "Gardevoir", TIPO.PSIQUICO, TIPO.HADA,
        habilidades = listOf(
            "Sincronía",
            "Rastro",
            "Telepatía"
        ),
        descripcion = "Para proteger a su Entrenador, es capaz de crear un pequeño agujero negro. Su poder psíquico alcanza la máxima expresión cuando defiende a un Entrenador en el que confía.",
        imagen = R.drawable.gardevoir
    ),
    Pokemon(
        "Lopunny", TIPO.NORMAL, null,
        habilidades = listOf(
            "Gran Encanto",
            "Zoquete",
            "Flexibilidad"
        ),
        descripcion = "Es muy precavido y rara vez se deja ver. Si siente peligro, huye dando grandes saltos con sus patas de tremenda flexibilidad.",
        imagen = R.drawable.lopunny
    ),
    Pokemon(
        "Tinkaton", TIPO.HADA, TIPO.ACERO,
        habilidades = listOf(
            "Rompemoldes",
            "Ritmo Propio",
            "Hurtadillas"
        ),
        descripcion = "Blande un martillo de 100 kg como si tal cosa. Lo usa para conseguir comida golpeando rocas para dejar inconscientes a los Pokémon que anidan en ellas.",
        imagen = R.drawable.tinkaton
    ),
    Pokemon(
        "Goodra", TIPO.DRAGON, null,
        habilidades = listOf(
            "Herbívoro",
            "Hidratación",
            "Baba"
        ),
        descripcion = "Este Pokémon de carácter amigable se entristece si se queda solo, y al llorar, mancha a su Entrenador con un fluido pegajoso.",
        imagen = R.drawable.goodra
    ),
    Pokemon("Arceus", TIPO.MULTITIPO, null,
        habilidades = listOf(
            "Multitipo"
        ),
        descripcion = "Según las leyendas de Sinnoh, este Pokémon surgió de un huevo y creó el mundo.",
        imagen = R.drawable.arceus),
    Pokemon(
        "Chien Pao", TIPO.SINIESTRO, TIPO.HIELO,
        habilidades = listOf(
            "Espada Debacle"
        ),
        descripcion = "La animosidad de quienes perecieron por la espada en el pasado se ha revestido de nieve y ha dado lugar a este Pokémon.",
        imagen = R.drawable.chienpao),
    Pokemon(
        "Giratina", TIPO.FANTASMA, TIPO.DRAGON,
        habilidades = listOf(
            "Presión",
            "Telepatía"
        ),
        descripcion = "Fue desterrado por su violencia. Observa el mundo en silencio desde el Mundo Distorsión.",
        imagen = R.drawable.giratina),
    Pokemon("Meowscarada", TIPO.PLANTA, TIPO.SINIESTRO, habilidades = listOf("Espesura", "Mutatipo"), descripcion = "Con el revés de su capa, hace que el polen de sus flores se adhiera a sus rivales y estalle. El tallo de la flor cambia de trayectoria para guiar la explosión.", imagen = R.drawable.meowscarada),
    Pokemon("Vaporeon", TIPO.AGUA, null, habilidades = listOf("Absorbe Agua", "Hidratación"), descripcion = "Sufrió una mutación y desarrolló aletas y branquias que le permiten vivir bajo el agua.", imagen = R.drawable.vaporeon),
    Pokemon("Garchomp", TIPO.DRAGON, TIPO.TIERRA, habilidades = listOf("Velo Arena", "Piel Tosca"), descripcion = "Cuando repliega el cuerpo y estira las alas, parece un avión a reacción. Vuela a velocidad sónica.", imagen = R.drawable.garchomp),
    Pokemon("Lucario", TIPO.LUCHA, TIPO.ACERO, habilidades = listOf("Impasible", "Foco Interno", "Justiciero"), descripcion = "Puede leer los pensamientos y movimientos de su adversario a través de su aura.", imagen = R.drawable.lucario),
    Pokemon("Lurantis", TIPO.PLANTA, null,
        habilidades = listOf(
            "Defensa Hoja",
            "Respondón"
        ),
        descripcion = "Exige un gran esfuerzo mantener su elegante apariencia. Quien lo descuide verá frustrado su intento de ganarse su confianza.",
        imagen = R.drawable.lurantis),
    Pokemon("Miraidon", TIPO.ELECTRICO, TIPO.DRAGON,
        habilidades = listOf(
            "Motor Hadrónico"
        ),
        descripcion = "Se sabe muy poco acerca de este Pokémon. Se dice que es capaz de reducir a cenizas cualquier terreno con sus rayos.",
        imagen = R.drawable.miraidon),
    Pokemon("Carbink", TIPO.ROCA, TIPO.HADA,
        habilidades = listOf(
            "Cuerpo Puro",
            "Robustez"
        ),
        descripcion = "Nace en las profundidades terrestres, donde la presión y la temperatura son extremas. Lleva millones de años sumido en un letargo.",
        imagen = R.drawable.carbink),
    Pokemon("Pheromosa", TIPO.LUCHA, TIPO.BICHO,
        habilidades = listOf(
            "Ultraimpulso"
        ),
        descripcion = "Uno de los temibles Ultraentes. Quienes se enfrentan a él, sin importar el género, quedan prendados de su belleza y pierden las ganas de combatir.",
        imagen = R.drawable.pheromosa
    ),
    Pokemon("Rayquaza", TIPO.DRAGON, TIPO.VOLADOR,
        habilidades = listOf(
            "Bucle Aire"
        ),
        descripcion = "Vive en la capa de ozono. Se dice que si Kyogre y Groudon fueran a luchar, bajaría de los cielos.",
        imagen = R.drawable.rayquaza
    ),
    Pokemon("Mawile", TIPO.ACERO, TIPO.HADA,
        habilidades = listOf(
            "Hipercorte",
            "Intimidación",
            "Potencia Bruta"
        ),
        descripcion = "Engaña a sus rivales con su aspecto dócil. Después, se gira y les propina un mordisco con sus enormes fauces de acero.",
        imagen = R.drawable.mawile
    ),
    Pokemon("Sylveon", TIPO.HADA, null,
        habilidades = listOf(
            "Gran Encanto",
            "Piel Feérica"
        ),
        descripcion = "Lanza desde sus apéndices sensoriales con forma de cinta unas ondas tranquilizadoras para detener los combates.",
        imagen = R.drawable.sylveon
    ),
    Pokemon("Glaceon", TIPO.HIELO, null,
        habilidades = listOf(
            "Manto Níveo",
            "Gélido"
        ),
        descripcion = "Es capaz de congelar su pelaje para erizarlo y usarlo a modo de aguzadas púas. Puede controlar a voluntad su temperatura corporal.",
        imagen = R.drawable.glaceon
    ),
    Pokemon("Eevee", TIPO.NORMAL, null,
        habilidades = listOf(
            "Fuga",
            "Adaptable",
            "Anticipación"
        ),
        descripcion = "Su irregular estructura genética encierra el secreto de su capacidad para adoptar una gran variedad de formas.",
        imagen = R.drawable.eevee
    ),
    Pokemon("Spheal", TIPO.HIELO, TIPO.AGUA,
        habilidades = listOf(
            "Sebo",
            "Gélido",
            "Despiste"
        ),
        descripcion = "Está recubierto de un grueso pelaje. Al cazar, es incapaz de sumergirse, por lo que se limita a buscar comida que esté flotando en el agua.",
        imagen = R.drawable.spheal
    ),
    Pokemon("Clodsire", TIPO.VENENO, TIPO.TIERRA,
        habilidades = listOf(
            "Punto Tóxico",
            "Absorbe Agua",
            "Ignorante"
        ),
        descripcion = "Cuando lo atacan, de su cuerpo brotan gruesas y afiladas púas. Es una defensa muy peligrosa, pues puede envenenar al contacto.",
        imagen = R.drawable.clodsire
    ),
    Pokemon("Delphox", TIPO.FUEGO, TIPO.PSIQUICO,
        habilidades = listOf(
            "Mar Llamas",
            "Prestidigitador"
        ),
        descripcion = "Valiéndose de sus poderes psíquicos, genera un vórtice de fuego a 3000 °C que envuelve y calcina a sus enemigos.",
        imagen = R.drawable.delphox
    ),
    Pokemon("Emboar", TIPO.FUEGO, TIPO.LUCHA,
        habilidades = listOf(
            "Mar Llamas",
            "Audaz"
        ),
        descripcion = "Prende fuego a sus puños con las llamas de su mentón para propinar puñetazos ardientes. Es muy rápido pese a su corpulencia.",
        imagen = R.drawable.emboar
    ),
    Pokemon("Toxtricity", TIPO.ELECTRICO, TIPO.VENENO,
        habilidades = listOf(
            "Punk Rock",
            "Más", "Menos",
            "Tecnicismo"
        ),
        descripcion = "Al rasgar las seis cuerdas que tiene en el pecho, genera una descarga eléctrica de 15 000 voltios, lo que pone en serio peligro a sus oponentes.",
        imagen = R.drawable.toxtricity
    ),
    Pokemon("Ceruledge", TIPO.FUEGO, TIPO.FANTASMA,
        habilidades = listOf("Absorbe Fuego", "Cuerpo Llama"),
        descripcion = "Las espadas que blande están imbuidas del rencor de un espadachín que pereció antes de culminar su propósito. Al recibir un tajo, la herida libera energía vital.",
        imagen = R.drawable.ceruledge),
    Pokemon("Lilligant", TIPO.PLANTA, null,
        habilidades = listOf(
            "Clorofila",
            "Ritmo Propio",
            "Defensa Hoja"
        ),
        descripcion = "La flor que le crece en la cabeza desprende un aroma relajante, pero cuidarla es muy complicado. Se marchita si se descuida su mantenimiento.",
        imagen = R.drawable.lilligant
    ),
    Pokemon("Zoroark", TIPO.SINIESTRO, null,
        habilidades = listOf(
            "Ilusión"
        ),
        descripcion = "Protege su guarida creando un paisaje ilusorio. Es muy protector con sus allegados, sobre todo con Zorua.",
        imagen = R.drawable.zoroark
    ),
    Pokemon("Ribombee", TIPO.BICHO, TIPO.HADA,
        habilidades = listOf(
            "Polvo Escudo",
            "Velo Dulce",
            "Busca Néctar"
        ),
        descripcion = "Adopta su forma variocolor cuando está expuesto a la lluvia. Se dice que es la viva encarnación de un arcoíris.",
        imagen = R.drawable.ribombee
    ),
    Pokemon("Indeedee", TIPO.PSIQUICO, TIPO.NORMAL,
        habilidades = listOf(
            "Foco Interno",
            "Sincronía",
            "Psicosimbiosis"
        ),
        descripcion = "Son Pokémon muy inteligentes y serviciales con los humanos y otros Pokémon. Los machos y las hembras tienen diferente apariencia y carácter.",
        imagen = R.drawable.indeedee
    )
)
