package com.joseenrique.proyecto_joseenrique.data

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.joseenrique.proyecto_joseenrique.R
import com.joseenrique.proyecto_joseenrique.databinding.FragmentFavItemBinding
import com.joseenrique.proyecto_joseenrique.databinding.FragmentUserInfoBinding

class FavItemFragment : Fragment() {
    private var _binding: FragmentFavItemBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFavItemBinding.inflate(inflater, container, false)
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.app_name)

        //llenar los campos con la informacion del usuario
        val savedUsername =
            requireContext().getSharedPreferences("savedUsername", Context.MODE_PRIVATE)
        val username = savedUsername.getString("username", "")
        val email = savedUsername.getString("email", "")
        val password = savedUsername.getString("password", "")
        val age = savedUsername.getString("age", "")

        binding.tvUsuario.setText(username)
        return binding.root
    }
}