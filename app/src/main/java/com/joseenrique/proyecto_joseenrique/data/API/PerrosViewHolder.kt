package com.joseenrique.proyecto_joseenrique.data.API

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.joseenrique.proyecto_joseenrique.databinding.ItemPerroBinding
import com.squareup.picasso.Picasso

class PerrosViewHolder(view: View): RecyclerView.ViewHolder(view){

    private val binding = ItemPerroBinding.bind(view)
    fun bind(image: String){
        Picasso.get().load(image).into(binding.imagePerro)
    }
}