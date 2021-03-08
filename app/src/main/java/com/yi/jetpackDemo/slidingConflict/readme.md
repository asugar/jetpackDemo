## 尝试去处理一个竖向RecycleView嵌套一个横向RecycleView的场景时的滑动问题
### 写一个recycleview嵌套的例子 
2020-12-29 -- ok
### 尝试去处理滑动冲突
* 自定义recycleView
* RecycleView的dispatchKeyEvent VS dispatchTouchEvent 为什么没有执行？
//不允许父View拦截事件
getParent().requestDisallowInterceptTouchEvent(true)

## SwipeRefreshLayout 嵌套 横向RecycleView时，横向滑动RV时，会带动SRL的向下刷新，另外手指离开RV时惯性滑动卡

让SRL下拉刷新有个滑动距离再触发

```
rvHomeFunction.setOnTouchListener { v, event ->
    when (event.action) {
        MotionEvent.ACTION_DOWN -> {
            srlayout.isEnabled = false
            true
        }
        MotionEvent.ACTION_UP -> {
//                    srlayout.isEnabled = true
            true
        }
        MotionEvent.ACTION_CANCEL ->{
            srlayout.isEnabled = true
            true
        }
        else -> {
            false
        }
    }
}
```

