package com.example.clarc.ui.global

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.clarc.R
import com.google.android.material.card.MaterialCardView
import com.google.android.material.textview.MaterialTextView
import kotlinx.android.synthetic.main.fragment_global.*

class GlobalFragment : Fragment() {

    private lateinit var globalConfirmed: MaterialTextView
    private lateinit var globalActive: MaterialTextView
    private lateinit var globalRecovered: MaterialTextView
    private lateinit var globalDeceased: MaterialTextView

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_global, container, false)
        globalConfirmed = root.findViewById(R.id.globalConfirmed)
        globalActive = root.findViewById(R.id.globalActive)
        globalRecovered = root.findViewById(R.id.globalRecovered)
        globalDeceased = root.findViewById(R.id.globalDeceased)
        globalConfirmed.text = "Confirmed\n0"
        globalActive.text = "Active\n0"
        globalRecovered.text = "Recovered\n0"
        globalDeceased.text = "Deceased\n0"
        return root
    }
}