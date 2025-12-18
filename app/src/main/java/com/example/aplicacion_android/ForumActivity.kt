package com.example.aplicacion_android

import android.content.Intent
import android.os.Bundle
import android.widget.Adapter
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ForumActivity : AppCompatActivity() {

    private lateinit var ivMenu: ImageView
    private lateinit var btnLogin: Button

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

        rvComentaris = findViewById(R.id.rvComentaris)
        rvComentaris.layoutManager = LinearLayoutManager(this)

        val comentaris = DataSource.comentaris

        adapter = ComentariAdapter(
            comentaris,
            { comentari ->
                val intent = Intent(this, ComentariActivity::class.java)
                startActivity(intent)
            }
        )

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
    }
}