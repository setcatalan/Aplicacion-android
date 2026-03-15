package com.example.aplicacion_android.User

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.aplicacion_android.R
import com.example.aplicacion_android.databinding.ActivityDadesUsuariBinding
import kotlin.getValue

class DadesUsuariActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDadesUsuariBinding

    private val usersViewModel: UsersViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityDadesUsuariBinding.inflate(layoutInflater)

        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnClose.setOnClickListener {
            finish()
        }

        binding.tvNom.text = intent.extras?.getString("nom")
        binding.tvId.text = intent.extras?.getInt("id").toString()
        binding.tvEdat.text = intent.extras?.getInt("edat").toString()
        binding.tvSexe.text = intent.extras?.getString("sexe")
        binding.tvContra.text = intent.extras?.getString("contra")
        binding.tvDesc.text = intent.extras?.getString("desc")

        binding.btnElimina.setOnClickListener {
            usersViewModel.eliminaUsuari(intent.extras?.getInt("id") ?: -1)
            setResult(RESULT_OK)
            finish()
        }
    }
}