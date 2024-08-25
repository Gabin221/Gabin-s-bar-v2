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
        }
    }

    private fun recupererSirops() {
        val queue = Volley.newRequestQueue(requireContext())
        val url = "https://gabinserrurot.fr/Api_gabinsbar/recupererSirops.php"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                val liste = response.split(";")
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
        val url = "https://gabinserrurot.fr/Api_gabinsbar/recupererSofts.php"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                val liste = response.split(";")
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
        val url = "https://gabinserrurot.fr/Api_gabinsbar/recupererCafes.php"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                val liste = response.split(";")
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
        val url = "https://gabinserrurot.fr/Api_gabinsbar/recupererThes.php"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                val liste = response.split(";")
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