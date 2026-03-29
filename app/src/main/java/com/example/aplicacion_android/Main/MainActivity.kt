package com.example.aplicacion_android.Main

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.RadioGroup
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.aplicacion_android.Login.LoginActivity
import com.example.aplicacion_android.R

class MainActivity : AppCompatActivity() {

    private lateinit var btnSeguent: Button
    private lateinit var btnLogin: Button
    private lateinit var rgEstil: RadioGroup
    private lateinit var ivGrafics: ImageView
    private lateinit var spinnerIdioma: Spinner

    private var idiomaSeleccionat: String = "Català"

    override fun onCreate(savedInstanceState: Bundle?) {

        val splashScreen = installSplashScreen()

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initComponents()
        initListeners()
    }

    private fun initComponents() {
        btnSeguent = findViewById(R.id.btnSeguent)
        btnLogin = findViewById(R.id.btnLogin)
        rgEstil = findViewById(R.id.rgEstil)
        ivGrafics = findViewById(R.id.ivGrafics)
        spinnerIdioma = findViewById(R.id.spinnerIdioma)

        val idiomes = listOf(
            "Català",
            "Castellano",
            "English",
            "Estaria bien que enseñaras en vez de criticar"
        )
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, idiomes)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerIdioma.adapter = adapter
    }

    private fun initListeners() {
        btnSeguent.setOnClickListener {
            val intent = Intent(this, PrincipalActivity::class.java)
            startActivity(intent)
        }

        btnLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        rgEstil.setOnCheckedChangeListener { group, checkedId ->  }

        ivGrafics.setOnClickListener {
            val intent = Intent(this, GraficsActivity::class.java)
            startActivity(intent)
        }

        spinnerIdioma.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: android.view.View?, position: Int, id: Long) {
                idiomaSeleccionat = parent.getItemAtPosition(position).toString()
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }
}