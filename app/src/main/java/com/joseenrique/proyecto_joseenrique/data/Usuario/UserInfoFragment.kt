package com.joseenrique.proyecto_joseenrique.data.Usuario

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.joseenrique.proyecto_joseenrique.CreditsFragment
import com.joseenrique.proyecto_joseenrique.R
import com.joseenrique.proyecto_joseenrique.data.FavItemFragment
import com.joseenrique.proyecto_joseenrique.databinding.FragmentCreditsBinding
import com.joseenrique.proyecto_joseenrique.databinding.FragmentMenuBinding
import com.joseenrique.proyecto_joseenrique.databinding.FragmentUserInfoBinding

class UserInfoFragment : Fragment() {
    private var _binding: FragmentUserInfoBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUserInfoBinding.inflate(inflater, container, false)
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.app_name)
        binding.vpNotice.adapter = NoticeAdapter(this)

// Now you can attach your TabLayoutMediator
        TabLayoutMediator(binding.tabMover, binding.vpNotice) { tab, position ->
            tab.text = when (position) {
                0 -> "Perfil"
                else -> "Creditos"
            }
        }.attach()

        return binding.root
    }

    class NoticeAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

        //NUMERO DE ELEMENTOS O FRAGMENTOS A CARGAR. EN ESTE CASO:2.
        //PODRÍA TOMAR LOS VALORES DEL TAMAÑO DE UNA LISTA, POR EJEMPLO.
        override fun getItemCount(): Int = 2

        //FUNCIÓN PARA CREAR LOS FRAGMENTOS (ASOCIAR). SEGÚN LA POSICIÓN RETORNARÁ UN FRAGMENTO U OTRO.
        //SE PODRÍA AUTOMATIZAR Y QUE SE LLAME A UN MISMO FRAGMENTO AL QUE LE LLEGAN DATOS QUE VARÍAN.
        override fun createFragment(position: Int): Fragment {
            // Return a NEW fragment instance in createFragment(int)
            val fragment = if (position == 0) FavItemFragment()
            else CreditsFragment()

            return fragment
        }
    }
}