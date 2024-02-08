package com.hyak4j.cb.tarvel.taipei.ui.viewmodel.attractions

import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hyak4j.cb.tarvel.taipei.model.attractions.Image
import com.youth.banner.adapter.BannerAdapter


class AttractionImageAdapter(images: List<Image>) :
    BannerAdapter<Image, AttractionImageAdapter.BannerViewHolder>(images) {

    override fun onCreateHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        val imageView = ImageView(parent.context)
        // viewpager2必須設置為match_parent
        imageView.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        return BannerViewHolder(imageView)
    }

    override fun onBindView(holder: BannerViewHolder, image: Image, position: Int, size: Int) {
        // 以Glide載入url圖片
        Glide.with(holder.imageView.context)
            .load(image.src)
            .centerCrop()
            .into(holder.imageView)
    }

    class BannerViewHolder(view: ImageView) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view
    }
}
