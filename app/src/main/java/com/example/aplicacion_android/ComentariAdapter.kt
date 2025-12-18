package com.example.aplicacion_android

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ComentariAdapter(
    private val comentaris: List<Comentari>,
    private val onItemClik: (Comentari) -> Unit
) : RecyclerView.Adapter<ComentariViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComentariViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_comentari, parent, false)
        return ComentariViewHolder(view, onItemClik)
    }

    override fun onBindViewHolder(holder: ComentariViewHolder, position: Int) {
        val comentari = comentaris[position]
        holder.bind(comentari)
    }

    override fun getItemCount(): Int = comentaris.size
}