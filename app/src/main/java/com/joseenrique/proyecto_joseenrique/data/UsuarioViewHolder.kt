package com.joseenrique.proyecto_joseenrique.data

import android.view.View
import androidx.recyclerview.widget.RecyclerView

class UsuarioViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val binding = DetailItemFragment.bind(view)

    fun render(usuarioModel: Usuario, onClickListener: (Usuario) -> Unit){

        itemView.setOnClickListener {
            onClickListener(usuarioModel)
        }
    }
}