package com.hyak4j.cb.tarvel.taipei.ui.viewmodel.attractions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hyak4j.cb.tarvel.taipei.model.attractions.Attraction
import com.hyak4j.cb.tarvel.taipei.model.attractions.AttractionRepository
import kotlinx.coroutines.launch

class AttractionViewModel(private val attractionRepository: AttractionRepository) : ViewModel() {
    private var _attraction = MutableLiveData<List<Attraction>>()
    val attraction: LiveData<List<Attraction>> get() = _attraction

    fun getAttraction() {
        viewModelScope.launch {
            val response = attractionRepository.getAttractions()
            _attraction.value = response.attractions
        }
    }
}