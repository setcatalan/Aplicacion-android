package com.example.aplicacion_android

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class RegisterActivity : AppCompatActivity() {

    private lateinit var btn_close_edit: ImageButton
    private lateinit var btnRegister: Button

    private lateinit var etUser: EditText
    private lateinit var etPass: EditText
    private lateinit var etPasswordRep: EditText
    private lateinit var etEmail: EditText

    private val viewModel: RegisterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initComponents()
        initListeners()
        initObservers()
    }

    private fun initComponents() {
        btn_close_edit = findViewById<ImageButton>(R.id.btn_close_edit)
        btnRegister = findViewById<Button>(R.id.btnRegister)
        etUser = findViewById<EditText>(R.id.etUser)
        etPass = findViewById<EditText>(R.id.etPassword)
        etPasswordRep = findViewById<EditText>(R.id.etPasswordRep)
        etEmail = findViewById<EditText>(R.id.etEmail)

        btnRegister.isEnabled = false
    }

    private fun initListeners() {
        btn_close_edit.setOnClickListener {
            finish()
        }

        //enviar canvis al model
        etUser.doAfterTextChanged { viewModel.onUserChanged(it) }
        etPass.doAfterTextChanged { viewModel.onPasswordChanged(it) }
        etPasswordRep.doAfterTextChanged { viewModel.onPasswordRepChanged(it) }
        etEmail.doAfterTextChanged { viewModel.onEmailChanged(it) }

        btnRegister.setOnClickListener {
            val intent = Intent(this, UserActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun initObservers() {
        viewModel.formState.observe(this) { state ->
            etUser.error = state.userError
            etPass.error = state.passwordError
            etPasswordRep.error = state.passwordRepError
            etEmail.error = state.emailError

            btnRegister.isEnabled = state.isDataValid
        }
    }
}