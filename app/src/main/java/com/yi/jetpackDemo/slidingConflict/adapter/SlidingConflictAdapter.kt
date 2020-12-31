package com.yi.jetpackDemo.slidingConflict.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yi.jetpackDemo.databinding.ItemSlidingConflictVerticalBinding
import com.yi.jetpackDemo.slidingConflict.bean.SlidingConflictBean

class SlidingConflictAdapter(val datas: List<SlidingConflictBean>?) :
    RecyclerView.Adapter<SlidingConflictAdapter.SlidingConflictHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SlidingConflictHolder {
        return SlidingConflictHolder(
            ItemSlidingConflictVerticalBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return datas?.size ?: 0
    }

    override fun onBindViewHolder(holder: SlidingConflictHolder, position: Int) {
        holder.onBindView(datas?.get(position))
    }

    inner class SlidingConflictHolder(private val binding: ItemSlidingConflictVerticalBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBindView(data: SlidingConflictBean?) {
            data?.let { dt ->
                binding.data = dt
                binding.rvHorizontal.setViewFlag(2)
                binding.rvHorizontal.adapter = SlidingConflictItemAdapter(dt.items)
            }
        }

    }

}