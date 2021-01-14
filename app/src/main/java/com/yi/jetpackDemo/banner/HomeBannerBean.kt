package com.yi.jetpackDemo.banner

import com.yi.jetpackDemo.R

/**
 * bannerAdapter使用的实体类
 */
enum class HomeBannerViewType(val value: Int) {
    DEFALUT_HOLDER(0),
    IMAGE_HOLDER(1),
    TITLE_HOLDER(2)
}

/**
 * bannerAdapter的跳转类型
 */
enum class HomeBannerJumpType(val type: Int) {
    DEFALU_JUMPT(0),
    SLIDING_CONFLICT(1),
    RXJAVA(2),
    RETROFIT(3),
    LIFE_CYCLE(4),
}

data class HomeBannerBean(
    var imgRes: Int = 0,
    var imgUrl: String = "",
    var title: String = "",
    var viewType: HomeBannerViewType = HomeBannerViewType.DEFALUT_HOLDER,
    var jumpType: HomeBannerJumpType = HomeBannerJumpType.DEFALU_JUMPT
) {

}

fun HomeBannerBean.getResImgs(): List<HomeBannerBean> {
    val list = ArrayList<HomeBannerBean>()
    list.add(
        HomeBannerBean(
            R.mipmap.image1,
            "",
            "slidingConflict",
            HomeBannerViewType.TITLE_HOLDER,
            HomeBannerJumpType.SLIDING_CONFLICT
        )
    )
    list.add(
        HomeBannerBean(
            R.mipmap.image2,
            "",
            "RxJava",
            HomeBannerViewType.TITLE_HOLDER,
            HomeBannerJumpType.RXJAVA
        )
    )
    list.add(
        HomeBannerBean(
            R.mipmap.image3,
            "",
            "Retrofit",
            HomeBannerViewType.TITLE_HOLDER,
            HomeBannerJumpType.RETROFIT
        )
    )
    list.add(
        HomeBannerBean(
            R.mipmap.image4,
            "",
            "LifeCycle",
            HomeBannerViewType.TITLE_HOLDER,
            HomeBannerJumpType.LIFE_CYCLE
        )
    )
    list.add(HomeBannerBean(R.mipmap.image5, "", "other"))
    list.add(HomeBannerBean(R.mipmap.image6, "", "other"))
    return list
}