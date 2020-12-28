package com.yi.jetpackDemo.banner

import com.yi.jetpackDemo.R

/**
 * bannerAdapter使用的实体类
 */
enum class HomeBannerViewType(val value: Int) {
    IMAGE_HOLDER(1),
    TITLE_HOLDER(2)
}

data class HomeBannerBean(
    var imgRes: Int = 0,
    var imgUrl: String = "",
    var title: String = "",
    var viewType: HomeBannerViewType = HomeBannerViewType.IMAGE_HOLDER
) {

}

fun HomeBannerBean.getResImgs(): List<HomeBannerBean> {
    val list = ArrayList<HomeBannerBean>()
    list.add(
        HomeBannerBean(
            R.mipmap.image1,
            "",
            "slidingConflict",
            HomeBannerViewType.TITLE_HOLDER
        )
    )
    list.add(HomeBannerBean(R.mipmap.image2, "", "other", HomeBannerViewType.TITLE_HOLDER))
    list.add(HomeBannerBean(R.mipmap.image3, "", "other", HomeBannerViewType.TITLE_HOLDER))
    list.add(HomeBannerBean(R.mipmap.image4, "", "other", HomeBannerViewType.TITLE_HOLDER))
    list.add(HomeBannerBean(R.mipmap.image5, "", "other", HomeBannerViewType.TITLE_HOLDER))
    list.add(HomeBannerBean(R.mipmap.image6, "", "other", HomeBannerViewType.TITLE_HOLDER))
    return list
}