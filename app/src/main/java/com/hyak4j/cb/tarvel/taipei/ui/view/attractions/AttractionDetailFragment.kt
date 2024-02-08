package com.hyak4j.cb.tarvel.taipei.ui.view.attractions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.hyak4j.cb.tarvel.taipei.R
import com.hyak4j.cb.tarvel.taipei.databinding.FragmentAttractionDetailBinding
import com.hyak4j.cb.tarvel.taipei.model.attractions.Attraction
import com.hyak4j.cb.tarvel.taipei.ui.viewmodel.attractions.AttractionImageAdapter
import com.youth.banner.indicator.CircleIndicator

class AttractionDetailFragment(private val attraction: Attraction) : Fragment() {
    private lateinit var binding: FragmentAttractionDetailBinding

    companion object {
        fun newInstance(attraction: Attraction) = AttractionDetailFragment(attraction)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAttractionDetailBinding.inflate(inflater, container, false)

        // 設置ActionBar返回按鈕
        val actionBar = (activity as AppCompatActivity?)?.supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayShowHomeEnabled(true)
        // ActionBar Title更換為景點名稱
        val customView = actionBar?.customView
        val textViewTitle = customView?.findViewById<TextView>(R.id.title)
        textViewTitle?.text = attraction.name

        // 圖片Banner
        val attractionImages = attraction.images
        val adapter = AttractionImageAdapter(attractionImages)
        binding.bannerImage
            .addBannerLifecycleObserver(this)
            .setAdapter(adapter)
            .indicator = CircleIndicator(requireContext())

        // 遊憩景點文字資訊
        binding.txtOpenTime.text = attraction.open_time
        binding.txtAddress.text = attraction.address
        binding.txtTel.text = attraction.tel
        binding.txtOfficialSite.text = attraction.official_site
        binding.txtIntroduction.text = attraction.introduction

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

}