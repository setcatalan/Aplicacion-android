package com.example.aplicacion_android.Login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aplicacion_android.User.Usuari
import com.example.aplicacion_android.User.UsuariAPI
import kotlinx.coroutines.launch

// Classe ViewModel per al Login
class LoginViewModel: ViewModel() {
    private val _nomUsuari = MutableLiveData<String>()
    private val _contrasenya = MutableLiveData<String>()
    val nomUsuari: LiveData<String> = _nomUsuari
    val contrasenya: LiveData<String> = _contrasenya

    private lateinit var llistaUsuaris: List<Usuari>

    fun canviNom(nom: String){
        _nomUsuari.value = nom
    }

    fun canviContra(contra: String){
        _contrasenya.value = contra
    }

    fun carregarUsuaris(){
        viewModelScope.launch {
            try {
                val response = UsuariAPI.API().llistaUsuaris()

                if (response.isSuccessful) {
                    llistaUsuaris = response.body() ?: emptyList()
                } else {
                    Log.e("API", "Error HTTP: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("API", "Error de connexió")
            }
        }
    }

    fun comprovacioUsuari(): Boolean{
        for (usuari in llistaUsuaris){
            if ((usuari.nom == _nomUsuari.value) and (usuari.contra == _contrasenya.value)){
                return true
            }
        }
        return false
    }

    fun getIdUsari(): Int{
        for (usuari in llistaUsuaris){
            if ((usuari.nom == _nomUsuari.value) and (usuari.contra == _contrasenya.value)){
                return usuari.id
            }
        }
        return -1
    }
}