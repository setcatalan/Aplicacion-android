package com.example.aplicacion_android

import com.example.aplicacion_android.Forum.Comentari
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry

object DataSource {
    val comentaris: List<Comentari> = listOf(
        Comentari("Usuari1", "Expedition 33 GOTY", "Expedition 33"),
        Comentari("Usuari2", "Esperando GTA VI", "GTA V"),
        Comentari("Usuari3", "Teneis que jugar Outer Wilds", "Outer Wilds"),
        Comentari("Usuari4", "Quien esta listo para el Evento?", "Fortnite"),
        Comentari("Usuari5", "Me salió Qiqi otra vez 😭", "Genshin Impact")
    )

    val entries = listOf(
        BarEntry(1f, 10f),
        BarEntry(2f, 15f),
        BarEntry(3f, 12f),
        BarEntry(4f, 20f)
    )
}