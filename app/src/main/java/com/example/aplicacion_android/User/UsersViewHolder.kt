package com.example.aplicacion_android.User

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.aplicacion_android.R
import com.squareup.picasso.Picasso

class UsersViewHolder(
    userView: View
) : RecyclerView.ViewHolder(userView) {

    private val tvUsuari: TextView = userView.findViewById(R.id.tvUsuari)
    private val ivUsuari: ImageView = userView.findViewById(R.id.ivUsuari)

    fun bind(user: Usuari?){
        tvUsuari.text = user?.nom
        /* Imatge del usuari  TODO: si no te imatge que es posi una per defecte
        Picasso.get()
            .load("http://129.158.219.17:8080/api/usuaris/get/imatge/" + user?.id)
            .into(ivUsuari)*/
    }
}