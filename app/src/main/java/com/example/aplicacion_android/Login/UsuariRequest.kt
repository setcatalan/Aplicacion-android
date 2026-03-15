package com.example.aplicacion_android.Login

import com.example.aplicacion_android.User.Sexe

data class UsuariRequest (
    val nom: String,
    val edat: Int,
    val sexe: Sexe,
    val contra: String
)