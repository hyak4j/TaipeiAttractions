package com.hyak4j.cb.tarvel.taipei.ui.viewmodel.attractions

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hyak4j.cb.tarvel.taipei.R
import com.hyak4j.cb.tarvel.taipei.databinding.ItemAttractionBinding
import com.hyak4j.cb.tarvel.taipei.model.attractions.Attraction
import com.hyak4j.cb.tarvel.taipei.ui.view.attractions.AttractionDetailFragment
import java.lang.Integer.min

class AttractionAdapter(private var _attractions: List<Attraction>, val context: Context) :
    RecyclerView.Adapter<AttractionAdapter.AttractionViewHolder>() {

    class AttractionViewHolder(val binding: ItemAttractionBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AttractionViewHolder {
        return AttractionViewHolder(
            ItemAttractionBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return _attractions.size
    }

    override fun onBindViewHolder(holder: AttractionViewHolder, position: Int) {
        holder.binding.root.setOnClickListener {
            seeAttractionDetail(position, holder)
        }
        holder.binding.btnAttractionName.setOnClickListener {
            seeAttractionDetail(position, holder)
        }
        holder.binding.imageButton.setOnClickListener {
            seeAttractionDetail(position, holder)
        }
        if (position < _attractions.size) {
            val currentAttraction = _attractions[position]

            // 檢查 images不為空
            val attractionImage = holder.binding.ivAttractionImage
            if (currentAttraction.images.isNotEmpty()) {
                Glide.with(context)
                    .load(currentAttraction.images[0].src)
                    .into(attractionImage)
            } else {
                // API無提供圖片處理
                attractionImage.setImageDrawable(ResourcesCompat
                    .getDrawable(context.resources, R.drawable.ic_noimage, null))
            }

            holder.binding.btnAttractionName.text = currentAttraction.name
            holder.binding.txtAttractionIntroduction.text = currentAttraction.introduction
        }
    }

    private fun seeAttractionDetail(
        position: Int,
        holder: AttractionViewHolder
    ) {
        val attractionDetailFragment = AttractionDetailFragment.newInstance(_attractions[position])
        val transaction =
            (holder.itemView.context as AppCompatActivity).supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main_container, attractionDetailFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    fun updateData(newAttractions: List<Attraction>) {
        val oldSize = _attractions.size
        _attractions = newAttractions
        val newSize = _attractions.size

        // 通知列表範圍發生變化
        notifyItemRangeChanged(0, min(oldSize, newSize))
        if (newSize > oldSize) {
            // 新數據數量比舊數據大，通知插入新數據的位置
            notifyItemRangeInserted(oldSize, newSize - oldSize)
        } else if (newSize < oldSize) {
            // 新數據數量比舊數據小，通知移除多餘的數據
            notifyItemRangeRemoved(newSize, oldSize - newSize)
        }
    }
}