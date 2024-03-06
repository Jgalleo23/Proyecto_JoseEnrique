package com.joseenrique.proyecto_joseenrique.data.Usuario

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.joseenrique.proyecto_joseenrique.databinding.FragmentDetailItemBinding
import com.squareup.picasso.Picasso

class UsuarioViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val binding = FragmentDetailItemBinding.bind(view)


    fun render(usuarioModel: Usuario) {
        binding.tvUsuarioTop.text = usuarioModel.nombre
        binding.tvUsuarioBottom.text = usuarioModel.nombre
        Glide.with(itemView.context).load(usuarioModel.foto).into(binding.ivPhoto)
        binding.chkLike.setOnCheckedChangeListener { _, isChecked ->
            usuarioModel.isFavorite = isChecked
        }
    }
}