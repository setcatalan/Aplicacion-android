package com.example.aplicacion_android.User

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter.FilterResults
import androidx.recyclerview.widget.RecyclerView
import com.example.aplicacion_android.Forum.Comentari
import com.example.aplicacion_android.R

class UsersAdapter(
    private var users: List<Usuari>?,
    private val onItemClick: (Usuari?) -> Unit
) : RecyclerView.Adapter<UsersViewHolder>() {

    fun actualitzaLlista(novaLlista: List<Usuari>){
        users = novaLlista
        Log.d("DEBUG", "Adapter actualizado: ${users?.size} usuarios")
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_user, parent, false)
        return UsersViewHolder(view, onItemClick)
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        val user = users?.get(position)
        holder.bind(user)
    }

    override fun getItemCount(): Int = users?.size ?: 0
}