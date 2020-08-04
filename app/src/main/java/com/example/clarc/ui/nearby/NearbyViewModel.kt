package com.example.clarc.ui.nearby

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NearbyViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is near by Fragment"
    }
    val text: LiveData<String> = _text
}