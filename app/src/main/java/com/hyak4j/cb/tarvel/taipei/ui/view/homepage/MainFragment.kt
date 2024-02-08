package com.hyak4j.cb.tarvel.taipei.ui.view.homepage

import android.app.AlertDialog
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hyak4j.cb.tarvel.taipei.R
import com.hyak4j.cb.tarvel.taipei.config.LanguageManager
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
import java.util.Locale

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

        // 需設置menu才會顯示
        setHasOptionsMenu(true)

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

        refreshUI()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    // 語系選擇
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_language -> {
                showLanguageSelectionDialog()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showLanguageSelectionDialog() {
        /*     支援語系
            zh-tw -繁體中文
            zh-cn -簡體中文
            en -英文
            ja -日文
            ko -韓文
            es -西班牙文
            th -泰文
            vi -越南文
            id -印尼文
         */
        val languageOptions = arrayOf(
            "繁體中文",
            "简体中文",
            "English",
            "日本語",
            "한국어",
            "Español",
            "แบบไทย",
            "Tiếng Việt",
            "bahasa Indonesia"
        )

        AlertDialog.Builder(requireContext())
            .setTitle(resources.getString(R.string.select_language))
            .setItems(languageOptions) { _, which ->
                // 選擇語系
                val selectedLanguage = when (which) {
                    0 -> "zh-tw"
                    1 -> "zh-cn"
                    2 -> "en"
                    3 -> "ja"
                    4 -> "ko"
                    5 -> "es"
                    6 -> "th"
                    7 -> "vi"
                    8 -> "id"
                    else -> "zh-tw"
                }
                LanguageManager.setLanguage(selectedLanguage)
                refreshUI()
            }
            .show()
    }

    private fun refreshUI() {
        CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.Main) {
                val currentLanguage = LanguageManager.getLanguage()

                setAppLocale(currentLanguage)

                updateActionBar()

                // 取得最新消息
                newsViewModel.getNews(currentLanguage)
                // 取得遊憩景點
                attractionViewModel.getAttraction(currentLanguage)
                // 景點總數顯示
                binding.txtAttractionNumber.text =
                    "${resources.getString(R.string.taipei_attractions)}  ${
                        AttractionRepository().getAttractions(currentLanguage).total.toString()
                    }"

                binding.btnAttractions.text = resources.getString(R.string.attractions)
                binding.btnNews.text = resources.getString(R.string.news)
            }
        }
    }

    private fun setAppLocale(currentLanguage: String) {
        // 設定APP區域設定
        val newLocale = Locale(currentLanguage)
        Locale.setDefault(newLocale)
        val configuration = Configuration(resources.configuration)
        configuration.setLocale(newLocale)
        resources.updateConfiguration(configuration, resources.displayMetrics)
    }

    private fun updateActionBar() {
        /*
           透過setCustomView 設定actionBar Title需要這樣處理
           若是supportActionBar?.setDisplayShowTitleEnabled(true) => 可直接依語系變化呈現
         */
        val actionBar = (requireActivity() as AppCompatActivity).supportActionBar
        val customView = actionBar?.customView

        if (customView != null) {
            val txtTitle = customView.findViewById<TextView>(R.id.title)
            txtTitle?.text = resources.getString(R.string.app_name)
        }
    }
}