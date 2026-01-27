package com.example.aplicacion_android

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
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
}

class LoginActivity : AppCompatActivity() {

    private lateinit var btnCloseEdit: ImageView
    private lateinit var btnRegister: Button
    private lateinit var btnConfirmarLogin: Button
    private lateinit var etUser: EditText
    private lateinit var etPass: EditText

    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Utilitzant el observe del ViewModel estableix el text del EditText en el valor del LiveData
        loginViewModel.nomUsuari.observe(this){ nomUsuari ->
            etUser.hint = nomUsuari
        }
        loginViewModel.contrasenya.observe(this){ contrasenya ->
            etPass.hint = contrasenya
        }

        initComponents()
        initListeners()
    }

    private fun initComponents() {
        btnCloseEdit = findViewById(R.id.btn_close_edit)
        btnRegister = findViewById(R.id.btnRegister)
        btnConfirmarLogin = findViewById(R.id.btnConfirmarLogin)
        etUser = findViewById(R.id.etUser)
        etPass = findViewById(R.id.etPass)
    }

    private fun initListeners() {
        btnCloseEdit.setOnClickListener {
            finish()
        }

        btnRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        btnConfirmarLogin.setOnClickListener {
            val intent = Intent(this, UserActivity::class.java)
            startActivity(intent)
            finish()
        }

        // Quan el text del EditText canvia, també canvia el contingut del LiveData del ViewModel
        etUser.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                loginViewModel.canviNom(etUser.text.toString())
            }
        })

        etPass.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                loginViewModel.canviContra(etPass.text.toString())
            }
        })
    }
}