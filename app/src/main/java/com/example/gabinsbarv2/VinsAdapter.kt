package com.example.gabinsbarv2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dataClassVins

class VinsAdapter(private val boissonList: List<dataClassVins>) : RecyclerView.Adapter<VinsAdapter.VinsViewHolder>() {

    class VinsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nomTextView: TextView = itemView.findViewById(R.id.nomVins)
        val imageView: ImageView = itemView.findViewById(R.id.imageVins)
        val quantiteTextView: TextView = itemView.findViewById(R.id.quantiteAlcoolVins)
        val regionTextView: TextView = itemView.findViewById(R.id.regionVins)
        val styleTextView: TextView = itemView.findViewById(R.id.styleVins)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VinsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_vins, parent, false)
        return VinsViewHolder(view)
    }

    override fun onBindViewHolder(holder: VinsViewHolder, position: Int) {
        val boisson = boissonList[position]
        holder.nomTextView.text = boisson.nom
        holder.quantiteTextView.text = "Alcool: ${boisson.quantite_alcool}°"
        holder.regionTextView.text = "Région: ${boisson.region}"
        holder.styleTextView.text = "Type: ${boisson.style}"

        // Utilisez une bibliothèque d'images comme Picasso ou Glide pour charger l'image à partir de l'URL
        Glide.with(holder.itemView.context)
            .load(boisson.imageUrl)
            .into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return boissonList.size
    }
}
