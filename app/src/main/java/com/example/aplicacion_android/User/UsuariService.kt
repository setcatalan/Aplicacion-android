package com.example.aplicacion_android.User

import com.example.aplicacion_android.Login.UsuariRequest
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Body
import retrofit2.http.POST

interface UsuariService {

    @GET("get/all")
    suspend fun llistaUsuaris(): Response<List<Usuari>>

    @GET("get/{id}")
    suspend fun usuari(@Path("id") id: Int): Response<Usuari>

    @DELETE("delete/{id}")
    suspend fun borraUsuari(@Path("id") id: Int): Response<Void>
  
    @POST("create/usuari")
    suspend fun registrarUsuari (
        @Body nouUsuari: UsuariRequest
    ): Response<Usuari>
}