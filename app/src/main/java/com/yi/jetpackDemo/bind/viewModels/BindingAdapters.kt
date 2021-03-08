package com.yi.jetpackDemo.bind.viewModels

import android.widget.ImageView
import androidx.databinding.BindingAdapter

/**
 * 绑定适配器
 * 1）特别适合五过多业务的交互
 * 2）可以使用可观察字段，并且可观察字段变化时，对应方法也会执行
 */
object BindingAdapters {
    // 1设置属性值
    // 1.1自动选择方法
    // 1.2指定自定义方法名称，怎么用？
//    @BindingMethods(
//        value = [
//            BindingMethod(
//                type = ImageView::class,
//                attribute = "android:tint",
//                method = "setImageTintList"
//            )]
//    )
    /**
     * 设置任务状态
     */
    @BindingAdapter("app:tabIndex", "app:taskStatus")
    @JvmStatic
    fun setTaskStatus(image: ImageView, index: Int, status: Int) {
        when (index) {
            0 -> {
                image.isSelected = status == 1
            }
            1 -> {
                image.isSelected = status == 1
            }
            2 -> {
                image.isSelected = status == 1
            }
        }
    }

    /**
     * 2对象转换
     * ObservableMap
     */

}