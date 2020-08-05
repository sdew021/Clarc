package com.example.clarc.ui.india

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.clarc.R
import com.google.android.material.textview.MaterialTextView
import org.json.JSONObject

class IndiaFragment : Fragment() {

    private lateinit var indiaConfirmed: MaterialTextView
    private lateinit var indiaActive: MaterialTextView
    private lateinit var indiaRecovered: MaterialTextView
    private lateinit var indiaDeceased: MaterialTextView
    private lateinit var indiaText: MaterialTextView

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_india, container, false)
        indiaConfirmed = root.findViewById(R.id.indiaConfirmed)
        indiaActive = root.findViewById(R.id.indiaActive)
        indiaRecovered = root.findViewById(R.id.indiaRecovered)
        indiaDeceased = root.findViewById(R.id.indiaDeceased)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val queue = Volley.newRequestQueue(requireContext())
        val url = "https://api.covid19india.org/data.json"
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> { response ->
                // Display the first 500 characters of the response string.
                val res = JSONObject(response).getJSONArray("statewise")
                res.getJSONObject(0).get("active")
                indiaConfirmed.text = indiaConfirmed.text.toString() + "\n" + res.getJSONObject(0).get("confirmed").toString()
                indiaActive.text = indiaActive.text.toString() + "\n" + res.getJSONObject(0).get("active").toString()
                indiaRecovered.text = indiaRecovered.text.toString() + "\n" + res.getJSONObject(0).get("recovered").toString()
                indiaDeceased.text = indiaDeceased.text.toString() + "\n" + res.getJSONObject(0).get("deaths").toString()
            },
            Response.ErrorListener { Log.d("Resp Error India", "onViewCreated: That didn't work!") })

        queue.add(stringRequest)

    }
}