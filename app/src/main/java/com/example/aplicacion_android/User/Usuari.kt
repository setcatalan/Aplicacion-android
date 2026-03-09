package com.example.aplicacion_android.User

import java.sql.Timestamp

data class Usuari(
    val id: Int,
    val nom: String,
    val desc: String,
    val edat: Int,
    val sexe: Sexe,
    val contra: String,
    val imatge: String,
    val dataCreated: Timestamp,
    val dataUpdated: Timestamp
)