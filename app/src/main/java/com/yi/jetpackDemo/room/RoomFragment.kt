package com.yi.jetpackDemo.room

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.yi.jetpackDemo.R
import com.yi.jetpackDemo.databinding.FragmentRoomBinding
import kotlinx.android.synthetic.main.fragment_room.*

class RoomFragment : Fragment() {

    lateinit var mBinding: FragmentRoomBinding
    val mAdapter = RoomAdapter(null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding =
            DataBindingUtil.inflate(
                layoutInflater,
                R.layout.fragment_room,
                container,
                false
            )
        mBinding.handler = ClickHandler(this)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvUser.layoutManager = LinearLayoutManager(context)
        rvUser.adapter = mAdapter
    }

    fun refreshData(data: List<User>) {
        mAdapter.refreshData(data)
    }
}