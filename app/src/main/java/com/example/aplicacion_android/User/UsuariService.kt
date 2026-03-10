package com.example.aplicacion_android.User

import com.example.aplicacion_android.Login.UsuariRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UsuariService {

    @GET("get/all")
    suspend fun llistaUsuaris(): Response<List<Usuari>>

    @POST("usuaris/")
    suspend fun registrarUsuari (
        @Body nouUsuari: UsuariRequest
    ): Response<Usuari>
}