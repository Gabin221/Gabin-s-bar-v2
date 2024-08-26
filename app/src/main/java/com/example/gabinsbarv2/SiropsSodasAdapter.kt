package com.example.gabinsbarv2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dataClassSiropsSodas

class SiropsSodasAdapter(private val boissonList: List<dataClassSiropsSodas>) : RecyclerView.Adapter<SiropsSodasAdapter.SiropsSodasViewHolder>() {

    class SiropsSodasViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nomTextView: TextView = itemView.findViewById(R.id.nomSiropsSodas)
        val imageView: ImageView = itemView.findViewById(R.id.imageSiropsSodas)
        val caloriesView: TextView = itemView.findViewById(R.id.caloriesSiropsSodas)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SiropsSodasViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_sirops_sodas, parent, false)
        return SiropsSodasViewHolder(view)
    }

    override fun onBindViewHolder(holder: SiropsSodasViewHolder, position: Int) {
        val boisson = boissonList[position]
        holder.nomTextView.text = boisson.nom
        holder.caloriesView.text = "Calories: ${boisson.calories} Kcal"

        // Utilisez une bibliothèque d'images comme Picasso ou Glide pour charger l'image à partir de l'URL
        Glide.with(holder.itemView.context)
            .load(boisson.imageUrl)
            .into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return boissonList.size
    }
}
