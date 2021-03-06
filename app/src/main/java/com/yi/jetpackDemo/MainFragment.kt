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
import com.yi.jetpackDemo.banner.HomeBannerJumpType
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
        banner.currentItem = 4
    }

    override fun OnBannerClick(data: HomeBannerBean?, position: Int) {
        data?.let { dt ->
            when (dt.jumpType) {
                HomeBannerJumpType.SERVICE -> {
                    findNavController().navigate(R.id.toServiceFragment)
                }
                HomeBannerJumpType.SLIDING_CONFLICT -> {
                    findNavController().navigate(R.id.toSlidingConflictFragment)
                }
                HomeBannerJumpType.RXJAVA -> {
                    findNavController().navigate(R.id.toRxJavaFragment)
                }
                HomeBannerJumpType.RETROFIT -> {
                    findNavController().navigate(R.id.toRetrofitFragment)
                }
                HomeBannerJumpType.LIFE_CYCLE -> {
                    findNavController().navigate(R.id.toLifeCycleFragment)
                }
                HomeBannerJumpType.EASY_PERMISSIONS -> {
                    findNavController().navigate(R.id.toEasyPermisionsFragment)
                }
                else -> {
                    Toast.makeText(context, dt.title, Toast.LENGTH_SHORT).show()
                }
            }

        }
    }
}