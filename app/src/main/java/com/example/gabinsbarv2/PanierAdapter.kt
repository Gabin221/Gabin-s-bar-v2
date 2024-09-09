package com.example.gabinsbarv2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PanierAdapter(private val boissonList: MutableList<String>, private val onDeleteClick: (Int) -> Unit) : RecyclerView.Adapter<PanierAdapter.PanierViewHolder>() {

    class PanierViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nomTextView: TextView = itemView.findViewById(R.id.nomPanier)
        val supprimerButton: Button = itemView.findViewById(R.id.supprimerButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PanierViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_panier, parent, false)
        return PanierViewHolder(view)
    }

    override fun onBindViewHolder(holder: PanierViewHolder, position: Int) {
        val boisson = boissonList[position]
        holder.nomTextView.text = boisson

        holder.supprimerButton.setOnClickListener {
            onDeleteClick(position)
        }
    }

    override fun getItemCount(): Int {
        return boissonList.size
    }
}
