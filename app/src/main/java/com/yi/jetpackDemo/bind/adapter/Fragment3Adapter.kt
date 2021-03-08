package com.yi.jetpackDemo.bind.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yi.jetpackDemo.databinding.ItemMyHolderBinding

/**
 * fragment3中rv的adapter
 */
class Fragment3Adapter(private val listener: MyListener?) :
    RecyclerView.Adapter<Fragment3Adapter.MyHolder>() {

    private var mData = ArrayList<String>()

    interface MyListener {
        fun onItemClick()
    }

    fun refreshData(datas: List<String>) {
        mData.clear()
        mData.addAll(datas)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(
            ItemMyHolderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.onBindView(mData[position], listener)
    }

    inner class MyHolder(private val binding: ItemMyHolderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBindView(data: String, listener: MyListener?) {
            binding.data = data
            binding.root.setOnClickListener {
                listener?.onItemClick()
            }
        }

    }
}