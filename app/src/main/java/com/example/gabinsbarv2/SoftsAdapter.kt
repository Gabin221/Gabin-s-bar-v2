package com.example.gabinsbarv2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dataClassSofts

class BoissonAdapter(private val boissonList: List<dataClassSofts>) : RecyclerView.Adapter<BoissonAdapter.BoissonViewHolder>() {

    class BoissonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nomTextView: TextView = itemView.findViewById(R.id.nomSofts)
        val imageView: ImageView = itemView.findViewById(R.id.imageSofts)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoissonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_softs, parent, false)
        return BoissonViewHolder(view)
    }

    override fun onBindViewHolder(holder: BoissonViewHolder, position: Int) {
        val boisson = boissonList[position]
        holder.nomTextView.text = boisson.nom

        Glide.with(holder.itemView.context)
            .load(boisson.imageUrl)
            .into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return boissonList.size
    }
}
