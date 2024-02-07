package com.hyak4j.cb.tarvel.taipei.ui.view.homepage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hyak4j.cb.tarvel.taipei.R
import com.hyak4j.cb.tarvel.taipei.databinding.FragmentMainBinding
import com.hyak4j.cb.tarvel.taipei.model.attractions.AttractionRepository
import com.hyak4j.cb.tarvel.taipei.model.news.NewsRepository
import com.hyak4j.cb.tarvel.taipei.ui.view.news.NewsViewModel
import com.hyak4j.cb.tarvel.taipei.ui.viewmodel.attractions.AttractionAdapter
import com.hyak4j.cb.tarvel.taipei.ui.viewmodel.attractions.AttractionViewModel
import com.hyak4j.cb.tarvel.taipei.ui.viewmodel.attractions.AttractionViewModelFactory
import com.hyak4j.cb.tarvel.taipei.ui.viewmodel.news.NewsAdapter
import com.hyak4j.cb.tarvel.taipei.ui.viewmodel.news.NewsViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    // 最新消息
    private lateinit var newsViewModel: NewsViewModel
    private val newsRepository by lazy {
        NewsRepository()
    }
    // 遊憩景點
    private lateinit var attractionViewModel: AttractionViewModel
    private val attractionRepository by lazy {
        AttractionRepository()
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

        // 遊憩景點
        attractionViewModel = ViewModelProvider(this, AttractionViewModelFactory(attractionRepository))
            .get(AttractionViewModel::class.java)

        attractionViewModel.attraction.observe(viewLifecycleOwner) { attraction ->
            binding.recyclerviewAttractions.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(
                    this@MainFragment.requireContext(),
                    RecyclerView.VERTICAL,
                    false)
                val attractionAdapter = AttractionAdapter(attraction, this@MainFragment.requireContext())
                attractionAdapter.updateData(attraction)
                adapter = attractionAdapter
            }
        }

        CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.Main) {
                newsViewModel.getNews()
                attractionViewModel.getAttraction()
                // 景點總數顯示
                binding.txtAttractionNumber.text = "${resources.getString(R.string.taipei_attractions)}  ${AttractionRepository().getAttractions().total.toString()}"
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

}