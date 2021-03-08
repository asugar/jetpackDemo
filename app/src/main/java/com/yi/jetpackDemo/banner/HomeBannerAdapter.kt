package com.yi.jetpackDemo.banner

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yi.jetpackDemo.databinding.ItemHomeBannerTitleHolderBinding
import com.youth.banner.adapter.BannerAdapter
import com.youth.banner.listener.OnBannerListener

/**
 * 首页bannerAdapter
 */
class HomeBannerAdapter(
    datas: List<HomeBannerBean>?,
    val listener: OnBannerListener<HomeBannerBean>?
) :
    BannerAdapter<HomeBannerBean, RecyclerView.ViewHolder>(datas) {

    override fun onCreateHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            HomeBannerViewType.IMAGE_HOLDER.value -> {
                return HomeBannerTitleHolder(
                    ItemHomeBannerTitleHolderBinding.inflate(
                        LayoutInflater.from(
                            parent.context
                        ), parent, false
                    )
                )
            }
            else -> {
                return HomeBannerTitleHolder(
                    ItemHomeBannerTitleHolderBinding.inflate(
                        LayoutInflater.from(
                            parent.context
                        ), parent, false
                    )
                )
            }

        }

    }

    override fun onBindView(
        holder: RecyclerView.ViewHolder?,
        data: HomeBannerBean?,
        position: Int,
        size: Int
    ) {
        when (holder?.itemViewType) {
            HomeBannerViewType.IMAGE_HOLDER.value -> {
                (holder as? HomeBannerTitleHolder)?.onBindView(data, listener)
            }
            else -> {
                (holder as? HomeBannerTitleHolder)?.onBindView(data, listener)
            }
        }

    }

    override fun getItemViewType(position: Int): Int {
        return getData(getRealPosition(position)).viewType.value
    }

    inner class HomeBannerTitleHolder(private val binding: ItemHomeBannerTitleHolderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBindView(data: HomeBannerBean?, listener: OnBannerListener<HomeBannerBean>?) {
            data?.let { dt ->
                binding.data = dt
                itemView.setOnClickListener {
                    listener?.OnBannerClick(dt, getRealPosition(adapterPosition))
                }
            }
        }
    }


}