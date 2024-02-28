package com.joseenrique.proyecto_joseenrique.data.Usuario

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.joseenrique.proyecto_joseenrique.R
import com.joseenrique.proyecto_joseenrique.data.Usuario.UsuarioProvider.Companion.listaUsuarios

class UsuarioAdapter(private var listaUsuario : List<Usuario>) : RecyclerView.Adapter<UsuarioViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsuarioViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return UsuarioViewHolder(layoutInflater.inflate(R.layout.fragment_detail_item, parent, false))
    }

    override fun getItemCount(): Int = listaUsuario.size

    override fun onBindViewHolder(holder: UsuarioViewHolder, position: Int) {
        val usuario = listaUsuario[position]
        holder.render(usuario)

        holder.itemView.setOnClickListener {
            Snackbar.make(it, "Esta es la mascota de ${usuario.nombre}", Snackbar.LENGTH_LONG).show()
        }
    }

    fun getFavoriteUsers(): List<Usuario> {
        return listaUsuarios.filter { it.isFavorite }
    }

    fun updateData(users: List<Usuario>) {
        listaUsuario = users
        notifyDataSetChanged()
    }
}