package com.example.gabinsbarv2.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.gabinsbarv2.R
import com.example.gabinsbarv2.databinding.FragmentAlcoolBinding
import com.example.gabinsbarv2.AlcoolAdapter
import dataClassAlcool
import com.example.gabinsbarv2.BieresAdapter
import dataClassBieres
import com.example.gabinsbarv2.VinsAdapter
import dataClassVins

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

    private val boissonsListBieres = mutableListOf<dataClassBieres>()
    private val boissonsListVins = mutableListOf<dataClassVins>()
    private val boissonsListClassiques = mutableListOf<dataClassAlcool>()
    private val boissonsListExtravagants = mutableListOf<dataClassAlcool>()
    private lateinit var boissonAdapterBieres: BieresAdapter
    private lateinit var boissonAdapterVins: VinsAdapter
    private lateinit var boissonAdapterClassiques: AlcoolAdapter
    private lateinit var boissonAdapterExtravagants: AlcoolAdapter

    var quantiteTotal = 0
    var requestCount = 0
    val TOTAL_REQUESTS = 4

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

        val recyclerViewBieres = root.findViewById<RecyclerView>(R.id.recyclerViewBieres)
        recyclerViewBieres.layoutManager = LinearLayoutManager(requireContext())
        boissonAdapterBieres = BieresAdapter(boissonsListBieres)
        recyclerViewBieres.adapter = boissonAdapterBieres

        val recyclerViewVins = root.findViewById<RecyclerView>(R.id.recyclerViewVins)
        recyclerViewVins.layoutManager = LinearLayoutManager(requireContext())
        boissonAdapterVins = VinsAdapter(boissonsListVins)
        recyclerViewVins.adapter = boissonAdapterVins

        val recyclerViewClassiques = root.findViewById<RecyclerView>(R.id.recyclerViewClassiques)
        recyclerViewClassiques.layoutManager = LinearLayoutManager(requireContext())
        boissonAdapterClassiques = AlcoolAdapter(boissonsListClassiques)
        recyclerViewClassiques.adapter = boissonAdapterClassiques

        val recyclerViewExtravagants = root.findViewById<RecyclerView>(R.id.recyclerViewExtravagants)
        recyclerViewExtravagants.layoutManager = LinearLayoutManager(requireContext())
        boissonAdapterExtravagants = AlcoolAdapter(boissonsListExtravagants)
        recyclerViewExtravagants.adapter = boissonAdapterExtravagants

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
        recupererBieres()
        recupererVins()
        recupererClassiques()
        recupererExtravagants()
    }

    private fun onRequestComplete() {
        requestCount++
        if (requestCount == TOTAL_REQUESTS) {
            quantiteBoissons.text = "$quantiteTotal boissons dispos."
            boissonAdapterBieres.notifyDataSetChanged()
            boissonAdapterVins.notifyDataSetChanged()
            boissonAdapterClassiques.notifyDataSetChanged()
            boissonAdapterExtravagants.notifyDataSetChanged()
        }
    }

    private fun recupererBieres() {
        val queue = Volley.newRequestQueue(requireContext())
        val url = "" // your url

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                val liste = response.split("|").map { item ->
                    val parts = item.split(";")
                    dataClassBieres(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5])
                }
                boissonsListBieres.addAll(liste)
                quantiteTotal += liste.size
                onRequestComplete()
            },
            {
                Toast.makeText(requireContext(), "Problème de récupération des bières", Toast.LENGTH_SHORT).show()
                onRequestComplete()
            })

        queue.add(stringRequest)
    }

    private fun recupererVins() {
        val queue = Volley.newRequestQueue(requireContext())
        val url = "" // your url

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                val liste = response.split("|").map { item ->
                    val parts = item.split(";")
                    dataClassVins(parts[0], parts[1], parts[2], parts[3], parts[4])
                }
                boissonsListVins.addAll(liste)
                quantiteTotal += liste.size
                onRequestComplete()
            },
            {
                Toast.makeText(requireContext(), "Problème de récupération des vins", Toast.LENGTH_SHORT).show()
                onRequestComplete()
            })

        queue.add(stringRequest)
    }

    private fun recupererClassiques() {
        val queue = Volley.newRequestQueue(requireContext())
        val url = "" // your url

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                val liste = response.split("|").map { item ->
                    val parts = item.split(";")
                    dataClassAlcool(parts[0], parts[1], parts[2])
                }
                boissonsListClassiques.addAll(liste)
                quantiteTotal += liste.size
                onRequestComplete()
            },
            {
                Toast.makeText(requireContext(), "Problème de récupération des classiques", Toast.LENGTH_SHORT).show()
                onRequestComplete()
            })

        queue.add(stringRequest)
    }

    private fun recupererExtravagants() {
        val queue = Volley.newRequestQueue(requireContext())
        val url = "" // your url

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                val liste = response.split("|").map { item ->
                    val parts = item.split(";")
                    dataClassAlcool(parts[0], parts[1], parts[2])
                }
                boissonsListExtravagants.addAll(liste)
                quantiteTotal += liste.size
                onRequestComplete()
            },
            {
                Toast.makeText(requireContext(), "Problème de récupération des extravagants", Toast.LENGTH_SHORT).show()
                onRequestComplete()
            })

        queue.add(stringRequest)
    }

}
