package com.example.aplicacion_android

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class UsersViewModel : ViewModel() {

    private val _usuaris = MutableLiveData<List<Usuari>>()
    val usuaris: LiveData<List<Usuari>> = _usuaris

    fun carregarUsuaris(){
        viewModelScope.launch {
            try {
                val response = UsuariAPI.API().llistaUsuaris()

                if (response.isSuccessful) {
                    _usuaris.value = response.body() ?: emptyList()
                } else {
                    Log.e("API", "Error HTTP: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("API", "Error de connexió")
            }
        }
    }
}