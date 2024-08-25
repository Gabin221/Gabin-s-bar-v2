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
import com.example.gabinsbarv2.BoissonAdapter
import com.example.gabinsbarv2.R
import com.example.gabinsbarv2.databinding.FragmentSoftsBinding
import dataClassSofts

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

    private val boissonsListSirops = mutableListOf<dataClassSofts>()
    private val boissonsListSofts = mutableListOf<dataClassSofts>()
    private val boissonsListCafes = mutableListOf<dataClassSofts>()
    private val boissonsListThes = mutableListOf<dataClassSofts>()
    private lateinit var boissonAdapterSirops: BoissonAdapter
    private lateinit var boissonAdapterSofts: BoissonAdapter
    private lateinit var boissonAdapterCafes: BoissonAdapter
    private lateinit var boissonAdapterThes: BoissonAdapter
    
    var quantiteTotal = 0
    var requestCount = 0
    val TOTAL_REQUESTS = 4

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

        val recyclerViewSirops = root.findViewById<RecyclerView>(R.id.recyclerViewSirops)
        recyclerViewSirops.layoutManager = LinearLayoutManager(requireContext())
        boissonAdapterSirops = BoissonAdapter(boissonsListSirops)
        recyclerViewSirops.adapter = boissonAdapterSirops

        val recyclerViewSofts = root.findViewById<RecyclerView>(R.id.recyclerViewSofts)
        recyclerViewSofts.layoutManager = LinearLayoutManager(requireContext())
        boissonAdapterSofts = BoissonAdapter(boissonsListSofts)
        recyclerViewSofts.adapter = boissonAdapterSofts

        val recyclerViewCafes = root.findViewById<RecyclerView>(R.id.recyclerViewCafes)
        recyclerViewCafes.layoutManager = LinearLayoutManager(requireContext())
        boissonAdapterCafes = BoissonAdapter(boissonsListCafes)
        recyclerViewCafes.adapter = boissonAdapterCafes

        val recyclerViewThes = root.findViewById<RecyclerView>(R.id.recyclerViewThes)
        recyclerViewThes.layoutManager = LinearLayoutManager(requireContext())
        boissonAdapterThes = BoissonAdapter(boissonsListThes)
        recyclerViewThes.adapter = boissonAdapterThes

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
        recupererSirops()
        recupererSofts()
        recupererCafes()
        recupererThes()
    }

    private fun onRequestComplete() {
        requestCount++
        if (requestCount == TOTAL_REQUESTS) {
            quantiteBoissons.text = "$quantiteTotal boissons dispos."
            boissonAdapterSirops.notifyDataSetChanged()
            boissonAdapterSofts.notifyDataSetChanged()
            boissonAdapterCafes.notifyDataSetChanged()
            boissonAdapterThes.notifyDataSetChanged()
        }
    }

    private fun recupererSirops() {
        val queue = Volley.newRequestQueue(requireContext())
        val url = "" // your URL

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                val liste = response.split(";").map { item ->
                    val parts = item.split("-")
                    dataClassSofts(parts[0], parts[1])
                }
                boissonsListSirops.addAll(liste)
                quantiteTotal += liste.size
                onRequestComplete()
            },
            {
                Toast.makeText(requireContext(), "Problème de récupération des sirops", Toast.LENGTH_SHORT).show()
                onRequestComplete()
            })

        queue.add(stringRequest)
    }

    private fun recupererSofts() {
        val queue = Volley.newRequestQueue(requireContext())
        val url = "" // your URL

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                val liste = response.split(";").map { item ->
                    val parts = item.split("-")
                    dataClassSofts(parts[0], parts[1])
                }
                boissonsListSofts.addAll(liste)
                quantiteTotal += liste.size
                onRequestComplete()
            },
            {
                Toast.makeText(requireContext(), "Problème de récupération des softs", Toast.LENGTH_SHORT).show()
                onRequestComplete()
            })

        queue.add(stringRequest)
    }

    private fun recupererCafes() {
        val queue = Volley.newRequestQueue(requireContext())
        val url = "" // your URL

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                val liste = response.split(";").map { item ->
                    val parts = item.split("-")
                    dataClassSofts(parts[0], parts[1])
                }
                boissonsListCafes.addAll(liste)
                quantiteTotal += liste.size
                onRequestComplete()
            },
            {
                Toast.makeText(requireContext(), "Problème de récupération des cafés", Toast.LENGTH_SHORT).show()
                onRequestComplete()
            })

        queue.add(stringRequest)
    }

    private fun recupererThes() {
        val queue = Volley.newRequestQueue(requireContext())
        val url = "" // your URL

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                val liste = response.split(";").map { item ->
                    val parts = item.split("-")
                    dataClassSofts(parts[0], parts[1])
                }
                boissonsListThes.addAll(liste)
                quantiteTotal += liste.size
                onRequestComplete()
            },
            {
                Toast.makeText(requireContext(), "Problème de récupération des thés", Toast.LENGTH_SHORT).show()
                onRequestComplete()
            })

        queue.add(stringRequest)
    }

}
