package com.example.aplicacion_android.User

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aplicacion_android.Forum.ComentariActivity
import com.example.aplicacion_android.R

class UsersActivity : AppCompatActivity() {

    private lateinit var btnClose: ImageView
    private lateinit var rvUsers: RecyclerView
    private lateinit var adapter: UsersAdapter

    private val usersViewModel: UsersViewModel by viewModels()

    val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            usersViewModel.carregarUsuaris()
        }
    }

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

        usersViewModel.usuaris.observe(this) { lista ->
            adapter.actualitzaLlista(lista)
        }
    }

    private fun initComponents() {
        btnClose = findViewById(R.id.btnClose)
        rvUsers = findViewById(R.id.rvUsers)
        rvUsers.layoutManager = LinearLayoutManager(this)

        // Inicializa el adapter vacío primero
        adapter = UsersAdapter(
            emptyList(),
            onItemClick = { usuari ->
                val intent = Intent(this, DadesUsuariActivity::class.java)
                intent.putExtra("id", usuari?.id ?: -1)
                intent.putExtra("nom", usuari?.nom)
                intent.putExtra("desc", usuari?.desc)
                intent.putExtra("edat", usuari?.edat ?: -1)
                intent.putExtra("sexe", usuari?.sexe?.name)
                intent.putExtra("contra", usuari?.contra)
                launcher.launch(intent)
            }
        )
        rvUsers.adapter = adapter

        // Un solo observer que actualiza la lista
        usersViewModel.usuaris.observe(this) { lista ->
            Log.d("DEBUG", "Lista actualizada: ${lista.size} usuarios")
            adapter.actualitzaLlista(lista)
        }

        usersViewModel.carregarUsuaris()
    }

    private fun initListeners() {
        btnClose.setOnClickListener {
            finish()
        }
    }
}