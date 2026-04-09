package com.example.aplicacion_android.User

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

    fun eliminaUsuari(id: Int){
        viewModelScope.launch {
            try {
                val response = UsuariAPI.API().borraUsuari(id)

                if (response.isSuccessful) {
                    _usuaris.value = _usuaris.value?.filter { it.id != id }
                } else {
                    Log.e("API", "Error HTTP: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("API", "Error de connexió: $e")
            }
        }

    }
}