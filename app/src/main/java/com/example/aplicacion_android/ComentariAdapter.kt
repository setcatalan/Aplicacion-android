package com.example.aplicacion_android

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView

class ComentariAdapter(
    private val comentaris: List<Comentari>,
    private val onItemClik: (Comentari) -> Unit,
    private val onResultsChanged: (Boolean) -> Unit
) : RecyclerView.Adapter<ComentariViewHolder>(), Filterable{

    private val comentarisFiltrats = comentaris.toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComentariViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_comentari, parent, false)
        return ComentariViewHolder(view, onItemClik)
    }

    override fun onBindViewHolder(holder: ComentariViewHolder, position: Int) {
        val comentari = comentarisFiltrats[position]
        holder.bind(comentari)
    }

    override fun getItemCount(): Int = comentarisFiltrats.size

    override fun getFilter(): Filter? {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val text = constraint.toString().lowercase().trim()

                val result = if (text.isEmpty()) {
                    comentaris
                } else {
                    comentaris.filter {
                        it.joc.lowercase().contains(text)
                    }
                }

                return FilterResults().apply {
                    values = result
                }
            }
            @SuppressLint("NotifyDataSetChanged")
            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                comentarisFiltrats.clear()
                val filtered = results?.values as? List<Comentari> ?: emptyList()
                comentarisFiltrats.addAll(filtered)
                onResultsChanged(filtered.isNotEmpty())
                notifyDataSetChanged()
            }
        }
    }
}