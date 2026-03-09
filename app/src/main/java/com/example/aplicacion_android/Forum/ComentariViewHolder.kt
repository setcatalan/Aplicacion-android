package com.example.aplicacion_android.Forum

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.aplicacion_android.R

class ComentariViewHolder(
    comentariView: View,
    private val onItemClick: (Comentari) -> Unit
) : RecyclerView.ViewHolder(comentariView){

    private val tvUsuari: TextView = comentariView.findViewById(R.id.tvUsuari)
    private val tvComentari: TextView = comentariView.findViewById(R.id.tvComentari)
    private val btnJoc: Button = comentariView.findViewById(R.id.btnJoc)

    fun bind(comentari: Comentari) {
        tvUsuari.text = comentari.usuari
        tvComentari.text = comentari.comentari
        btnJoc.text = comentari.joc

        itemView.setOnClickListener {
            onItemClick(comentari)
        }
    }
}