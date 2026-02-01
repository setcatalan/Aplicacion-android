package com.example.aplicacion_android

object DataSource {
    val comentaris: List<Comentari> = listOf(
        Comentari("Usuari1", "Expedition 33 GOTY", "Expedition 33"),
        Comentari("Usuari2", "Esperando GTA VI", "GTA V"),
        Comentari("Usuari3", "Teneis que jugar Outer Wilds", "Outer Wilds"),
        Comentari("Usuari4", "Quien esta listo para el Evento?", "Fortnite"),
        Comentari("Usuari5", "Me salió Qiqi otra vez 😭", "Genshin Impact")
    )

    val usuaris: List<Usuari> = listOf(
        Usuari("Usuari1", "hola12"),
        Usuari("Usuari2", "12hola"),
        Usuari("Usuari3", "1hola2")
    )
}