package com.example.aplicacion_android.Jocs

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.aplicacion_android.R

class GameViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val name: TextView = view.findViewById(R.id.tvGameName)
    val category: TextView = view.findViewById(R.id.tvGameCategory)
}