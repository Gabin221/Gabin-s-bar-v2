package com.example.gabinsbarv2.fragments

import PanierManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gabinsbarv2.PanierAdapter
import com.example.gabinsbarv2.databinding.FragmentPanierBinding
import com.example.gabinsbarv2.R

class PanierFragment : Fragment() {
    private var _binding: FragmentPanierBinding? = null
    private val binding get() = _binding!!

    private lateinit var boissonAdapterPanier: PanierAdapter
    lateinit var viderPanierButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentPanierBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val recyclerViewPanier = root.findViewById<RecyclerView>(R.id.recyclerViewPanier)
        recyclerViewPanier.layoutManager = LinearLayoutManager(requireContext())

        boissonAdapterPanier = PanierAdapter(PanierManager.monPanier) { position ->
            PanierManager.supprimerElement(position)
            boissonAdapterPanier.notifyItemRemoved(position)
            boissonAdapterPanier.notifyItemRangeChanged(position, PanierManager.monPanier.size)
        }

        recyclerViewPanier.adapter = boissonAdapterPanier

        viderPanierButton = root.findViewById(R.id.viderPanierButton)
        viderPanierButton.setOnClickListener {
            PanierManager.supprimerTout()
            boissonAdapterPanier.notifyDataSetChanged()
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
