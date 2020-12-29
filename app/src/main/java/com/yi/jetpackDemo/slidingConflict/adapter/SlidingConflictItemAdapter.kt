package com.yi.jetpackDemo.slidingConflict.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yi.jetpackDemo.databinding.ItemSlidingConflictHorizontalBinding
import com.yi.jetpackDemo.slidingConflict.bean.SlidingConflictItemBean

class SlidingConflictItemAdapter(val datas: List<SlidingConflictItemBean>?) :
    RecyclerView.Adapter<SlidingConflictItemAdapter.SlidingConflictItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SlidingConflictItemHolder {
        return SlidingConflictItemHolder(
            ItemSlidingConflictHorizontalBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return datas?.size ?: 0
    }

    override fun onBindViewHolder(holder: SlidingConflictItemHolder, position: Int) {
        holder.onBindView(datas?.get(position))
    }

    inner class SlidingConflictItemHolder(private val binding: ItemSlidingConflictHorizontalBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBindView(data: SlidingConflictItemBean?) {
            data?.let { dt ->
                binding.data = dt
            }
        }

    }


}