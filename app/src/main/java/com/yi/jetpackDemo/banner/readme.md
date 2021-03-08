## 测试使用banner库：https://github.com/youth5201314/banner

### 问题 使用databinding的时候src无法加载图片，需要使用imageResource
```
android:src="@{data.imgRes}"// 无法加载
@Nullable
public Drawable getDrawable(@StyleableRes int index) {
    return getDrawableForDensity(index, 0);
}

getDrawableForDensity

使用的是StyleableRes中的方法，旧版本没有mipmap，可能底层不支持

app:imageResource="@{data.imgRes}" // 可以正常加载
@android.view.RemotableViewMethod(asyncImpl="setImageResourceAsync")
    public void setImageResource(@DrawableRes int resId) {
        // The resource configuration may have changed, so we should always
        // try to load the resource even if the resId hasn't changed.
        final int oldWidth = mDrawableWidth;
        final int oldHeight = mDrawableHeight;

        updateDrawable(null);
        mResource = resId;
        mUri = null;

        resolveUri();

        if (oldWidth != mDrawableWidth || oldHeight != mDrawableHeight) {
            requestLayout();
        }
        invalidate();
    }
```

### @todo 可以使用RxJava过滤快速的两次点击 


## 提供一个测试跳转得入口