package com.example.aplicacion_android

import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class UsersActivity : AppCompatActivity() {

    private lateinit var btnClose: ImageView
    private lateinit var rvUsers: RecyclerView
    private lateinit var adapter: UsersAdapter

    private val usersViewModel: UsersViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_users)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initComponents()
        initListeners()
    }

    private fun initComponents() {
        btnClose = findViewById(R.id.btnClose)
        rvUsers = findViewById(R.id.rvUsers)
        rvUsers.layoutManager = LinearLayoutManager(this)

        usersViewModel.carregarUsuaris()
        usersViewModel.usuaris.observe(this){ usuaris ->
            adapter = UsersAdapter(usuaris)
            rvUsers.adapter = adapter
        }
    }

    private fun initListeners() {
        btnClose.setOnClickListener {
            finish()
        }
    }
}