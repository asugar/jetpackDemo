package com.yi.jetpackDemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.yi.jetpackDemo.bind.viewModels.SharedViewModel

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
    }
}