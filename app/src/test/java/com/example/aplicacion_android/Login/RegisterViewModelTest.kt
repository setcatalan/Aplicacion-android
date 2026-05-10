package com.example.aplicacion_android.Login

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Before
import org.junit.Rule
import kotlin.test.Test
import org.junit.Assert.*

class RegisterViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: RegisterViewModel

    @Before
    fun setUp() {
        viewModel = RegisterViewModel()
    }

    @Test
    fun formularioInicial_noEsValido() {
        assertFalse(viewModel.formState.value?.isDataValid ?: true)
    }

    @Test
    fun usuarioVacio_muestraError() {
        viewModel.onUserChanged("")
        viewModel.onPasswordChanged("abc123")
        viewModel.onPasswordRepChanged("abc123")
        viewModel.onEmailChanged("test@test.com")

        assertEquals("Usuario obligatorio", viewModel.formState.value?.userError)
        assertFalse(viewModel.formState.value?.isDataValid ?: true)
    }

    @Test
    fun usuarioCorto_muestraError() {
        viewModel.onUserChanged("ab")
        viewModel.onPasswordChanged("abc123")
        viewModel.onPasswordRepChanged("abc123")
        viewModel.onEmailChanged("test@test.com")

        assertEquals("Mínimo 3 caracteres", viewModel.formState.value?.userError)
    }

    @Test
    fun passwordCorta_muestraError() {
        viewModel.onUserChanged("cristina")
        viewModel.onPasswordChanged("abc1")
        viewModel.onPasswordRepChanged("abc1")
        viewModel.onEmailChanged("test@test.com")

        assertEquals("Mínimo 6 caracteres", viewModel.formState.value?.passwordError)
    }

    @Test
    fun passwordSinNumeros_muestraError() {
        viewModel.onUserChanged("cristina")
        viewModel.onPasswordChanged("abcdef")
        viewModel.onPasswordRepChanged("abcdef")
        viewModel.onEmailChanged("test@test.com")

        assertEquals("Debe tener letras y números", viewModel.formState.value?.passwordError)
    }

    @Test
    fun passwordRepetidaVacia_muestraError() {
        viewModel.onUserChanged("cristina")
        viewModel.onPasswordChanged("abc123")
        viewModel.onPasswordRepChanged("")
        viewModel.onEmailChanged("test@test.com")

        assertEquals("Repite la contraseña", viewModel.formState.value?.passwordRepError)
    }

    @Test
    fun passwordsDistintas_muestraError() {
        viewModel.onUserChanged("cristina")
        viewModel.onPasswordChanged("abc123")
        viewModel.onPasswordRepChanged("abc124")
        viewModel.onEmailChanged("test@test.com")

        assertEquals("No coincide", viewModel.formState.value?.passwordRepError)
    }

    @Test
    fun emailVacio_muestraError() {
        viewModel.onUserChanged("cristina")
        viewModel.onPasswordChanged("abc123")
        viewModel.onPasswordRepChanged("abc123")
        viewModel.onEmailChanged("")

        assertEquals("Email obligatorio", viewModel.formState.value?.emailError)
    }

    @Test
    fun emailIncorrecto_muestraError() {
        viewModel.onUserChanged("cristina")
        viewModel.onPasswordChanged("abc123")
        viewModel.onPasswordRepChanged("abc123")
        viewModel.onEmailChanged("correoIncorrecto")

        assertEquals("Formato email no válido", viewModel.formState.value?.emailError)
    }

    @Test
    fun datosValidos_formularioEsValido() {
        viewModel.onUserChanged("cristina")
        viewModel.onPasswordChanged("abc123")
        viewModel.onPasswordRepChanged("abc123")
        viewModel.onEmailChanged("test@test.com")

        val state = viewModel.formState.value

        assertNull(state?.userError)
        assertNull(state?.passwordError)
        assertNull(state?.passwordRepError)
        assertNull(state?.emailError)
        assertTrue(state?.isDataValid ?: false)
    }
}