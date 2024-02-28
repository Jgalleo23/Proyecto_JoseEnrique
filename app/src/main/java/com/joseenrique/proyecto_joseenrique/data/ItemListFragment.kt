package com.joseenrique.proyecto_joseenrique.data

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.joseenrique.proyecto_joseenrique.R
import com.joseenrique.proyecto_joseenrique.data.Usuario.UsuarioAdapter
import com.joseenrique.proyecto_joseenrique.databinding.FragmentItemListBinding
import com.joseenrique.proyecto_joseenrique.databinding.FragmentMenuBinding

class ItemListFragment : Fragment() {

    private lateinit var usuarioAdapter: UsuarioAdapter

    private var _binding: FragmentItemListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentItemListBinding.inflate(inflater, container, false)
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.app_name)

        binding.recyclerFavoritos.layoutManager = LinearLayoutManager(context)

        usuarioAdapter = UsuarioAdapter(emptyList()) // Inicializa el adaptador con una lista vacía
        binding.recyclerFavoritos.adapter = usuarioAdapter

        return binding.root
    }

    override fun onResume() {
        super.onResume()

        // Aquí debes obtener la lista de usuarios favoritos y actualizar el adaptador
        val favoriteUsers = usuarioAdapter.getFavoriteUsers()
        usuarioAdapter.updateData(favoriteUsers)
    }
}