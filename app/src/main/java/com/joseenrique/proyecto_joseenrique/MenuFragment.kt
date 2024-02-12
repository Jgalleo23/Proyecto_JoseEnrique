package com.joseenrique.proyecto_joseenrique

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.joseenrique.proyecto_joseenrique.databinding.FragmentMenuBinding

class MenuFragment : Fragment() {
    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMenuBinding.inflate(inflater, container, false)

        binding.btCredit.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_creditsFragment)
        }

        binding.btSalir.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_loginFragment)
        }
        return binding.root
    }
}