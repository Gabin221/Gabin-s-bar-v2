package com.example.gabinsbarv2.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.example.gabinsbarv2.R
import com.example.gabinsbarv2.databinding.FragmentSoftsBinding

class SoftsFragment : Fragment() {
    private var _binding: FragmentSoftsBinding? = null
    private val binding get() = _binding!!

    lateinit var quantiteBoissons: TextView
    lateinit var contenuSirops: LinearLayout
    lateinit var contenuSoft: LinearLayout
    lateinit var contenuCafes: LinearLayout
    lateinit var contenuThes: LinearLayout
    lateinit var buttonSirops: LinearLayout
    lateinit var buttonSoft: LinearLayout
    lateinit var buttonCafes: LinearLayout
    lateinit var buttonThes: LinearLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentSoftsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        quantiteBoissons = root.findViewById(R.id.quantiteBoissons)
        contenuSirops = root.findViewById(R.id.contenuSirops)
        contenuSoft = root.findViewById(R.id.contenuSofts)
        contenuCafes = root.findViewById(R.id.contenuCafes)
        contenuThes = root.findViewById(R.id.contenuThes)
        buttonSirops = root.findViewById(R.id.buttonSirops)
        buttonSoft = root.findViewById(R.id.buttonSofts)
        buttonCafes = root.findViewById(R.id.buttonCafes)
        buttonThes = root.findViewById(R.id.buttonThes)

        displayQuantites()
        gestionClicks()

        return root
    }

    fun gestionClicks() {
        buttonSirops.setOnClickListener {
            if (contenuSirops.visibility == View.GONE) {
                contenuSirops.visibility = View.VISIBLE
                contenuSoft.visibility = View.GONE
                contenuCafes.visibility = View.GONE
                contenuThes.visibility = View.GONE
            }
        }

        buttonSoft.setOnClickListener {
            if (contenuSoft.visibility == View.GONE) {
                contenuSoft.visibility = View.VISIBLE
                contenuSirops.visibility = View.GONE
                contenuCafes.visibility = View.GONE
                contenuThes.visibility = View.GONE
            }
        }

        buttonCafes.setOnClickListener {
            if (contenuCafes.visibility == View.GONE) {
                contenuCafes.visibility = View.VISIBLE
                contenuSoft.visibility = View.GONE
                contenuSirops.visibility = View.GONE
                contenuThes.visibility = View.GONE
            }
        }

        buttonThes.setOnClickListener {
            if (contenuThes.visibility == View.GONE) {
                contenuThes.visibility = View.VISIBLE
                contenuSoft.visibility = View.GONE
                contenuSirops.visibility = View.GONE
                contenuCafes.visibility = View.GONE
            }
        }
    }

    fun displayQuantites() {
        val quantites = listOf(28, 3, 6, 4)
        var quantiteTotal = 0
        for (quantite in quantites) {
            quantiteTotal += quantite
        }
        quantiteBoissons.text = "$quantiteTotal boissons dispos."
    }

}