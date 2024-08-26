package com.example.gabinsbarv2.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
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

    var quantiteTotal = 0
    var requestCount = 0
    val TOTAL_REQUESTS = 8

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
        recupererSirops()
        recupererSofts()
        recupererBieres()
        recupererVins()
        recupererClassiques()
        recupererExtravagants()
        recupererCafes()
        recupererThes()
    }

    private fun onRequestComplete() {
        requestCount++
        if (requestCount == TOTAL_REQUESTS) {
            quantiteBoissons.text = "$quantiteTotal boissons dispos."
        }
    }

    private fun recupererSirops() {
        val queue = Volley.newRequestQueue(requireContext())
        val url = "" // your url

        val stringRequest = StringRequest(Request.Method.GET, url,
            { response ->
                val liste = response.split("|")
                quantiteSirops.text = liste.size.toString()
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
        val url = "" // your url

        val stringRequest = StringRequest(Request.Method.GET, url,
            { response ->
                val liste = response.split("|")
                quantiteSofts.text = liste.size.toString()
                quantiteTotal += liste.size
                onRequestComplete()
            },
            {
                Toast.makeText(requireContext(), "Problème de récupération des softs", Toast.LENGTH_SHORT).show()
                onRequestComplete()
            })

        queue.add(stringRequest)
    }

    private fun recupererBieres() {
        val queue = Volley.newRequestQueue(requireContext())
        val url = "" // your url

        val stringRequest = StringRequest(Request.Method.GET, url,
            { response ->
                val liste = response.split("|")
                quantiteBieres.text = liste.size.toString()
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

        val stringRequest = StringRequest(Request.Method.GET, url,
            { response ->
                val liste = response.split("|")
                quantiteVins.text = liste.size.toString()
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

        val stringRequest = StringRequest(Request.Method.GET, url,
            { response ->
                val liste = response.split("|")
                quantiteClassiques.text = liste.size.toString()
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

        val stringRequest = StringRequest(Request.Method.GET, url,
            { response ->
                val liste = response.split("|")
                quantiteExtravagants.text = liste.size.toString()
                quantiteTotal += liste.size
                onRequestComplete()
            },
            {
                Toast.makeText(requireContext(), "Problème de récupération des extravagants", Toast.LENGTH_SHORT).show()
                onRequestComplete()
            })

        queue.add(stringRequest)
    }

    private fun recupererCafes() {
        val queue = Volley.newRequestQueue(requireContext())
        val url = "" // your url

        val stringRequest = StringRequest(Request.Method.GET, url,
            { response ->
                val liste = response.split("|")
                quantiteCafes.text = liste.size.toString()
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
        val url = "" // your url

        val stringRequest = StringRequest(Request.Method.GET, url,
            { response ->
                val liste = response.split("|")
                quantiteThes.text = liste.size.toString()
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
