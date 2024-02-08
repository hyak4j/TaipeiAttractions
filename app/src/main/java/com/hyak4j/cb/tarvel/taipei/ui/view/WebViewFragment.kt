package com.hyak4j.cb.tarvel.taipei.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.hyak4j.cb.tarvel.taipei.R
import com.hyak4j.cb.tarvel.taipei.databinding.FragmentWebviewBinding

class WebViewFragment(private val url: String, private val source: Int, private val name: String?) : Fragment() {
    private lateinit var binding: FragmentWebviewBinding

    companion object {
        fun newInstance(url: String, source: Int, name: String) = WebViewFragment(url, source, name)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWebviewBinding.inflate(inflater, container, false)
        binding.progressBar.visibility = View.VISIBLE
        // 設置ActionBar返回按鈕
        val actionBar = (activity as AppCompatActivity?)?.supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayShowHomeEnabled(true)
        // ActionBar Title更換為最新消息
        val customView = actionBar?.customView
        val textViewTitle = customView?.findViewById<TextView>(R.id.title)
        textViewTitle?.text = if (source == 0){
            resources.getString(R.string.news)
        } else {
            name
        }

        // webview相關設定
        val webView = binding.webview
        webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                binding.progressBar.visibility = View.GONE
            }
        }
        val webSettings = webView.settings
        webSettings.javaScriptEnabled = true
        webView.loadUrl(url)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}