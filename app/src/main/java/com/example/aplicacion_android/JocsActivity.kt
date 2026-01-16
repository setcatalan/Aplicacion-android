package com.example.aplicacion_android

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
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
}