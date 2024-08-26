package com.example.gabinsbarv2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dataClassAlcool

class AlcoolAdapter(private val boissonList: List<dataClassAlcool>) : RecyclerView.Adapter<AlcoolAdapter.AlcoolViewHolder>() {

    class AlcoolViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nomTextView: TextView = itemView.findViewById(R.id.nomAlcool)
        val imageView: ImageView = itemView.findViewById(R.id.imageAlcool)
        val quantiteTextView: TextView = itemView.findViewById(R.id.quantiteAlcoolAlcool)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlcoolViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_alcool, parent, false)
        return AlcoolViewHolder(view)
    }

    override fun onBindViewHolder(holder: AlcoolViewHolder, position: Int) {
        val boisson = boissonList[position]
        holder.nomTextView.text = boisson.nom
        holder.quantiteTextView.text = "Alcool: ${boisson.quantite_alcool}°"

        // Utilisez une bibliothèque d'images comme Picasso ou Glide pour charger l'image à partir de l'URL
        Glide.with(holder.itemView.context)
            .load(boisson.imageUrl)
            .into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return boissonList.size
    }
}
