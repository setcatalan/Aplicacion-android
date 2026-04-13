package com.example.aplicacion_android.User

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {

    private val _usuari = MutableLiveData<Usuari>()
    val usuari: LiveData<Usuari> = _usuari

    fun carregaUsuari(id: Int){
        viewModelScope.launch {
            try {
                val response = UsuariAPI.API().usuari(id)

                if (response.isSuccessful){
                    _usuari.value = response.body()
                } else {
                    Log.e("API", "Error HTTP: ${response.code()}")
                }
            } catch (e: Exception){
                Log.e("API", "Error de connexió: " + e.message)
            }
        }
    }
}