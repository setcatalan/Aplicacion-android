package com.example.aplicacion_android

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView

class JocsActivity: AppCompatActivity() {

    private lateinit var adapter: GamesAdapter

    private val allGames = listOf(
        Game("The Witcher 3", Category.RPG),
        Game("Elden Ring", Category.RPG),
        Game("Final Fastasy VII Remake", Category.RPG),

        Game("God of War", Category.AVENTURA),
        Game("The Last of Us", Category.AVENTURA),
        Game("Zelda: Breath of the Wild", Category.AVENTURA),

        Game("DOOM Eternal", Category.ACCIO),
        Game("Call Of Duty", Category.ACCIO),
        Game("Hades", Category.ACCIO)
    )

    private var selectedCategory: Category = Category.TOTS
    private var searchText: String = ""
    private lateinit var ivMenu: ImageView
    private lateinit var btnLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jocs)

        val recycler = findViewById<RecyclerView>(R.id.recycler_novetats)
        adapter = GamesAdapter(allGames)
        recycler.adapter = adapter

        applyFilters()

        val search = findViewById<EditText>(R.id.search_game)
        search.addTextChangedListener { editable ->
            searchText = editable?.toString().orEmpty()
            applyFilters()
        }

        findViewById<Button>(R.id.btnTots).setOnClickListener {
            selectedCategory = Category.TOTS
            applyFilters()
        }

        findViewById<Button>(R.id.btnAccio).setOnClickListener {
            selectedCategory = Category.ACCIO
            applyFilters()
        }

        findViewById<Button>(R.id.btnRPG).setOnClickListener {
            selectedCategory = Category.RPG
            applyFilters()
        }

        findViewById<Button>(R.id.btnAventura).setOnClickListener {
            selectedCategory = Category.AVENTURA
            applyFilters()
        }
        initComponents()
        initListeners()
    }

    private fun applyFilters() {
        val filtered = allGames
            .filter { game ->
                selectedCategory == Category.TOTS || game.category == selectedCategory
            }
            .filter { game ->
                game.name.contains(searchText, ignoreCase = true)
            }
        adapter.updateList(filtered)
    }

    private fun initComponents() {
        ivMenu = findViewById<ImageView>(R.id.ivMenu)
        btnLogin = findViewById<Button>(R.id.btnLogin)
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