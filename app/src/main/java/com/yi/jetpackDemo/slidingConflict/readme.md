## 尝试去处理一个竖向RecycleView嵌套一个横向RecycleView的场景时的滑动问题
### 写一个recycleview嵌套的例子 
2020-12-29 -- ok
### 尝试去处理滑动冲突
* 自定义recycleView
* RecycleView的dispatchKeyEvent VS dispatchTouchEvent 为什么没有执行？
//不允许父View拦截事件
getParent().requestDisallowInterceptTouchEvent(true)