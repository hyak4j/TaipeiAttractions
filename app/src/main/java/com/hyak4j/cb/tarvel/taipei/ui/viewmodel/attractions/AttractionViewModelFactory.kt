package com.hyak4j.cb.tarvel.taipei.ui.viewmodel.attractions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hyak4j.cb.tarvel.taipei.model.attractions.AttractionRepository

class AttractionViewModelFactory(private val attractionRepository: AttractionRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AttractionViewModel::class.java)) {
            return AttractionViewModel(attractionRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}