package com.example.gabinsbarv2.fragments

import PanierManager.monPanier
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.gabinsbarv2.R
import com.example.gabinsbarv2.databinding.FragmentPanierBinding

class PanierFragment : Fragment() {
    private var _binding: FragmentPanierBinding? = null
    private val binding get() = _binding!!

    lateinit var listeBoissons: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentPanierBinding.inflate(inflater, container, false)
        val root: View = binding.root

        listeBoissons = root.findViewById(R.id.listeBoissons)

        listeBoissons.text = monPanier.toString()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
