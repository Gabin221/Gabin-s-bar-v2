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
import dataClassVins

class VinsAdapter(private val boissonList: List<dataClassVins>) : RecyclerView.Adapter<VinsAdapter.VinsViewHolder>() {

    class VinsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nomTextView: TextView = itemView.findViewById(R.id.nomVins)
        val imageView: ImageView = itemView.findViewById(R.id.imageVins)
        val quantiteTextView: TextView = itemView.findViewById(R.id.quantiteAlcoolVins)
        val regionTextView: TextView = itemView.findViewById(R.id.regionVins)
        val styleTextView: TextView = itemView.findViewById(R.id.styleVins)
        val circularProgressIndicator: CircularProgressIndicator = itemView.findViewById(R.id.circularProgressIndicator)
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
                    holder.quantiteTextView.text = "Alcool: ${String.format("%.1f", progress)}°"
                    progress += increment
                    handler.postDelayed(this, 10)
                } else {
                    holder.circularProgressIndicator.progress = maxProgress.toInt()
                    holder.quantiteTextView.text = "Alcool: ${String.format("%.1f", maxProgress)}°"
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
