package com.yi.jetpackDemo

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.yi.jetpackDemo.banner.HomeBannerAdapter
import com.yi.jetpackDemo.banner.HomeBannerBean
import com.yi.jetpackDemo.banner.getResImgs
import com.yi.jetpackDemo.lunchModel.ActivityA
import com.youth.banner.listener.OnBannerListener
import kotlinx.android.synthetic.main.fragment_main.*

/**
 * mainFragment 导航入口
 */
class MainFragment : Fragment(), OnBannerListener<HomeBannerBean> {

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

        view.findViewById<Button>(R.id.btnToLaunchModel).setOnClickListener {
            val intent = Intent(context, ActivityA::class.java)
            startActivity(intent)
        }

        initBanner()
    }

    private fun initBanner() {
        banner.addBannerLifecycleObserver(this)//添加生命周期观察者
            .setAdapter(HomeBannerAdapter(HomeBannerBean().getResImgs(), this))
            .setIndicator(indicator, false)
    }

    override fun OnBannerClick(data: HomeBannerBean?, position: Int) {
        Toast.makeText(context, data?.title, Toast.LENGTH_SHORT).show()
    }
}