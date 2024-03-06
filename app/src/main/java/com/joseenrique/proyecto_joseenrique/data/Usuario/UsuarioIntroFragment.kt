package com.joseenrique.proyecto_joseenrique.data.Usuario

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.joseenrique.proyecto_joseenrique.R
import com.joseenrique.proyecto_joseenrique.data.dataStore.UserPreferences
import com.joseenrique.proyecto_joseenrique.databinding.FragmentFavItemBinding
import com.joseenrique.proyecto_joseenrique.databinding.FragmentLoginBinding
import com.joseenrique.proyecto_joseenrique.databinding.FragmentUsuarioIntroBinding
import com.joseenrique.proyecto_joseenrique.userDataStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UsuarioIntroFragment : Fragment() {
    private lateinit var _binding: FragmentUsuarioIntroBinding
    private val binding get() = _binding!!

    private var accion = R.id.action_usuarioIntroFragment_to_menuFragment
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUsuarioIntroBinding.inflate(inflater, container, false)
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.intro)

        binding.btGuardarPreferencias.setOnClickListener() {
            findNavController().navigate(accion)
            lifecycleScope.launch(Dispatchers.IO) {
                guardarUsuario(
                    cheked = binding.chkRecordar.isChecked,
                    usuario = binding.etUsuario.text.toString(),
                    descripcion = binding.etDescripcion.text.toString(),
                    sexo = binding.etSexo.text.toString(),
                    raza = binding.etRaza.text.toString()
                )
            }
        }

        lifecycleScope.launch(Dispatchers.IO) {
            tomarDatos().collect {
                withContext(Dispatchers.Main) {
                    binding.etUsuario.setText(it.nombre)
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

    private suspend fun guardarUsuario(
        usuario: String,
        cheked: Boolean,
        descripcion: String,
        sexo: String,
        raza: String
    ) {
        context?.userDataStore?.edit { preferences ->
            preferences[booleanPreferencesKey("introduccion")] = cheked
            preferences[stringPreferencesKey("nombre")] = usuario
            preferences[stringPreferencesKey("descripcion")] = descripcion
            preferences[stringPreferencesKey("sexo")] = sexo
            preferences[stringPreferencesKey("raza")] = raza
        }
    }

}