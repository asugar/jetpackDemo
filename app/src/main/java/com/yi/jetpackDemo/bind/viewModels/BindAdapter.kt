package com.yi.jetpackDemo.bind.viewModels

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter

/**
 * 绑定适配器
 */
class BindAdapter {
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
    // 1.3自定义逻辑
    @BindingAdapter("imageUrl", "error")
    fun loadImage(view: ImageView, url: String, error: Drawable) {
//        Picasso.get().load(url).error(error).into(view)
    }

    /**
     * 2对象转换
     * ObservableMap
     */

}