package com.example.gabinsbarv2

import PanierManager.ajouterElement
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.progressindicator.CircularProgressIndicator
import dataClassAlcool

class AlcoolAdapter(private val boissonList: List<dataClassAlcool>) : RecyclerView.Adapter<AlcoolAdapter.AlcoolViewHolder>() {

    class AlcoolViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nomTextView: TextView = itemView.findViewById(R.id.nomAlcool)
        val imageView: ImageView = itemView.findViewById(R.id.imageAlcool)
        val quantiteTextView: TextView = itemView.findViewById(R.id.quantiteAlcoolAlcool)
        val circularProgressIndicator: CircularProgressIndicator = itemView.findViewById(R.id.circularProgressIndicator)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlcoolViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_alcool, parent, false)
        return AlcoolViewHolder(view)
    }

    override fun onBindViewHolder(holder: AlcoolViewHolder, position: Int) {
        val boisson = boissonList[position]
        holder.itemView.setOnClickListener {
            ajouterElement(boisson.nom)
            Toast.makeText(holder.itemView.context, "${boisson.nom} ajouté au panier", Toast.LENGTH_SHORT).show()
        }
        holder.nomTextView.text = boisson.nom

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
                    holder.quantiteTextView.text = "${String.format("%.1f", progress)}°"
                    progress += increment
                    handler.postDelayed(this, 10)
                } else {
                    holder.circularProgressIndicator.progress = maxProgress.toInt()
                    holder.quantiteTextView.text = "${String.format("%.1f", maxProgress)}°"
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
