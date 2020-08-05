package com.example.clarc.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.clarc.MainActivity
import com.example.clarc.ui.india.IndiaViewHolder

class ListAdapter(private val list: List<MainActivity.Data>): RecyclerView.Adapter<IndiaViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IndiaViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return IndiaViewHolder(inflater,parent)
    }

    override fun onBindViewHolder(holder: IndiaViewHolder, position: Int) {
        val data = list[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = list.size
}