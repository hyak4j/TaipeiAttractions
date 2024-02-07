package com.hyak4j.cb.tarvel.taipei.ui.viewmodel.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hyak4j.cb.tarvel.taipei.databinding.ItemNewsBinding
import com.hyak4j.cb.tarvel.taipei.model.news.Data

class NewsAdapter(private var _news: List<Data>) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    class NewsViewHolder(val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            ItemNewsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return _news.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.binding.txtNewsTitle.text = _news.get(position).title
        // 發現消息敘述中有換行及空白符號，呈現前進行濾除
        holder.binding.txtNewsDescription.text = _news.get(position).description
                                                      .replace("[\\r\\n\\s]*&nbsp;".toRegex(), "").trim()
    }

}
