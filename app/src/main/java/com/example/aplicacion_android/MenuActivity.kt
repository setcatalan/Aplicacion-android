package com.example.aplicacion_android

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MenuActivity : AppCompatActivity() {

    private lateinit var ivX: ImageView
    private lateinit var tvPgPrincipal: TextView
    private lateinit var tvPgForum: TextView
    private lateinit var tvPgPreguntes: TextView
    private lateinit var tvPgInfo: TextView
    private lateinit var tvPgAjuda: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initComponents()
        initListeners()
    }

    private fun initComponents() {
        ivX = findViewById<ImageView>(R.id.ivX)
        tvPgPrincipal = findViewById<TextView>(R.id.tvPgPrincipal)
        tvPgForum = findViewById<TextView>(R.id.tvPgForum)
        tvPgPreguntes = findViewById<TextView>(R.id.tvPgPreguntes)
        tvPgInfo = findViewById<TextView>(R.id.tvPgInfo)
        tvPgAjuda = findViewById<TextView>(R.id.tvPgAjuda)
    }

    private fun initListeners() {
        ivX.setOnClickListener {
            finish()
        }

        tvPgPrincipal.setOnClickListener {
            val intent = Intent(this, PrincipalActivity::class.java)
            startActivity(intent)
            finish()
        }

        tvPgForum.setOnClickListener {
            val intent = Intent(this, ForumActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}