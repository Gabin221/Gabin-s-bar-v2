package com.example.gabinsbarv2.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.gabinsbarv2.R
import com.example.gabinsbarv2.SuggestionBiere
import com.example.gabinsbarv2.databinding.FragmentSuggestionsBinding

class SuggestionsFragment : Fragment() {
    private var _binding: FragmentSuggestionsBinding? = null
    private val binding get() = _binding!!

    lateinit var suggestionSirops: TextView
    lateinit var suggestionSofts: TextView
    lateinit var suggestionBieres: TextView
    lateinit var suggestionVins: TextView
    lateinit var suggestionClassiques: TextView
    lateinit var suggestionExtravagants: TextView
    lateinit var typeBiere: TextView

    private val boissonsListSirops = mutableListOf<String>()
    private val boissonsListSofts = mutableListOf<String>()
    private val boissonsListBieres = mutableListOf<SuggestionBiere>()
    private val boissonsListVins = mutableListOf<String>()
    private val boissonsListClassiques = mutableListOf<String>()
    private val boissonsListExtravagants = mutableListOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentSuggestionsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        suggestionSirops = root.findViewById(R.id.suggestionSirops)
        suggestionSofts = root.findViewById(R.id.suggestionSofts)
        suggestionBieres = root.findViewById(R.id.suggestionBieres)
        suggestionVins = root.findViewById(R.id.suggestionVins)
        suggestionClassiques = root.findViewById(R.id.suggestionClassiques)
        suggestionExtravagants = root.findViewById(R.id.suggestionExtravagants)
        typeBiere = root.findViewById(R.id.typeBiere)

        displayQuantites()

        return root
    }

    fun displayQuantites() {
        recupererSirops()
        recupererSofts()
        recupererBieres()
        recupererVins()
        recupererClassiques()
        recupererExtravagants()
    }

    private fun recupererSirops() {
        val queue = Volley.newRequestQueue(requireContext())
        val url = "" // your URL

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                val liste = response.split("|").map { item ->
                    val parts = item.split(";")
                    boissonsListSirops.add(parts[0])
                }
                val randomSirop = mutableListOf(boissonsListSirops.random())
                suggestionSirops.text = randomSirop[0]
            },
            {
                Toast.makeText(requireContext(), "Problème de récupération des sirops", Toast.LENGTH_SHORT).show()
            })

        queue.add(stringRequest)
    }

    private fun recupererSofts() {
        val queue = Volley.newRequestQueue(requireContext())
        val url = "" // your URL

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                val liste = response.split("|").map { item ->
                    val parts = item.split(";")
                    boissonsListSofts.add(parts[0])
                }
                val randomSoft = mutableListOf(boissonsListSofts.random())
                suggestionSofts.text = randomSoft[0]
            },
            {
                Toast.makeText(requireContext(), "Problème de récupération des softs", Toast.LENGTH_SHORT).show()
            })

        queue.add(stringRequest)
    }

    private fun recupererBieres() {
        val queue = Volley.newRequestQueue(requireContext())
        val url = "" // your URL

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                boissonsListBieres.clear()

                val liste = response.split("|").map { item ->
                    val parts = item.split(";")
                    if (parts.size >= 2) {
                        val nomBiere = parts[0]
                        val typeService = parts[3]
                        if (typeService == "1") {
                            boissonsListBieres.add(SuggestionBiere(nom = nomBiere, typeService = "(bouteille)"))
                        } else {
                            boissonsListBieres.add(SuggestionBiere(nom = nomBiere, typeService = "(pression)"))
                        }
                    }
                }

                if (boissonsListBieres.isNotEmpty()) {
                    val randomBiere = boissonsListBieres.random()
                    suggestionBieres.text = randomBiere.nom
                    typeBiere.text = randomBiere.typeService // Afficher le type de service (Bouteille ou Pression)
                } else {
                    suggestionBieres.text = "Aucune bière disponible"
                    typeBiere.text = ""
                }
            },
            {
                Toast.makeText(requireContext(), "Problème de récupération des bières", Toast.LENGTH_SHORT).show()
            })

        queue.add(stringRequest)
    }

    private fun recupererVins() {
        val queue = Volley.newRequestQueue(requireContext())
        val url = "" // your URL

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                val liste = response.split("|").map { item ->
                    val parts = item.split(";")
                    boissonsListVins.add(parts[0])
                }
                val randomVin = mutableListOf(boissonsListVins.random())
                suggestionVins.text = randomVin[0]
            },
            {
                Toast.makeText(requireContext(), "Problème de récupération des vins", Toast.LENGTH_SHORT).show()
            })

        queue.add(stringRequest)
    }

    private fun recupererClassiques() {
        val queue = Volley.newRequestQueue(requireContext())
        val url = "" // your URL

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                val liste = response.split("|").map { item ->
                    val parts = item.split(";")
                    boissonsListClassiques.add(parts[0])
                }
                val randomClassique = mutableListOf(boissonsListClassiques.random())
                suggestionClassiques.text = randomClassique[0]
            },
            {
                Toast.makeText(requireContext(), "Problème de récupération des classiques", Toast.LENGTH_SHORT).show()
            })

        queue.add(stringRequest)
    }

    private fun recupererExtravagants() {
        val queue = Volley.newRequestQueue(requireContext())
        val url = "" // your URL

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                val liste = response.split("|").map { item ->
                    val parts = item.split(";")
                    boissonsListExtravagants.add(parts[0])
                }
                val randomExtravagant = mutableListOf(boissonsListExtravagants.random())
                suggestionExtravagants.text = randomExtravagant[0]
            },
            {
                Toast.makeText(requireContext(), "Problème de récupération des extravagants", Toast.LENGTH_SHORT).show()
            })

        queue.add(stringRequest)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
