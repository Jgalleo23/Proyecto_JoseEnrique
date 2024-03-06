package com.joseenrique.proyecto_joseenrique.data.API

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.joseenrique.proyecto_joseenrique.R

class PerrosAdapter(val images: List<String>): RecyclerView.Adapter<PerrosViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PerrosViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PerrosViewHolder(layoutInflater.inflate(R.layout.item_perro, parent, false))
    }

    override fun getItemCount(): Int = images.size

    override fun onBindViewHolder(holder: PerrosViewHolder, position: Int) {
        val item = images[position]
        holder.bind(item)
    }

}