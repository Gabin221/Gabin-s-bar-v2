package com.example.gabinsbarv2.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.gabinsbarv2.R
import com.example.gabinsbarv2.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    lateinit var buttonHome2Sirops: LinearLayout
    lateinit var buttonHome2Softs: LinearLayout
    lateinit var buttonHome2Bieres: LinearLayout
    lateinit var buttonHome2Vins: LinearLayout
    lateinit var buttonHome2Classiques: LinearLayout
    lateinit var buttonHome2Extravagants: LinearLayout
    lateinit var buttonHome2Cafes: LinearLayout
    lateinit var buttonHome2Thes: LinearLayout
    lateinit var quantiteBoissons: TextView
    lateinit var quantiteSirops: TextView
    lateinit var quantiteSofts: TextView
    lateinit var quantiteBieres: TextView
    lateinit var quantiteVins: TextView
    lateinit var quantiteClassiques: TextView
    lateinit var quantiteExtravagants: TextView
    lateinit var quantiteCafes: TextView
    lateinit var quantiteThes: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        buttonHome2Sirops = root.findViewById(R.id.buttonHome2Sirops)
        buttonHome2Softs = root.findViewById(R.id.buttonHome2Softs)
        buttonHome2Bieres = root.findViewById(R.id.buttonHome2Bieres)
        buttonHome2Vins = root.findViewById(R.id.buttonHome2Vins)
        buttonHome2Classiques = root.findViewById(R.id.buttonHome2Classiques)
        buttonHome2Extravagants = root.findViewById(R.id.buttonHome2Extravagants)
        buttonHome2Cafes = root.findViewById(R.id.buttonHome2Cafes)
        buttonHome2Thes = root.findViewById(R.id.buttonHome2Thes)
        quantiteBoissons = root.findViewById(R.id.quantiteBoissons)
        quantiteSirops = root.findViewById(R.id.quantiteSirops)
        quantiteSofts = root.findViewById(R.id.quantiteSofts)
        quantiteBieres = root.findViewById(R.id.quantiteBieres)
        quantiteVins = root.findViewById(R.id.quantiteVins)
        quantiteClassiques = root.findViewById(R.id.quantiteClassiques)
        quantiteExtravagants = root.findViewById(R.id.quantiteExtravagants)
        quantiteCafes = root.findViewById(R.id.quantiteCafes)
        quantiteThes = root.findViewById(R.id.quantiteThes)

        displayQuantites()

        return root
    }

    fun displayQuantites() {
        val quantites = listOf(28, 3, 5, 7, 6, 4, 6, 4)
        var quantiteTotal = 0
        for (quantite in quantites) {
            quantiteTotal += quantite
        }
        quantiteSirops.text = quantites[0].toString()
        quantiteSofts.text = quantites[1].toString()
        quantiteBieres.text = quantites[2].toString()
        quantiteVins.text = quantites[3].toString()
        quantiteClassiques.text = quantites[4].toString()
        quantiteExtravagants.text = quantites[5].toString()
        quantiteCafes.text = quantites[6].toString()
        quantiteThes.text = quantites[7].toString()
        quantiteBoissons.text = "$quantiteTotal boissons dispos."
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonHome2Sirops.setOnClickListener {
            //findNavController().popBackStack(R.id.homeFragment, false)
            findNavController().navigate(R.id.softsFragment)
        }

        buttonHome2Softs.setOnClickListener {
            //findNavController().popBackStack(R.id.homeFragment, false)
            findNavController().navigate(R.id.softsFragment)
        }

        buttonHome2Cafes.setOnClickListener {
            //findNavController().popBackStack(R.id.homeFragment, false)
            findNavController().navigate(R.id.softsFragment)
        }

        buttonHome2Thes.setOnClickListener {
            //findNavController().popBackStack(R.id.homeFragment, false)
            findNavController().navigate(R.id.softsFragment)
        }

        buttonHome2Bieres.setOnClickListener {
            //findNavController().popBackStack(R.id.homeFragment, false)
            findNavController().navigate(R.id.alcoolFragment)
        }

        buttonHome2Vins.setOnClickListener {
            //findNavController().popBackStack(R.id.homeFragment, false)
            findNavController().navigate(R.id.alcoolFragment)
        }

        buttonHome2Classiques.setOnClickListener {
            //findNavController().popBackStack(R.id.homeFragment, false)
            findNavController().navigate(R.id.alcoolFragment)
        }

        buttonHome2Extravagants.setOnClickListener {
            //findNavController().popBackStack(R.id.homeFragment, false)
            findNavController().navigate(R.id.alcoolFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}