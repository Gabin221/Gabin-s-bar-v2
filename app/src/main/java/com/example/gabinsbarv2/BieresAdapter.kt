package com.example.gabinsbarv2

import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.progressindicator.CircularProgressIndicator
import dataClassBieres

class BieresAdapter(private val boissonList: List<dataClassBieres>) : RecyclerView.Adapter<BieresAdapter.BieresViewHolder>() {

    class BieresViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nomTextView: TextView = itemView.findViewById(R.id.nomBieres)
        val imageView: ImageView = itemView.findViewById(R.id.imageBieres)
        val quantiteAlcoolView: TextView = itemView.findViewById(R.id.quantiteAlcoolBieres)
        val bouteilleView: TextView = itemView.findViewById(R.id.bouteilleBieres)
        val paysView: TextView = itemView.findViewById(R.id.paysBieres)
        val styleView: TextView = itemView.findViewById(R.id.styleBieres)
        val circularProgressIndicator: CircularProgressIndicator = itemView.findViewById(R.id.circularProgressIndicator)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BieresViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_bieres, parent, false)
        return BieresViewHolder(view)
    }

    override fun onBindViewHolder(holder: BieresViewHolder, position: Int) {
        val boisson = boissonList[position]
        holder.nomTextView.text = boisson.nom
        holder.paysView.text = boisson.pays
        holder.styleView.text = boisson.style

        if (boisson.bouteille.equals("1")) {
            holder.bouteilleView.text = " (bouteille)"
        } else {
            holder.bouteilleView.text = " (pression)"
        }

        Glide.with(holder.itemView.context)
            .load(boisson.imageUrl)
            .into(holder.imageView)

        val handler = Handler(Looper.getMainLooper())
        var progress = 0.0
        val maxProgress = boisson.quantite_alcool.toDouble()
        val increment = 0.1

        holder.circularProgressIndicator.progress = progress.toInt()

        val runnable = object : Runnable {
            override fun run() {
                if (progress < maxProgress) {
                    holder.circularProgressIndicator.progress = progress.toInt()
                    holder.quantiteAlcoolView.text = "${String.format("%.1f", progress)}°"
                    progress += increment
                    handler.postDelayed(this, 10)
                } else {
                    holder.circularProgressIndicator.progress = maxProgress.toInt()
                    holder.quantiteAlcoolView.text = "${String.format("%.1f", maxProgress)}°"
                    handler.removeCallbacks(this)
                }
            }
        }

        handler.post(runnable)
    }

    override fun getItemCount(): Int {
        return boissonList.size
    }
}
