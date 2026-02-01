package com.example.aplicacion_android

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

// Classe ViewModel per al Login
class LoginViewModel: ViewModel() {
    private val _nomUsuari = MutableLiveData<String>()
    private val _contrasenya = MutableLiveData<String>()
    val nomUsuari: LiveData<String> = _nomUsuari
    val contrasenya: LiveData<String> = _contrasenya

    fun canviNom(nom: String){
        _nomUsuari.value = nom
    }

    fun canviContra(contra: String){
        _contrasenya.value = contra
    }

    fun comprovacioUsuari(): Boolean{
        for (usuari in DataSource.usuaris){
            if ((usuari.nomUsuari == _nomUsuari.value) and (usuari.contrasenya == _contrasenya.value)){
                return true
            }
        }
        return false
    }
}