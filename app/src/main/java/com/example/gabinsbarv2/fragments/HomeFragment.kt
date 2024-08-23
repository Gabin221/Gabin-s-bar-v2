package com.example.gabinsbarv2.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
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

        return root
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