package com.example.gabinsbarv2.fragments

import PanierManager
import PanierManager.monPanier
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.gabinsbarv2.PanierAdapter
import com.example.gabinsbarv2.databinding.FragmentPanierBinding
import com.example.gabinsbarv2.R
import org.json.JSONObject
import java.net.URLEncoder

class PanierFragment : Fragment() {
    private var _binding: FragmentPanierBinding? = null
    private val binding get() = _binding!!

    private lateinit var boissonAdapterPanier: PanierAdapter
    private lateinit var viderPanierButton: Button
    private lateinit var commanderButton: Button
    private var lastClickTime: Long = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentPanierBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val recyclerViewPanier = root.findViewById<RecyclerView>(R.id.recyclerViewPanier)
        commanderButton = root.findViewById(R.id.commanderButton)
        viderPanierButton = root.findViewById(R.id.viderPanierButton)

        recyclerViewPanier.layoutManager = LinearLayoutManager(requireContext())

        boissonAdapterPanier = PanierAdapter(monPanier) { position ->
            PanierManager.supprimerElement(position)
            boissonAdapterPanier.notifyItemRemoved(position)
            boissonAdapterPanier.notifyItemRangeChanged(position, monPanier.size)
            updateCommanderButtonState()
        }

        updateCommanderButtonState()

        recyclerViewPanier.adapter = boissonAdapterPanier
        viderPanierButton.setOnClickListener {
            PanierManager.supprimerTout()
            boissonAdapterPanier.notifyDataSetChanged()
            updateCommanderButtonState()
        }

        commanderButton.setOnClickListener {
            val currentTime = System.currentTimeMillis()
            if (currentTime - lastClickTime > 3000) {

                commanderButton.isEnabled = false

                if (SessionManager.isLoggedIn) {
                    try {
                        val commande = monPanier.joinToString(separator = "\n    - ")
                        val texte = "${SessionManager.pseudo} aimerait bien:\n    - $commande"
                        val texteEncode = URLEncoder.encode(texte, "UTF-8")
                        val url = "use/your/url&text=${texteEncode}"

                        val params = JSONObject()

                        val request = JsonObjectRequest(
                            Request.Method.POST, url, params,
                            { response ->
                                response.toString()
                            },
                            { error ->
                                error.message
                            }
                        )

                        Volley.newRequestQueue(context).add(request)

                        val message = "La commande a bien été envoyée"
                        Toast.makeText(
                            requireContext(),
                            message,
                            Toast.LENGTH_SHORT
                        ).show()
                        PanierManager.supprimerTout()
                        boissonAdapterPanier.notifyDataSetChanged()
                        updateCommanderButtonState()
                    } catch (e: Exception) {
                        val message =
                            "Erreur lors de l'envoi de la commande: " + e.message
                        Toast.makeText(
                            requireContext(),
                            message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    Toast.makeText(requireContext(), "Vous devez vous connecter pour passer commande", Toast.LENGTH_SHORT).show()
                }

                Handler(Looper.getMainLooper()).postDelayed({
                    updateCommanderButtonState()
                }, 3000)
            }

            lastClickTime = currentTime
        }

        return root
    }

    private fun updateCommanderButtonState() {
        commanderButton.isEnabled = monPanier.isNotEmpty()
        viderPanierButton.isEnabled = monPanier.isNotEmpty()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
