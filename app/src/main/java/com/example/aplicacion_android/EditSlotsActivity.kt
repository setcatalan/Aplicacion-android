package com.example.aplicacion_android

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.imageview.ShapeableImageView

class EditSlotsActivity : AppCompatActivity() {

    private lateinit var edit_slot_image: ShapeableImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_edit_slots)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initComponents()
        initListeners()
    }

    private fun initComponents() {
        edit_slot_image = findViewById<ShapeableImageView>(R.id.edit_slot_image)
    }

    private fun initListeners() {
        edit_slot_image.setOnClickListener {
            val intent = Intent(this, GamesSelectorActivity::class.java)
            startActivity(intent)
        }
    }
}