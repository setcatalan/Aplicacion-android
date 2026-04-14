package com.example.aplicacion_android.Main

import android.content.Intent
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.aplicacion_android.Login.LoginActivity
import com.example.aplicacion_android.R

class PrincipalActivity : AppCompatActivity() {

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
        enableEdgeToEdge()
        setContentView(R.layout.activity_principal)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initComponents()
        initListeners()
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