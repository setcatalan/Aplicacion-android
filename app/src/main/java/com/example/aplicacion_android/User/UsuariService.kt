package com.example.aplicacion_android.User

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface UsuariService {

    @GET("get/all")
    suspend fun llistaUsuaris(): Response<List<Usuari>>

    @GET("get/{id}")
    suspend fun usuari(@Path("id") id: Int): Response<Usuari>
}