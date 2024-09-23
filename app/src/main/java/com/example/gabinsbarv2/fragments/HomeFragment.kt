package com.example.gabinsbarv2.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.gabinsbarv2.R
import com.example.gabinsbarv2.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    lateinit var buttonHome2Suggestions: LinearLayout
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
    lateinit var pseudo_utilisateur: TextView

    var quantiteTotal = 0
    var quantiteTotaleSirops = 0
    var quantiteTotaleSofts = 0
    var quantiteTotaleBieres = 0
    var quantiteTotaleVins = 0
    var quantiteTotaleClassiques = 0
    var quantiteTotaleExtravagants = 0
    var quantiteTotaleCafes = 0
    var quantiteTotaleThes = 0

    var requestCount = 0
    val TOTAL_REQUESTS = 8
    var dataLoaded = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        if (savedInstanceState != null) {
            quantiteTotal = savedInstanceState.getInt("quantiteTotal", 0)
            requestCount = savedInstanceState.getInt("requestCount", 0)
            dataLoaded = savedInstanceState.getBoolean("dataLoaded", false)
        }

        buttonHome2Suggestions = root.findViewById(R.id.buttonHome2Suggestions)
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
        pseudo_utilisateur = root.findViewById(R.id.pseudo_utilisateur)

        if (SessionManager.pseudo != "") {
            pseudo_utilisateur.text = SessionManager.pseudo
        }

        if (!dataLoaded) {
            displayQuantites()
        } else {
            quantiteBoissons.text = "$quantiteTotal boissons dispos."
            quantiteSirops.text = quantiteTotaleSirops.toString()
            quantiteSofts.text = quantiteTotaleSofts.toString()
            quantiteBieres.text = quantiteTotaleBieres.toString()
            quantiteVins.text = quantiteTotaleVins.toString()
            quantiteClassiques.text = quantiteTotaleClassiques.toString()
            quantiteExtravagants.text = quantiteTotaleExtravagants.toString()
            quantiteCafes.text = quantiteTotaleCafes.toString()
            quantiteThes.text = quantiteTotaleThes.toString()
        }

        buttonHome2Sirops.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("category", "Sirops")
            findNavController().navigate(R.id.softsFragment, bundle)
        }
        buttonHome2Softs.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("category", "Softs")
            findNavController().navigate(R.id.softsFragment, bundle)
        }
        buttonHome2Bieres.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("category", "Bieres")
            findNavController().navigate(R.id.alcoolFragment, bundle)
        }
        buttonHome2Vins.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("category", "Vins")
            findNavController().navigate(R.id.alcoolFragment, bundle)
        }
        buttonHome2Classiques.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("category", "Classiques")
            findNavController().navigate(R.id.alcoolFragment, bundle)
        }
        buttonHome2Extravagants.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("category", "Extravagants")
            findNavController().navigate(R.id.alcoolFragment, bundle)
        }
        buttonHome2Cafes.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("category", "Cafes")
            findNavController().navigate(R.id.softsFragment, bundle)
        }
        buttonHome2Thes.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("category", "Thes")
            findNavController().navigate(R.id.softsFragment, bundle)
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buttonHome2Suggestions.setOnClickListener {
            findNavController().popBackStack(R.id.homeFragment, false)
            findNavController().navigate(R.id.suggestionsFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun displayQuantites() {
        dataLoaded = false

        quantiteTotal = 0
        requestCount = 0

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
            dataLoaded = true
        }
    }

    private fun recupererSirops() {
        val queue = Volley.newRequestQueue(requireContext())
        val url = "use/your/url"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                val liste = response.split("|")
                quantiteSirops.text = liste.size.toString()
                quantiteTotaleSirops += liste.size
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
        val url = "use/your/url"

        val stringRequest = StringRequest(Request.Method.GET, url,
            { response ->
                val liste = response.split("|")
                quantiteSofts.text = liste.size.toString()
                quantiteTotaleSofts += liste.size
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
        val url = "use/your/url"

        val stringRequest = StringRequest(Request.Method.GET, url,
            { response ->
                val liste = response.split("|")
                quantiteBieres.text = liste.size.toString()
                quantiteTotaleBieres += liste.size
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
        val url = "use/your/url"

        val stringRequest = StringRequest(Request.Method.GET, url,
            { response ->
                val liste = response.split("|")
                quantiteVins.text = liste.size.toString()
                quantiteTotaleVins += liste.size
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
        val url = "use/your/url"

        val stringRequest = StringRequest(Request.Method.GET, url,
            { response ->
                val liste = response.split("|")
                quantiteClassiques.text = liste.size.toString()
                quantiteTotaleClassiques += liste.size
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
        val url = "use/your/url"

        val stringRequest = StringRequest(Request.Method.GET, url,
            { response ->
                val liste = response.split("|")
                quantiteExtravagants.text = liste.size.toString()
                quantiteTotaleExtravagants += liste.size
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
        val url = "use/your/url"

        val stringRequest = StringRequest(Request.Method.GET, url,
            { response ->
                val liste = response.split("|")
                quantiteCafes.text = liste.size.toString()
                quantiteTotaleCafes += liste.size
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
        val url = "use/your/url"

        val stringRequest = StringRequest(Request.Method.GET, url,
            { response ->
                val liste = response.split("|")
                quantiteThes.text = liste.size.toString()
                quantiteTotaleThes += liste.size
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
