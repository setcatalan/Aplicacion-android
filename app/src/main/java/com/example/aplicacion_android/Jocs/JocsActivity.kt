package com.example.aplicacion_android.Jocs

import android.content.Intent
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import com.example.aplicacion_android.Login.LoginActivity
import com.example.aplicacion_android.Main.MenuActivity
import com.example.aplicacion_android.R

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

    private lateinit var recognizer: SpeechRecognizer

    val recognizerIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
        putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        putExtra(RecognizerIntent.EXTRA_LANGUAGE, "ca-ES")
    }

    private var recoVeu: Boolean = false

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

        recognizer = SpeechRecognizer.createSpeechRecognizer(this)

        recoVeu = intent.extras?.getBoolean("recoVeu") ?: false
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

        recognizer.setRecognitionListener(object: RecognitionListener{
            override fun onResults(results: Bundle?) {
                val spokenText = results
                    ?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
                    ?.get(0)
                    ?.lowercase()

                handleVoiceCommand(spokenText)
            }

            override fun onError(error: Int) {}
            override fun onReadyForSpeech(params: Bundle?) {}
            override fun onBeginningOfSpeech() {}
            override fun onRmsChanged(rmsdB: Float) {}
            override fun onBufferReceived(buffer: ByteArray?) {}
            override fun onEndOfSpeech() {}
            override fun onPartialResults(partialResults: Bundle?) {}
            override fun onEvent(eventType: Int, params: Bundle?) {}
        })

        if (recoVeu){
            recognizer.startListening(recognizerIntent)
        }
    }

    private fun handleVoiceCommand(command: String?) {
        when {
            command?.contains("menu") == true -> {
                val intent = Intent(this, MenuActivity::class.java)
                intent.putExtra("recoVeu", recoVeu)
                startActivity(intent)
            }
            command?.contains("iniciar sessió") == true -> {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
        }
    }
}