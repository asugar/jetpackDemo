package com.yi.jetpackDemo.room

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yi.jetpackDemo.databinding.ItemRoomBinding

class RoomAdapter(var mData: ArrayList<User>?) :
    RecyclerView.Adapter<RoomAdapter.RoomViewHolder>() {

    fun refreshData(data: List<User>) {
        if (mData == null) mData = ArrayList()
        mData?.clear()
        if (data.isNullOrEmpty().not()) {
            mData?.addAll(data)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder {
        return RoomViewHolder(
            ItemRoomBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return mData?.size ?: 0
    }

    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {
        holder.onBindView(mData?.get(position))
    }

    inner class RoomViewHolder(private val binding: ItemRoomBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBindView(data: User?) {
            binding.user = data
        }

    }
}