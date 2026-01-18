package com.example.aplicacion_android

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class ForumActivity : AppCompatActivity(){

    private lateinit var ivMenu: ImageView
    private lateinit var btnLogin: Button
    private lateinit var ivFiltra: ImageView
    private lateinit var etFiltre: EditText
    private lateinit var tvSinResultados: TextView

    private lateinit var rvComentaris: RecyclerView
    private lateinit var adapter: ComentariAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_forum)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initComponents()
        initListeners()
    }

    private fun initComponents() {
        ivMenu = findViewById(R.id.ivMenu)
        btnLogin = findViewById(R.id.btnLogin)
        ivFiltra = findViewById(R.id.ivFiltra)
        etFiltre = findViewById(R.id.etFiltre)
        tvSinResultados = findViewById(R.id.tvSinResultados)

        rvComentaris = findViewById(R.id.rvComentaris)
        rvComentaris.layoutManager = LinearLayoutManager(this)

        val comentaris = DataSource.comentaris

        adapter = ComentariAdapter(
            comentaris,
            onItemClik = { comentari ->
                val intent = Intent(this, ComentariActivity::class.java)
                intent.putExtra("comentari", comentari.comentari)
                intent.putExtra("usuari", comentari.usuari)
                startActivity(intent)
            }
        ) { hayResultados ->
            rvComentaris.isVisible = hayResultados
            tvSinResultados.isVisible = !hayResultados
        }

        rvComentaris.adapter = adapter
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

        ivFiltra.setOnClickListener {
            if (etFiltre.isGone) {
                etFiltre.visibility = View.VISIBLE
            } else if (etFiltre.isVisible){
                etFiltre.visibility = View.GONE
            }
        }

        etFiltre.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                adapter.filter?.filter(s)
            }
        })

    }
}