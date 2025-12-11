package com.example.aplicacion_android

import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.imageview.ShapeableImageView

class EditSlotsActivity : AppCompatActivity() {

    private lateinit var edit_slot_image: ShapeableImageView
    private lateinit var btn_close_edit: ImageView
    private lateinit var btn_save_slot: Button

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
        btn_close_edit = findViewById<ImageView>(R.id.btn_close_edit)
        btn_save_slot = findViewById<Button>(R.id.btn_save_slot)
    }

    private fun initListeners() {
        edit_slot_image.setOnClickListener {
            val intent = Intent(this, GamesSelectorActivity::class.java)
            startActivity(intent)
        }

        btn_close_edit.setOnClickListener { finish() }

        btn_save_slot.setOnClickListener {
            finish()
        }
    }
}