package com.example.aplicacion_android

import android.content.Intent
import android.os.Bundle
import android.view.View.GONE
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class UserActivity : AppCompatActivity() {

    private lateinit var ivSlot1: ImageView
    private lateinit var ivSlot2: ImageView
    private lateinit var ivSlot3: ImageView

    private lateinit var ivDelSlot1: ImageView
    private lateinit var ivDelSlot2: ImageView
    private lateinit var ivDelSlot3: ImageView

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
        ivSlot1 = findViewById<ImageView>(R.id.slot1)
        ivSlot2 = findViewById<ImageView>(R.id.slot2)
        ivSlot3 = findViewById<ImageView>(R.id.slot3)
        ivDelSlot1 = findViewById<ImageView>(R.id.delete_slot1)
        ivDelSlot2 = findViewById<ImageView>(R.id.delete_slot2)
        ivDelSlot3 = findViewById<ImageView>(R.id.delete_slot3)

    }

    private fun initListenes() {
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
    }

    private fun initUI() {
        ivDelSlot1.visibility = GONE
        ivDelSlot2.visibility = GONE
        ivDelSlot3.visibility = GONE
    }
}