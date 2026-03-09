package com.example.aplicacion_android.User

import retrofit2.Response
import retrofit2.http.GET

interface UsuariService {

    @GET("get/all")
    suspend fun llistaUsuaris(): Response<List<Usuari>>
}