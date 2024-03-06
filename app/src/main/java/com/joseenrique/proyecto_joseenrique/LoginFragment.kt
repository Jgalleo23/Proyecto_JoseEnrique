package com.joseenrique.proyecto_joseenrique

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat.getSystemService
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.joseenrique.proyecto_joseenrique.data.Usuario.UsuarioIntroFragment
import com.joseenrique.proyecto_joseenrique.data.dataStore.UserPreferences
import com.joseenrique.proyecto_joseenrique.databinding.FragmentLoginBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

val Context.userDataStore by preferencesDataStore(name = "USER_PREFERENCES")

var accion = R.id.action_loginFragment_to_usuarioIntroFragment

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.app_name)

        binding.btIniciarSesion.setOnClickListener {
            val usuario = binding.etUser.text.toString()
            val contrasena = binding.etContrasena.text.toString()
            esconderTeclado()

            lifecycleScope.launch(Dispatchers.IO) {
                tomarDatos().collect {
                    withContext(Dispatchers.Main) {
                        if (it.introduccion == true) {
                            accion = R.id.action_loginFragment_to_menuFragment
                        } else {
                            accion = R.id.action_loginFragment_to_usuarioIntroFragment
                        }
                    }
                }
            }

            if (binding.etUser.text.toString().isEmpty() || binding.etContrasena.text.toString()
                    .isEmpty()
            ) {
                binding.etUser.error = "Campo vacío"
                binding.etContrasena.error = "Campo vacío"
                return@setOnClickListener
            } else {
                findNavController().navigate(accion)

                /*val saveUsername =
                    requireContext().getSharedPreferences("savedUsername", Context.MODE_PRIVATE)
                saveUsername.edit().putString("username", usuario).apply()*/
                lifecycleScope.launch(Dispatchers.IO) {
                    guardarUsuario(usuario, contrasena)
                }
            }
        }

        binding.swTheme.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }

        return binding.root
    }

    private suspend fun guardarUsuario(usuario: String, contrasena: String) {
        context?.userDataStore?.edit { preferences ->
            preferences[stringPreferencesKey("nombre")] = usuario
            preferences[stringPreferencesKey("contrasena")] = contrasena
        }
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

    private fun esconderTeclado() {
        val imm = getSystemService(requireContext(), InputMethodManager::class.java)
        imm?.hideSoftInputFromWindow(binding.etUser.windowToken, 0)
    }


}