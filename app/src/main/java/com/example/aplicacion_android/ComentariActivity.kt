package com.example.aplicacion_android

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ComentariActivity : AppCompatActivity() {

    private lateinit var ivMenu: ImageView
    private lateinit var btnLogin: Button
    private lateinit var tvUsuari: TextView
    private lateinit var tvComentari: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_comentari)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initComponents()
        initUI()
        initListeners()
    }

    private fun initComponents() {
        ivMenu = findViewById(R.id.ivMenu)
        btnLogin = findViewById(R.id.btnLogin)
        tvUsuari = findViewById(R.id.tvUsuari)
        tvComentari = findViewById(R.id.tvComentari)
    }

    private fun initUI(){
        tvUsuari.text = intent.extras?.getString("usuari") ?: "Nom d'usuari"
        tvComentari.text = intent.extras?.getString("comentari") ?: "Comentari"
    }

    private fun initListeners() {
        ivMenu.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }

        btnLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}