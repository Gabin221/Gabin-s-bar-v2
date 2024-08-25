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
        }
    }

    private fun recupererBieres() {
        val queue = Volley.newRequestQueue(requireContext())
        val url = "https://gabinserrurot.fr/Api_gabinsbar/recupererBieres.php"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                val liste = response.split(";")
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
        val url = "https://gabinserrurot.fr/Api_gabinsbar/recupererVins.php"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                val liste = response.split(";")
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
        val url = "https://gabinserrurot.fr/Api_gabinsbar/recupererClassiques.php"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                val liste = response.split(";")
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
        val url = "https://gabinserrurot.fr/Api_gabinsbar/recupererExtravagants.php"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                val liste = response.split(";")
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