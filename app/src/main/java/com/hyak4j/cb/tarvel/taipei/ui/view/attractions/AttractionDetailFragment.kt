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
import com.hyak4j.cb.tarvel.taipei.ui.view.WebViewFragment
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
        if (attractionImages.isNotEmpty()) {
            val adapter = AttractionImageAdapter(attractionImages)
            binding.bannerImage
                .addBannerLifecycleObserver(this)
                .setAdapter(adapter)
                .indicator = CircleIndicator(requireContext())
        } else {
            binding.bannerImage.visibility = View.GONE
        }

        // 若無資料，隱藏其TextView
        binding.txtOpenTime.visibility = if (attraction.open_time.isEmpty()) View.GONE else View.VISIBLE
        binding.txtAddress.visibility = if (attraction.address.isEmpty()) View.GONE else View.VISIBLE
        binding.txtTel.visibility = if (attraction.tel.isEmpty()) View.GONE else View.VISIBLE
        binding.txtOfficialSite.visibility = if (attraction.official_site.isEmpty()) View.GONE else View.VISIBLE
        binding.txtIntroduction.visibility = if (attraction.introduction.isEmpty()) View.GONE else View.VISIBLE
        // 遊憩景點文字資訊顯示
        binding.txtOpenTime.text = attraction.open_time
        binding.txtAddress.text = attraction.address
        binding.txtTel.text = attraction.tel
        binding.txtOfficialSite.text = attraction.official_site
        binding.txtIntroduction.text = attraction.introduction

        binding.txtOfficialSite.setOnClickListener {
            val newFragment = WebViewFragment.newInstance(attraction.official_site, 1, attraction.name)
            val transaction = (requireContext() as AppCompatActivity).supportFragmentManager.beginTransaction()
            transaction.replace(R.id.main_container, newFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

}