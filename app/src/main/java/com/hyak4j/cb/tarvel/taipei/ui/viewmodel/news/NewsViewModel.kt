package com.hyak4j.cb.tarvel.taipei.ui.view.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hyak4j.cb.tarvel.taipei.model.news.Data
import com.hyak4j.cb.tarvel.taipei.model.news.NewsRepository
import kotlinx.coroutines.launch

class NewsViewModel(private val newsRepository: NewsRepository) : ViewModel() {

    private var _news = MutableLiveData<List<Data>>()
    val news: LiveData<List<Data>> get() = _news

    fun getNews(language: String) {
        viewModelScope.launch {
            val response = newsRepository.getNews(language)
            _news.value = response
        }
    }
}