package com.example.aplicacion_android

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface UsuariService {

    @GET("get/all")
    suspend fun llistaUsuaris(): Response<List<Usuari>>
}