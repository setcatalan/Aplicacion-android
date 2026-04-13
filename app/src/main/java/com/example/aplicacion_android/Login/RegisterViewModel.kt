package com.example.aplicacion_android.Login

import android.util.Log
import android.util.Log.e
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aplicacion_android.User.Sexe
import com.example.aplicacion_android.User.UsuariAPI
import kotlinx.coroutines.launch

/*Esta clase se encarga de gestionar la lógica de negocio del formulario
* de registro y de mantener el estado de la UI de forma independiente
* a la Activity
*
* El ViewModel, hace que no dependamos directamente de la interficie gráfica
* a la hora de los datos y validaciones*/
class RegisterViewModel : ViewModel() {
    //MutableLiveData contiene el estado del formulario, solo el ViewModel puede modificar
    private val _formState = MutableLiveData(RegisterFormState())


    //LiveData publico y inmutable, expone el estado del formulario a la Activity
    val formState: LiveData<RegisterFormState> = _formState

    private val _registerOk = MutableLiveData<Boolean>()
    val registerOk: LiveData<Boolean> = _registerOk

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    private var user: String = ""
    private var pass: String = ""
    private var passRep: String = ""
    private var email: String = ""

    //funciones llamadas desde la Activity cad vez q modifica el usuario algo, se actualiza y se vuelve a validar
    fun onUserChanged(value: String) {
        user = value
        validate()
    }

    fun onPasswordChanged(value: String) {
        pass = value
        validate()
    }

    fun onPasswordRepChanged(value: String) {
        passRep = value
        validate()
    }

    fun onEmailChanged(value: String) {
        email = value
        validate()
    }

    //funcion de validacion, comprueba campos y crea un nuevo estado que sera observado por la UI
    private fun validate() {
        val userError = validateUser(user)
        val passError = validatePassword(pass)
        val passRepError = validateRepeatPassword(pass,passRep)
        val emailError = validateEmail(email)

        val valid = userError == null &&
                passError == null &&
                passRepError == null &&
                emailError == null

        _formState.value = RegisterFormState(
            userError = userError,
            passwordError = passError,
            passwordRepError = passRepError,
            emailError = emailError,
            isDataValid = valid
        )
    }

    fun registerUser() {
        validate()
        if (_formState.value?.isDataValid != true) {
            _registerOk.value = false
            _errorMessage.value = "Formulario no válido"
            return
        }

        val nouUsuari = UsuariRequest(
            nom = user,
            edat = -1,
            sexe = Sexe.noEspecificat,
            contra = pass,
        )

        viewModelScope.launch {
            try {
                val response = UsuariAPI.API().registrarUsuari(nouUsuari)

                if (response.isSuccessful) {
                    _registerOk.value = true
                    _errorMessage.value = ""
                } else {
                    _registerOk.value = false
                    _errorMessage.value = "Error HTTP: ${response.code()}"
                    Log.e("API", "Error HTTP: ${response.code()}")
                }
            } catch (e: Exception) {
                _registerOk.value = false
                _errorMessage.value = "Error de conexión"
                Log.e("API", "Error de conexión", e)
            }
        }
    }

    //estas funciones validan cada campo que hay
    private fun validateUser(u: String): String? {
        if (u.isBlank()) return "Usuario obligatorio"
        if (u.length < 3) return "Mínimo 3 caracteres"
        return null
    }

    private fun validatePassword(p: String): String? {
        if (p.isBlank()) return "Contraseña obligatoria"
        if (p.length < 6) return "Mínimo 6 caracteres"
        val hasLetter = p.any { it.isLetter() }
        val hasDigit = p.any { it.isDigit() }
        if (!hasLetter || !hasDigit) return "Debe tener letras y números"
        return null
    }

    private fun validateRepeatPassword(p: String, r: String): String? {
        if (r.isBlank()) return "Repite la contraseña"
        if (p != r) return "No coincide"
        return null
    }

    private fun validateEmail(e: String): String? {
        if (e.isBlank()) return "Email obligatorio"
        if (!Patterns.EMAIL_ADDRESS.matcher(e).matches()) return "Formato email no válido"
        return null
    }
}