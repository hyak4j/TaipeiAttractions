package com.hyak4j.cb.tarvel.taipei.ui.viewmodel.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hyak4j.cb.tarvel.taipei.model.news.NewsRepository
import com.hyak4j.cb.tarvel.taipei.ui.view.news.NewsViewModel

class NewsViewModelFactory(private val newsRepository: NewsRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewsViewModel::class.java)) {
            return NewsViewModel(newsRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}