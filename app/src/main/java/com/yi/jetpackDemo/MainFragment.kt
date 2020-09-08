package com.yi.jetpackDemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

/**
 * mainFragment 导航入口
 */
class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.btnToBinding).setOnClickListener {
            findNavController().navigate(R.id.toViewBindingFragment)
        }

        view.findViewById<Button>(R.id.btnToWorkManager).setOnClickListener {
            findNavController().navigate(R.id.toWorkManagerFragment)
        }

        view.findViewById<Button>(R.id.btnToRoom).setOnClickListener {
            findNavController().navigate(R.id.toRoomFragment)
        }

    }
}