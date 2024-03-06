package com.joseenrique.proyecto_joseenrique.data.Usuario


import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.joseenrique.proyecto_joseenrique.CreditsFragment
import com.joseenrique.proyecto_joseenrique.R
import com.joseenrique.proyecto_joseenrique.data.FavItemFragment
import com.joseenrique.proyecto_joseenrique.data.dataStore.UserPreferences
import com.joseenrique.proyecto_joseenrique.databinding.FragmentCreditsBinding
import com.joseenrique.proyecto_joseenrique.databinding.FragmentFavItemBinding
import com.joseenrique.proyecto_joseenrique.databinding.FragmentLoginBinding
import com.joseenrique.proyecto_joseenrique.databinding.FragmentUserInfoBinding
import com.joseenrique.proyecto_joseenrique.userDataStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class UserInfoFragment : Fragment() {
    private var _binding: FragmentUserInfoBinding? = null
    private val binding get() = _binding!!

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserInfoBinding.inflate(inflater, container, false)
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.app_name)
        binding.vpNotice.adapter = NoticeAdapter(this)

        TabLayoutMediator(binding.tabMover, binding.vpNotice) { tab, position ->
            tab.text = when (position) {
                0 -> "Perfil"
                else -> "Creditos"
            }
        }.attach()

        lifecycleScope.launch(Dispatchers.IO) {
            tomarDatos().collect {
                withContext(Dispatchers.Main) {
                    FragmentFavItemBinding.bind(binding.root).tvUsuario.text = it.nombre
                    FragmentFavItemBinding.bind(binding.root).tvUsuarioDescripcion.text =
                        it.descripcion
                    FragmentFavItemBinding.bind(binding.root).tvSexo.text = it.sexo
                    FragmentFavItemBinding.bind(binding.root).tvRazaMascota.text = it.raza
                }
            }
        }

        return binding.root
    }

    private fun tomarDatos() = binding.root.context.userDataStore.data.map { preferences ->
        UserPreferences(
            nombre = preferences[stringPreferencesKey("nombre")].orEmpty(),
            contrasena = preferences[stringPreferencesKey("contrasena")].orEmpty(),
            introduccion = preferences[booleanPreferencesKey("introduccion")] ?: false,
            descripcion = preferences[stringPreferencesKey("descripcion")].orEmpty(),
            sexo = preferences[stringPreferencesKey("sexo")].orEmpty(),
            raza = preferences[stringPreferencesKey("raza")].orEmpty()
        )
    }

    class NoticeAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
        override fun getItemCount(): Int = 2

        override fun createFragment(position: Int): Fragment {
            val fragment = if (position == 0) FavItemFragment()
            else CreditsFragment()

            return fragment
        }
    }
}