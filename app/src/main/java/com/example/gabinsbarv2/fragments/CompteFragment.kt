package com.example.gabinsbarv2.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.gabinsbarv2.R
import com.example.gabinsbarv2.databinding.FragmentCompteBinding
import org.json.JSONException
import org.json.JSONObject
import java.security.MessageDigest

class CompteFragment : Fragment() {
    private var _binding: FragmentCompteBinding? = null
    private val binding get() = _binding!!

    lateinit var editTextPseudo: EditText
    lateinit var editTextPassword: EditText
    lateinit var buttonLogin: Button
    lateinit var buttonLogout: Button
    lateinit var erreurSaisiePseudo: TextView
    lateinit var erreurSaisieMdp: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentCompteBinding.inflate(inflater, container, false)
        val root: View = binding.root

        editTextPseudo = root.findViewById(R.id.editTextPseudo)
        editTextPassword = root.findViewById(R.id.editTextPassword)
        buttonLogin = root.findViewById(R.id.buttonLogin)
        buttonLogout = root.findViewById(R.id.buttonLogout)
        erreurSaisiePseudo = root.findViewById(R.id.erreurSaisiePseudo)
        erreurSaisieMdp = root.findViewById(R.id.erreurSaisieMdp)

        buttonLogin.setOnClickListener {
            val pseudo = editTextPseudo.text.toString()
            val password = editTextPassword.text.toString()
            erreurSaisiePseudo.visibility = View.GONE
            erreurSaisieMdp.visibility = View.GONE

            if (pseudo.isNotEmpty() && password.isNotEmpty()) {
                val hashedPassword = hashSHA256(password)

                sendLoginRequest(pseudo, hashedPassword)
            } else {
                if (pseudo.isEmpty()) {
                    erreurSaisiePseudo.visibility = View.VISIBLE
                }
                if (password.isEmpty()) {
                    erreurSaisieMdp.visibility = View.VISIBLE
                }
            }
        }

        buttonLogout.setOnClickListener {
            SessionManager.isLoggedIn = false
            SessionManager.pseudo = ""
            Toast.makeText(requireContext(), "Vous êtes déconnecté.", Toast.LENGTH_SHORT).show()
        }

        return root
    }

    private fun hashSHA256(password: String): String {
        val bytes = MessageDigest.getInstance("SHA-256").digest(password.toByteArray())
        return bytes.joinToString("") { "%02x".format(it) }
    }

    private fun sendLoginRequest(pseudo: String, hashedPassword: String) {
        val url = "https://gabinserrurot.fr/Api_gabinsbar/connexionUtilisateur.php"
        val queue = Volley.newRequestQueue(requireContext())

        val urlWithParams = "$url?pseudo=$pseudo&mot_de_passe=$hashedPassword"

        val stringRequest = StringRequest(
            Request.Method.GET, urlWithParams,
            { response ->
                try {
                    val jsonResponse = JSONObject(response)
                    val status = jsonResponse.getString("status")
                    val message = jsonResponse.getString("message")

                    if (status == "success") {
                        Toast.makeText(requireContext(), "Connexion réussie!", Toast.LENGTH_SHORT).show()
                        SessionManager.isLoggedIn = true
                        SessionManager.pseudo = pseudo
                    } else {
                        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
                    }
                } catch (e: JSONException) {
                    Toast.makeText(requireContext(), "Erreur de format de réponse.", Toast.LENGTH_SHORT).show()
                }
            },
            { error ->
                Toast.makeText(requireContext(), "Erreur: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        )

        queue.add(stringRequest)
    }

}