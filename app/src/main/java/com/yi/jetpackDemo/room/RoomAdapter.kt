package com.yi.jetpackDemo.room

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yi.jetpackDemo.databinding.ItemRoomBinding
import com.yi.jetpackDemo.room.database.User
import com.yi.jetpackDemo.room.greenDaoDB.User2

class RoomAdapter(var mData: ArrayList<User>?, var mData2: ArrayList<User2>?) :
    RecyclerView.Adapter<RoomAdapter.RoomViewHolder>() {

    private var dbType: Int = 1// 1: room; 2:greenDao

    fun refreshData(data: List<User>?, data2: List<User2>?, dbType: Int = 1) {
        this.dbType = dbType
        if (dbType == 2) {
            if (mData2 == null) mData2 = ArrayList()
            mData2?.clear()
            if (data2.isNullOrEmpty().not()) {
                mData2?.addAll(data2!!)
            }
        } else {
            if (mData == null) mData = ArrayList()
            mData?.clear()
            if (data.isNullOrEmpty().not()) {
                mData?.addAll(data!!)
            }
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
        return if (dbType == 2) {
            mData2?.size ?: 0
        } else {
            mData?.size ?: 0
        }
    }

    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {
        holder.onBindView(mData?.get(position), mData2?.get(position))
    }

    inner class RoomViewHolder(private val binding: ItemRoomBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBindView(data: User?, data2: User2?) {
            binding.dbType = dbType
            binding.user = data
            binding.user2 = data2
        }

    }
}