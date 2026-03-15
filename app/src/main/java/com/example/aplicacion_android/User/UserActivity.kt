package com.example.aplicacion_android.User

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View.GONE
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.aplicacion_android.R
import kotlin.getValue

class UserActivity : AppCompatActivity() {

    private lateinit var btn_close_edit: ImageView

    private lateinit var username: TextView
    private lateinit var tvNom: TextView
    private lateinit var tvDesc: TextView
    private lateinit var tvEdat: TextView
    private lateinit var tvSexe: TextView

    private lateinit var ivSlot1: ImageView
    private lateinit var ivSlot2: ImageView
    private lateinit var ivSlot3: ImageView

    private lateinit var ivDelSlot1: ImageView
    private lateinit var ivDelSlot2: ImageView
    private lateinit var ivDelSlot3: ImageView

    private lateinit var btnUsuaris: Button

    private val userViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_user)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initComponents()
        initListenes()
        initUI()
    }

    private fun initComponents() {
        btn_close_edit = findViewById<ImageView>(R.id.btn_close_edit)

        username = findViewById(R.id.username)
        tvNom = findViewById(R.id.tvNom)
        tvDesc = findViewById(R.id.tvDesc)
        tvEdat = findViewById(R.id.tvEdat)
        tvSexe = findViewById(R.id.tvSexe)

        ivSlot1 = findViewById<ImageView>(R.id.slot1)
        ivSlot2 = findViewById<ImageView>(R.id.slot2)
        ivSlot3 = findViewById<ImageView>(R.id.slot3)

        ivDelSlot1 = findViewById<ImageView>(R.id.delete_slot1)
        ivDelSlot2 = findViewById<ImageView>(R.id.delete_slot2)
        ivDelSlot3 = findViewById<ImageView>(R.id.delete_slot3)

        btnUsuaris = findViewById<Button>(R.id.btnUsuaris)

    }

    private fun initListenes() {
        btn_close_edit.setOnClickListener {
            finish()
        }

        ivSlot1.setOnClickListener {
            val intent = Intent(this, EditSlotsActivity::class.java)
            startActivity(intent)
        }

        ivSlot2.setOnClickListener {
            val intent = Intent(this, EditSlotsActivity::class.java)
            startActivity(intent)
        }

        ivSlot3.setOnClickListener {
            val intent = Intent(this, EditSlotsActivity::class.java)
            startActivity(intent)
        }

        btnUsuaris.setOnClickListener {
            val intent = Intent(this, UsersActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initUI() {
        userViewModel.carregaUsuari(intent.extras?.getInt("id") ?: -1)
        userViewModel.usuari.observe(this) { usuari ->
            username.text = usuari.nom
            tvNom.text = usuari.nom
            tvDesc.text = usuari.desc
            tvEdat.text = usuari.edat.toString()
            if (usuari.sexe.name == "masculí"){
                tvSexe.text = "Masculí"
            } else if (usuari.sexe.name == "femení"){
                tvSexe.text = "Femení"
            } else {
                tvSexe.text = "No Especificat"
            }
        }

        ivDelSlot1.visibility = GONE
        ivDelSlot2.visibility = GONE
        ivDelSlot3.visibility = GONE
    }
}