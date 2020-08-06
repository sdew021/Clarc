package com.example.clarc.ui.india

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.clarc.MainActivity
import com.example.clarc.R
import com.example.clarc.adapters.ListAdapter
import com.google.android.material.textview.MaterialTextView
import kotlinx.android.synthetic.main.fragment_india.*
import org.json.JSONArray
import org.json.JSONObject

class IndiaFragment : Fragment() {

    private lateinit var indiaConfirmed: MaterialTextView
    private lateinit var indiaActive: MaterialTextView
    private lateinit var indiaRecovered: MaterialTextView
    private lateinit var indiaDeceased: MaterialTextView
    private lateinit var indiaConfirmedDelta: MaterialTextView
    private lateinit var indiaRecoveredDelta: MaterialTextView
    private lateinit var indiaDeceasedDelta: MaterialTextView
    private lateinit var indiaText: MaterialTextView
    private lateinit var res:JSONArray

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
        indiaConfirmedDelta = root.findViewById(R.id.indiaConfirmedDelta)
        indiaRecoveredDelta = root.findViewById(R.id.indiaRecoveredDelta)
        indiaDeceasedDelta = root.findViewById(R.id.indiaDeceasedDelta)
        indiaText = root.findViewById(R.id.indiaText)
        res = JSONArray()
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
                res = JSONObject(response).getJSONArray("statewise")
                for(i in 0 until res.length()){
                    Log.d("JSON CHECK","Json Object $i : ${res.getJSONObject(i).get("state")}")
                }
                indiaListView.apply {
                    layoutManager = LinearLayoutManager(activity)
                    adapter = ListAdapter(parseData(res))
                }
                indiaConfirmed.text =  res.getJSONObject(0).get("confirmed").toString()
                indiaActive.text = res.getJSONObject(0).get("active").toString()
                indiaRecovered.text =  res.getJSONObject(0).get("recovered").toString()
                indiaDeceased.text =  res.getJSONObject(0).get("deaths").toString()
                indiaConfirmedDelta.text = "+"+ res.getJSONObject(0).get("deltaconfirmed").toString()
                indiaRecoveredDelta.text = "+"+ res.getJSONObject(0).get("deltarecovered").toString()
                indiaDeceasedDelta.text = "+"+ res.getJSONObject(0).get("deltadeaths").toString()
                indiaText.text = indiaText.text.toString() + " " + res.getJSONObject(0).get("lastupdatedtime").toString()
            },
            Response.ErrorListener { Log.d("Resp Error India", "onViewCreated: That didn't work!") })

        queue.add(stringRequest)



    }

    private fun parseData(res: JSONArray): List<MainActivity.Data> {
        val retList: MutableList<MainActivity.Data> = mutableListOf()
        for(i in 0 until res.length()){
            Log.d("JSON CHECK","Json Object $i : ${res.getJSONObject(i).get("state")}")
            val data = MainActivity.Data(
                res.getJSONObject(i).get("state").toString(),
                res.getJSONObject(i).get("lastupdatedtime").toString(),
                res.getJSONObject(i).get("confirmed").toString(),
                res.getJSONObject(i).get("active").toString(),
                res.getJSONObject(i).get("recovered").toString(),
                res.getJSONObject(i).get("deaths").toString()

            )
            if(data.name!="Total"){
                retList.add(data)
            }
        }
        return retList
    }
}