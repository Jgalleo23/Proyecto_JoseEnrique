package com.joseenrique.proyecto_joseenrique.data

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.joseenrique.proyecto_joseenrique.R

class UsuarioAdapter(private val listaUsuario : List<Usuario>, private val onClickListener: (Usuario) -> Unit) : RecyclerView.Adapter<UsuarioViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsuarioViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return UsuarioViewHolder(layoutInflater.inflate(R.layout.fragment_detail_item, parent, false))
    }

    override fun getItemCount(): Int {
        return listaUsuario.size
    }

    override fun onBindViewHolder(holder: UsuarioViewHolder, position: Int) {
        holder.render(listaUsuario[position], onClickListener)
    }
}