package com.joseenrique.proyecto_joseenrique

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.joseenrique.proyecto_joseenrique.data.Usuario.UsuarioAdapter
import com.joseenrique.proyecto_joseenrique.data.Usuario.UsuarioProvider
import com.joseenrique.proyecto_joseenrique.databinding.FragmentMenuBinding

class MenuFragment : Fragment() {
    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.app_name)

/**val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.options_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.perfil -> {
                        findNavController().navigate(R.id.action_menuFragment_to_userInfoFragment)
                        true
                    }
                    R.id.favoritos -> {
                        findNavController().navigate(R.id.action_menuFragment_to_itemListFragment)
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)*/

        inciarRecycler()
        return binding.root
    }

    private fun inciarRecycler(){
        binding.recyclerPublicaciones.layoutManager = LinearLayoutManager(this.context)
        binding.recyclerPublicaciones.adapter = UsuarioAdapter(UsuarioProvider.listaUsuarios)
    }

}