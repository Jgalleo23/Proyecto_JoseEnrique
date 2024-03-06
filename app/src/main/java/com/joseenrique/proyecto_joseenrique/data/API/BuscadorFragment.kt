package com.joseenrique.proyecto_joseenrique.data.API

import android.annotation.SuppressLint
import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.core.content.ContextCompat.getSystemService
import androidx.recyclerview.widget.LinearLayoutManager
import com.joseenrique.proyecto_joseenrique.R
import com.joseenrique.proyecto_joseenrique.databinding.FragmentBuscadorBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Locale

class BuscadorFragment : Fragment(), SearchView.OnQueryTextListener {
    private var _binding: FragmentBuscadorBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: PerrosAdapter

    val dogImages = mutableListOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentBuscadorBinding.inflate(inflater, container, false)
        binding.svBuscador.setOnQueryTextListener(this)
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.buscador)
        inciarRecycler()
        return binding.root
    }

    private fun inciarRecycler() {
        adapter = PerrosAdapter(dogImages)
        binding.recyclerPerros.layoutManager = LinearLayoutManager(this.context)
        binding.recyclerPerros.adapter = adapter
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/breed/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun buscarPorNomrbe(query: String) {
            CoroutineScope(Dispatchers.IO).launch {
                val llamada =
                    getRetrofit().create(APIService::class.java).getPerrosPorRaza("$query/images")
                val perros = llamada.body()
                withContext(Dispatchers.Main) {
                    if (llamada.isSuccessful) {
                        val listaPerros = perros?.images ?: emptyList()
                        dogImages.clear()
                        dogImages.addAll(listaPerros)
                        adapter.notifyDataSetChanged()
                    } else {
                        Toast.makeText(context, "Error en la busqueda", Toast.LENGTH_SHORT).show()
                    }

                    esconderTeclado()
                }
            }

    }

    private fun esconderTeclado() {
        val imm = getSystemService(requireContext(), InputMethodManager::class.java)
        imm?.hideSoftInputFromWindow(binding.svBuscador.windowToken, 0)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (!query.isNullOrEmpty()) {
            buscarPorNomrbe(query.toLowerCase())
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }
}