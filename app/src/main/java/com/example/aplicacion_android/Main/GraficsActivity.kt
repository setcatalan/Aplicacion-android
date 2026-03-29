package com.example.aplicacion_android.Main

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.aplicacion_android.R
import com.example.aplicacion_android.User.Usuari
import com.example.aplicacion_android.databinding.ActivityGraficsBinding
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.LegendEntry
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import kotlinx.coroutines.launch
import androidx.core.graphics.toColorInt
import com.example.aplicacion_android.Login.UsuariLogin
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet

class GraficsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGraficsBinding

    private val graficsViewModel: GraficsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityGraficsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                graficsViewModel.llistaUsuaris.collect { usuaris ->
                    if (usuaris.isNotEmpty()) {
                        actualitzarGraficGenere(usuaris)
                        actualitzarGraficEdat(usuaris)
                    }
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                graficsViewModel.llistaLogins.collect { logins ->
                    if (logins.isNotEmpty()) {
                        actualitzarGraficLogins(logins)
                    }
                }
            }
        }
    }

    private fun actualitzarGraficGenere(usuaris: List<Usuari>) {
        val entries = graficsViewModel.graficGenere(usuaris)

        val dataSet = BarDataSet(entries, "").apply {
            valueTextSize = 12f
            colors = listOf(
                "#F44336".toColorInt(),
                "#4CAF50".toColorInt(),
                "#2196F3".toColorInt()
            )
        }

        val legendEntries = listOf(
            LegendEntry("Masculí", Legend.LegendForm.SQUARE, 10f, 2f, null, "#F44336".toColorInt()),
            LegendEntry("Femení", Legend.LegendForm.SQUARE, 10f, 2f, null, "#4CAF50".toColorInt()),
            LegendEntry("No especificat", Legend.LegendForm.SQUARE, 10f, 2f, null, "#2196F3".toColorInt())
        )

        binding.barChartGenere.apply {
            data = BarData(dataSet)
            description.text = ""
            xAxis.valueFormatter = IndexAxisValueFormatter(listOf("", "Masculí", "Femení", "No esp."))
            xAxis.position = XAxis.XAxisPosition.BOTTOM
            xAxis.granularity = 1f
            legend.setCustom(legendEntries)
            notifyDataSetChanged()
            invalidate()
        }
    }

    private fun actualitzarGraficEdat(usuaris: List<Usuari>) {
        val entries = graficsViewModel.graficEdat(usuaris)

        val dataSet = BarDataSet(entries, "").apply {
            valueTextSize = 12f
            colors = listOf(
                "#FF9800".toColorInt(),
                "#9C27B0".toColorInt(),
                "#2196F3".toColorInt(),
                "#4CAF50".toColorInt()
            )
        }

        val legendEntries = listOf(
            LegendEntry("0-17 anys", Legend.LegendForm.SQUARE, 10f, 2f, null, "#FF9800".toColorInt()),
            LegendEntry("18-35 anys", Legend.LegendForm.SQUARE, 10f, 2f, null, "#9C27B0".toColorInt()),
            LegendEntry("36-60 anys", Legend.LegendForm.SQUARE, 10f, 2f, null, "#2196F3".toColorInt()),
            LegendEntry("61+ anys", Legend.LegendForm.SQUARE, 10f, 2f, null, "#4CAF50".toColorInt())
        )

        binding.barChartEdat.apply {  // ✅ nuevo chart en el layout
            data = BarData(dataSet)
            description.text = ""
            xAxis.valueFormatter = IndexAxisValueFormatter(
                listOf("", "0-17", "18-35", "36-60", "61+")
            )
            xAxis.position = XAxis.XAxisPosition.BOTTOM
            xAxis.granularity = 1f
            legend.setCustom(legendEntries)
            notifyDataSetChanged()
            invalidate()
        }
    }

    private fun actualitzarGraficLogins(logins: List<UsuariLogin>) {
        val entries = graficsViewModel.graficLogins(logins)

        val colors = listOf(
            "#F44336".toColorInt(),
            "#4CAF50".toColorInt(),
            "#2196F3".toColorInt(),
            "#FF9800".toColorInt(),
            "#9C27B0".toColorInt(),
            "#00BCD4".toColorInt(),
            "#FF5722".toColorInt(),
            "#607D8B".toColorInt()
        )

        val dataSet = PieDataSet(entries, "").apply {
            this.colors = colors.take(entries.size)
            valueTextSize = 12f
            valueTextColor = android.graphics.Color.WHITE
        }

        binding.pieChartLogins.apply {
            data = PieData(dataSet)
            description.text = ""
            isDrawHoleEnabled = true
            holeRadius = 40f
            setHoleColor(android.graphics.Color.TRANSPARENT)
            setEntryLabelColor(android.graphics.Color.BLACK)
            setEntryLabelTextSize(11f)
            legend.isEnabled = true
            legend.form = Legend.LegendForm.CIRCLE
            notifyDataSetChanged()
            invalidate()
        }
    }
}