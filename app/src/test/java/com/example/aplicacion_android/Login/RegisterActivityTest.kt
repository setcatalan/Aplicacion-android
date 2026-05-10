package com.example.aplicacion_android.Login

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.withHint
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.aplicacion_android.R
import org.hamcrest.CoreMatchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class RegisterActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(RegisterActivity::class.java)

    @Test
    fun camposRegistro_seMuestranCorrectamente() {
        onView(withId(R.id.tituloLogin))
            .check(matches(isDisplayed()))

        onView(withId(R.id.etUser))
            .check(matches(isDisplayed()))
            .check(matches(withHint(R.string.nombreUsuarioReg)))

        onView(withId(R.id.etPassword))
            .check(matches(isDisplayed()))
            .check(matches(withHint(R.string.passUsuarioReg)))

        onView(withId(R.id.etPasswordRep))
            .check(matches(isDisplayed()))
            .check(matches(withHint(R.string.repetirPass)))

        onView(withId(R.id.etEmail))
            .check(matches(isDisplayed()))
            .check(matches(withHint(R.string.emailReg)))

        onView(withId(R.id.btnRegister))
            .check(matches(isDisplayed()))
            .check(matches(withText(R.string.register)))

        onView(withId(R.id.btn_close_edit))
            .check(matches(isDisplayed()))
    }

    @Test
    fun botonRegistro_empiezaDesactivado() {
        onView(withId(R.id.btnRegister))
            .check(matches(not(isEnabled())))
    }

    @Test
    fun botonRegistro_seActiva_conDatosValidos() {
        onView(withId(R.id.etUser))
            .perform(typeText("Usuario"), closeSoftKeyboard())

        onView(withId(R.id.etPassword))
            .perform(typeText("Hola123"), closeSoftKeyboard())

        onView(withId(R.id.etPasswordRep))
            .perform(typeText("Hola123"), closeSoftKeyboard())

        onView(withId(R.id.etEmail))
            .perform(typeText("usuario@gmail.com"), closeSoftKeyboard())

        onView(withId(R.id.btnRegister))
            .check(matches(isEnabled()))
    }

    @Test
    fun botonRegistro_sigueDesactivado_conEmailIncorrecto() {
        onView(withId(R.id.etUser))
            .perform(typeText("Usuario"), closeSoftKeyboard())

        onView(withId(R.id.etPassword))
            .perform(typeText("Hola123"), closeSoftKeyboard())

        onView(withId(R.id.etPasswordRep))
            .perform(typeText("Hola123"), closeSoftKeyboard())

        onView(withId(R.id.etEmail))
            .perform(typeText("emailInvalido"), closeSoftKeyboard())

        onView(withId(R.id.btnRegister))
            .check(matches(not(isEnabled())))
    }

    @Test
    fun botonRegistro_sigueDesactivado_conContrasenasDistintas() {
        onView(withId(R.id.etUser))
            .perform(typeText("Usuario"), closeSoftKeyboard())

        onView(withId(R.id.etPassword))
            .perform(typeText("Hola123"), closeSoftKeyboard())

        onView(withId(R.id.etPasswordRep))
            .perform(typeText("123Hola"), closeSoftKeyboard())

        onView(withId(R.id.etEmail))
            .perform(typeText("usuario@gmail.com"), closeSoftKeyboard())

        onView(withId(R.id.btnRegister))
            .check(matches(not(isEnabled())))
    }
}