package com.hyak4j.cb.tarvel.taipei.ui.view.homepage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hyak4j.cb.tarvel.taipei.databinding.FragmentMainBinding
import com.hyak4j.cb.tarvel.taipei.model.news.NewsRepository
import com.hyak4j.cb.tarvel.taipei.ui.view.news.NewsViewModel
import com.hyak4j.cb.tarvel.taipei.ui.viewmodel.news.NewsAdapter
import com.hyak4j.cb.tarvel.taipei.ui.viewmodel.news.NewsViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private lateinit var newsViewModel: NewsViewModel
    private val newsRepository by lazy {
        NewsRepository()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)

        // 最新消息
        newsViewModel = ViewModelProvider(this, NewsViewModelFactory(newsRepository))
            .get(NewsViewModel::class.java)

        newsViewModel.news.observe(viewLifecycleOwner) { news ->
            binding.recyclerviewNews.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(
                    this@MainFragment.requireContext(),
                    RecyclerView.VERTICAL,
                    false
                )
                adapter = NewsAdapter(news)
            }
        }

        CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.Main) {
                newsViewModel.getNews()
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

}