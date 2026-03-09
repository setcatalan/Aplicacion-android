package com.example.aplicacion_android.User

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UsuariAPI {

    companion object{
        private var usuariAPI: UsuariService? = null

        @Synchronized
        fun API(): UsuariService{
            if (usuariAPI == null){

                val gsondateformat = GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                    .create()

                usuariAPI = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create(gsondateformat))
                    .baseUrl("http://129.158.219.17:8080/api/usuaris/")
                    .build()
                    .create(UsuariService::class.java)
            }
            return usuariAPI!!
        }
    }
}