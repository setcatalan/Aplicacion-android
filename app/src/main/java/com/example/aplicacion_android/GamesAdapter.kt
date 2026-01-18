package com.example.aplicacion_android

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GamesAdapter(
    private var items: List<Game>
) : RecyclerView.Adapter<GameViewHolder>() {
    fun updateList(newList: List<Game>) {
        items = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_jocs, parent, false)
        return GameViewHolder(view)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val game = items[position]
        holder.name.text = game.name
        holder.category.text = game.category.name
    }

    override fun getItemCount(): Int = items.size
}

class GameViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val name: TextView = view.findViewById(R.id.tvGameName)
    val category: TextView = view.findViewById(R.id.tvGameCategory)
}