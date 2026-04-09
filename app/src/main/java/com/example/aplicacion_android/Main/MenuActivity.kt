package com.example.aplicacion_android.Main

import android.content.Intent
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.aplicacion_android.Ajuda.AjudaActivity
import com.example.aplicacion_android.Forum.ForumActivity
import com.example.aplicacion_android.Jocs.JocsActivity
import com.example.aplicacion_android.Preguntes.PreguntesActivity
import com.example.aplicacion_android.R

class MenuActivity : AppCompatActivity() {

    private lateinit var ivX: ImageView
    private lateinit var tvPgPrincipal: TextView
    private lateinit var tvPgForum: TextView
    private lateinit var tvPgPreguntes: TextView
    private lateinit var tvPgInfo: TextView
    private lateinit var tvPgAjuda: TextView

    private lateinit var recognizer: SpeechRecognizer
    private lateinit var recognizerIntent: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initComponents()
        initSpeechRecognizer()
        initListeners()
    }

    private fun initComponents() {
        ivX = findViewById<ImageView>(R.id.ivX)
        tvPgPrincipal = findViewById<TextView>(R.id.tvPgPrincipal)
        tvPgForum = findViewById<TextView>(R.id.tvPgForum)
        tvPgPreguntes = findViewById<TextView>(R.id.tvPgPreguntes)
        tvPgInfo = findViewById<TextView>(R.id.tvPgInfo)
        tvPgAjuda = findViewById<TextView>(R.id.tvPgAjuda)
    }

    private fun initSpeechRecognizer() {
        recognizer = SpeechRecognizer.createSpeechRecognizer(this)

        recognizerIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            putExtra(RecognizerIntent.EXTRA_LANGUAGE, "ca-ES")
        }

        recognizer.setRecognitionListener(object : RecognitionListener {
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
    }


    private fun initListeners() {

        ivX.setOnClickListener { finish() }

        tvPgPrincipal.setOnClickListener {
            val intent = Intent(this, PrincipalActivity::class.java)
            startActivity(intent)
            finish()
        }

        tvPgForum.setOnClickListener {
            val intent = Intent(this, ForumActivity::class.java)
            startActivity(intent)
            finish()
        }

        tvPgPreguntes.setOnClickListener {
            val intent = Intent(this, PreguntesActivity::class.java)
            startActivity(intent)
            finish()
        }

        tvPgInfo.setOnClickListener {
            val intent = Intent(this, JocsActivity::class.java)
            startActivity(intent)
            finish()
        }

        tvPgAjuda.setOnClickListener {
            val intent = Intent(this, AjudaActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun handleVoiceCommand(command: String?) {
        when {
            command?.contains("principal") == true -> {
                val intent = Intent(this, PrincipalActivity::class.java)
                startActivity(intent)
                finish()
            }

            command?.contains("forum") == true || command?.contains("fòrum") == true -> {
                val intent = Intent(this, ForumActivity::class.java)
                startActivity(intent)
                finish()
            }

            command?.contains("preguntes") == true -> {
                val intent = Intent(this, PreguntesActivity::class.java)
                startActivity(intent)
                finish()
            }

            command?.contains("info") == true || command?.contains("jocs") == true -> {
                val intent = Intent(this, JocsActivity::class.java)
                startActivity(intent)
                finish()
            }

            command?.contains("ajuda") == true -> {
                val intent = Intent(this, AjudaActivity::class.java)
                startActivity(intent)
                finish()
            }

            command?.contains("enrere") == true || command?.contains("sortir") == true -> {
                finish()
            }
        }
    }


}