package com.example.clarc.ui.india

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.clarc.MainActivity
import com.example.clarc.R
import com.google.android.material.textview.MaterialTextView
import kotlinx.android.synthetic.main.fragment_india.*

class IndiaViewHolder(inflater: LayoutInflater,parent: ViewGroup): RecyclerView.ViewHolder(inflater.inflate(
    R.layout.list_item,parent,false)) {
    private var name: MaterialTextView? = null
    private var time: MaterialTextView? = null
    private var confirmed: MaterialTextView? = null
    private var recovered: MaterialTextView? = null
    private var deceased: MaterialTextView? = null
    private var active: MaterialTextView? = null

    init {
        name = itemView.findViewById(R.id.name)
        time = itemView.findViewById(R.id.time)
        confirmed = itemView.findViewById(R.id.confirmed)
        recovered = itemView.findViewById(R.id.recovered)
        active = itemView.findViewById(R.id.active)
        deceased = itemView.findViewById(R.id.deceased)
    }

    fun bind(data : MainActivity.Data){
        name?.text = data.name
        time?.text = data.time
        confirmed?.text = data.confirmed
        recovered?.text = data.recovered
        active?.text = data.active
        deceased?.text = data.deceased
    }
}