package com.example.aplicacion_android.Main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aplicacion_android.Login.UsuariLogin
import com.example.aplicacion_android.User.Usuari
import com.example.aplicacion_android.User.UsuariAPI
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.PieEntry
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class GraficsViewModel : ViewModel() {

    private val _llistaUsuaris = MutableStateFlow<List<Usuari>>(emptyList())
    val llistaUsuaris: StateFlow<List<Usuari>> = _llistaUsuaris

    private val _llistaLogins = MutableStateFlow<List<UsuariLogin>>(emptyList())
    val llistaLogins: StateFlow<List<UsuariLogin>> = _llistaLogins

    init {
        carregarUsuaris()
        carregarLogins()
    }

    fun carregarUsuaris() {
        viewModelScope.launch {
            try {
                val response = UsuariAPI.API().llistaUsuaris()
                if (response.isSuccessful) {
                    _llistaUsuaris.value = response.body() ?: emptyList()
                } else {
                    Log.e("API", "Error HTTP: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("API", "Error de connexió: $e")
            }
        }
    }

    fun carregarLogins() {
        val db = FirebaseFirestore.getInstance()
        db.collection("usuaris")
            .get()
            .addOnSuccessListener { result ->
                _llistaLogins.value = result.toObjects(UsuariLogin::class.java)
            }
            .addOnFailureListener { e ->
                Log.e("Firebase", "Error al carregar logins: $e")
            }
    }

    fun graficGenere(usuaris: List<Usuari>): List<BarEntry> {
        var masculi = 0f
        var femeni = 0f
        var noEspecificat = 0f

        for (usuari in usuaris) {
            when (usuari.sexe.name) {
                "masculí" -> masculi++
                "femení"  -> femeni++
                else      -> noEspecificat++
            }
        }

        return listOf(
            BarEntry(1f, masculi),
            BarEntry(2f, femeni),
            BarEntry(3f, noEspecificat)
        )
    }

    fun graficEdat(usuaris: List<Usuari>): List<BarEntry> {
        var joves = 0f      // 0-17
        var adults = 0f     // 18-35
        var madurs = 0f     // 36-60
        var grans = 0f      // 61+

        for (usuari in usuaris) {
            when {
                usuari.edat <= 17 -> joves++
                usuari.edat <= 35 -> adults++
                usuari.edat <= 60 -> madurs++
                else              -> grans++
            }
        }

        return listOf(
            BarEntry(1f, joves),
            BarEntry(2f, adults),
            BarEntry(3f, madurs),
            BarEntry(4f, grans)
        )
    }

    fun graficLogins(logins: List<UsuariLogin>): List<PieEntry> {
        return logins
            .filter { it.numLogins > 0 }
            .filter { it.nom != "usuariProbaDB" }  // ✅ excluye el usuario de prueba
            .map { PieEntry(it.numLogins.toFloat(), it.nom) }
    }
}