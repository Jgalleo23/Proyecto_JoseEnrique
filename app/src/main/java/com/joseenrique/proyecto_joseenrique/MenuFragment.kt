package com.joseenrique.proyecto_joseenrique

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.joseenrique.proyecto_joseenrique.data.API.APIService
import com.joseenrique.proyecto_joseenrique.data.Usuario.UsuarioAdapter
import com.joseenrique.proyecto_joseenrique.data.Usuario.UsuarioProvider
import com.joseenrique.proyecto_joseenrique.databinding.FragmentMenuBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Locale

class MenuFragment : Fragment() {
    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.app_name)
        inciarRecycler()
        return binding.root
    }

    private fun inciarRecycler() {
        binding.recyclerPublicaciones.layoutManager = LinearLayoutManager(this.context)
        binding.recyclerPublicaciones.adapter = UsuarioAdapter(UsuarioProvider.listaUsuarios)
    }
}