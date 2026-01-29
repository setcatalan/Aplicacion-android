package com.example.aplicacion_android
/*Clase de datos que representa el estado del formulario de registro
*
* coniene posibles errores de los campos y si és válido o no*/
data class RegisterFormState(
    val userError: String? = null,
    val passwordError: String? = null,
    val passwordRepError: String? = null,
    val emailError: String? = null,
    val isDataValid: Boolean = false
)
