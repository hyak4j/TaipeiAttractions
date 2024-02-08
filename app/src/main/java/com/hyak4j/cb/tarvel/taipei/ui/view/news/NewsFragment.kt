package com.hyak4j.cb.tarvel.taipei.ui.view.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.hyak4j.cb.tarvel.taipei.R
import com.hyak4j.cb.tarvel.taipei.databinding.FragmentNewsBinding

class NewsFragment(private val url: String) : Fragment() {
    private lateinit var binding: FragmentNewsBinding

    companion object {
        fun newInstance(url: String) = NewsFragment(url)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsBinding.inflate(inflater, container, false)

        // 設置ActionBar返回按鈕
        val actionBar = (activity as AppCompatActivity?)?.supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayShowHomeEnabled(true)
        // ActionBar Title更換為最新消息
        val customView = actionBar?.customView
        val textViewTitle = customView?.findViewById<TextView>(R.id.title)
        textViewTitle?.text = resources.getString(R.string.news)

        // webview相關設定
        val newsWebView = binding.webviewNews
        val webSettings = newsWebView.settings
        webSettings.javaScriptEnabled = true
        newsWebView.loadUrl(url)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}