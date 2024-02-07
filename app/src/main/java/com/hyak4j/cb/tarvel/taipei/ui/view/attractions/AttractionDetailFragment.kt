package com.hyak4j.cb.tarvel.taipei.ui.view.attractions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hyak4j.cb.tarvel.taipei.R

class AttractionDetailFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_attraction_detail, container, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

}