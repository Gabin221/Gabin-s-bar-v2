package com.example.gabinsbarv2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dataClassBieres

class BieresAdapter(private val boissonList: List<dataClassBieres>) : RecyclerView.Adapter<BieresAdapter.BieresViewHolder>() {

    class BieresViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nomTextView: TextView = itemView.findViewById(R.id.nomBieres)
        val imageView: ImageView = itemView.findViewById(R.id.imageBieres)
        val quantiteAlcoolView: TextView = itemView.findViewById(R.id.quantiteAlcoolBieres)
        val bouteilleView: TextView = itemView.findViewById(R.id.bouteilleBieres)
        val paysView: TextView = itemView.findViewById(R.id.paysBieres)
        val styleView: TextView = itemView.findViewById(R.id.styleBieres)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BieresViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_bieres, parent, false)
        return BieresViewHolder(view)
    }

    override fun onBindViewHolder(holder: BieresViewHolder, position: Int) {
        val boisson = boissonList[position]
        holder.nomTextView.text = boisson.nom
        holder.paysView.text = "Pays: ${boisson.pays}"
        holder.styleView.text = "Style: ${boisson.style}"
        holder.quantiteAlcoolView.text = "Alcool: ${boisson.quantite_alcool}°"

        if (boisson.bouteille.equals("1")) {
            holder.bouteilleView.text = " (bouteille)"
        } else {
            holder.bouteilleView.text = " (pression)"
        }

        // Utilisez une bibliothèque d'images comme Picasso ou Glide pour charger l'image à partir de l'URL
        Glide.with(holder.itemView.context)
            .load(boisson.imageUrl)
            .into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return boissonList.size
    }
}
