package com.example.gabinsbarv2.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.example.gabinsbarv2.R
import com.example.gabinsbarv2.databinding.FragmentAlcoolBinding

class AlcoolFragment : Fragment() {
    private var _binding: FragmentAlcoolBinding? = null
    private val binding get() = _binding!!

    lateinit var quantiteBoissons: TextView
    lateinit var contenuBieres: LinearLayout
    lateinit var contenuVins: LinearLayout
    lateinit var contenuClassiques: LinearLayout
    lateinit var contenuExtravagants: LinearLayout
    lateinit var buttonBieres: LinearLayout
    lateinit var buttonVins: LinearLayout
    lateinit var buttonClassiques: LinearLayout
    lateinit var buttonExtravagants: LinearLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentAlcoolBinding.inflate(inflater, container, false)
        val root: View = binding.root

        quantiteBoissons = root.findViewById(R.id.quantiteBoissons)
        contenuBieres = root.findViewById(R.id.contenuBieres)
        contenuVins = root.findViewById(R.id.contenuVins)
        contenuClassiques = root.findViewById(R.id.contenuClassiques)
        contenuExtravagants = root.findViewById(R.id.contenuExtravagants)
        buttonBieres = root.findViewById(R.id.buttonBieres)
        buttonVins = root.findViewById(R.id.buttonVins)
        buttonClassiques = root.findViewById(R.id.buttonClassiques)
        buttonExtravagants = root.findViewById(R.id.buttonExtravagants)

        displayQuantites()
        gestionClicks()

        return root
    }

    fun gestionClicks() {
        buttonBieres.setOnClickListener {
            if (contenuBieres.visibility == View.GONE) {
                contenuBieres.visibility = View.VISIBLE
                contenuVins.visibility = View.GONE
                contenuClassiques.visibility = View.GONE
                contenuExtravagants.visibility = View.GONE
            }
        }

        buttonVins.setOnClickListener {
            if (contenuVins.visibility == View.GONE) {
                contenuVins.visibility = View.VISIBLE
                contenuBieres.visibility = View.GONE
                contenuClassiques.visibility = View.GONE
                contenuExtravagants.visibility = View.GONE
            }
        }

        buttonClassiques.setOnClickListener {
            if (contenuClassiques.visibility == View.GONE) {
                contenuClassiques.visibility = View.VISIBLE
                contenuVins.visibility = View.GONE
                contenuBieres.visibility = View.GONE
                contenuExtravagants.visibility = View.GONE
            }
        }

        buttonExtravagants.setOnClickListener {
            if (contenuExtravagants.visibility == View.GONE) {
                contenuExtravagants.visibility = View.VISIBLE
                contenuVins.visibility = View.GONE
                contenuBieres.visibility = View.GONE
                contenuClassiques.visibility = View.GONE
            }
        }
    }

    fun displayQuantites() {
        val quantites = listOf(5, 7, 6, 4)
        var quantiteTotal = 0
        for (quantite in quantites) {
            quantiteTotal += quantite
        }
        quantiteBoissons.text = "$quantiteTotal boissons dispos."
    }

}