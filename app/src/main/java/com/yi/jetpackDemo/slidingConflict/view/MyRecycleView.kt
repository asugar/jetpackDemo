package com.yi.jetpackDemo.slidingConflict.view

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.ViewConfiguration
import androidx.recyclerview.widget.RecyclerView
import com.orhanobut.logger.Logger
import com.yi.jetpackDemo.slidingConflict.SCTag
import kotlin.math.abs

/**
 * 1) 不让外出的RecycleView滑动
 * * 外层不拦截
 * 2）滑动内层时，外层不动
 * https://blog.csdn.net/xifei66/article/details/105551123
 */
class MyRecycleView : RecyclerView {

    private var viewFlag: Int = 1// 标记外层还是内层的RecycleView：1-外；2-内

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int = 0) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    fun setViewFlag(flag: Int) {
        viewFlag = flag
    }

    private var mScrollPointerId = 0
    private var mInitialTouchX = 0
    private var mInitialTouchY: Int = 0
    private var mTouchSlop = 0

    private fun init() {
        val vc: ViewConfiguration = ViewConfiguration.get(context)
        this.mTouchSlop = vc.scaledTouchSlop
    }


    override fun setScrollingTouchSlop(slopConstant: Int) {
        val vc: ViewConfiguration = ViewConfiguration.get(this.context)
        when (slopConstant) {
            0 -> {
                mTouchSlop = vc.scaledTouchSlop
            }
            1 -> {
                mTouchSlop = vc.scaledPagingTouchSlop
            }
            else -> {
                Logger.t(SCTag).d(
                    "RecyclerView",
                    "setScrollingTouchSlop(): bad argument constant $slopConstant; using default value"
                )
            }
        }
        super.setScrollingTouchSlop(slopConstant)
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        Logger.t(SCTag).d("$viewFlag dispatchTouchEvent ${ev?.action}")
        return super.dispatchTouchEvent(ev)
    }

    override fun onInterceptTouchEvent(e: MotionEvent?): Boolean {
        Logger.t(SCTag).d("$viewFlag onInterceptTouchEvent ${e?.action}")
        val canScrollHorizontally = layoutManager!!.canScrollHorizontally()
        val canScrollVertically = layoutManager!!.canScrollVertically()
        val action = e!!.actionMasked
        val actionIndex = e.actionIndex

        when (action) {
            MotionEvent.ACTION_DOWN -> {
                mScrollPointerId = e.getPointerId(0)
                this.mInitialTouchX = ((e.x + 0.5F).toInt())
                this.mInitialTouchY = ((e.y + 0.5F).toInt())
                return super.onInterceptTouchEvent(e);
            }
            MotionEvent.ACTION_MOVE -> {
                val index = e.findPointerIndex(this.mScrollPointerId)
                if (index < 0) {
                    Log.e(
                        "RecyclerView",
                        "Error processing scroll; pointer index for id " + this.mScrollPointerId + " not found. Did any MotionEvents get skipped?"
                    );
                    return false;
                }
                val x = (e.getX(index) + 0.5F).toInt()
                val y = (e.getY(index) + 0.5F).toInt()
                if (scrollState != 1) {
                    val dx = x - this.mInitialTouchX
                    val dy = y - this.mInitialTouchY
                    var startScroll: Boolean = false
                    if (canScrollHorizontally && abs(dx) > this.mTouchSlop && abs(dx) > abs(dy)
                    ) {
                        startScroll = true
                    }

                    if (canScrollVertically && abs(dy) > this.mTouchSlop && abs(dy) > abs(
                            dx
                        )
                    ) {
                        startScroll = true
                    }
                    Log.d(
                        "MyRecyclerView",
                        "canX:" + canScrollHorizontally + "--canY" + canScrollVertically + "--dx:" + dx + "--dy:" + dy + "--startScorll:" + startScroll + "--mTouchSlop" + mTouchSlop
                    )
                    return startScroll && super.onInterceptTouchEvent(e);
                }

            }
            MotionEvent.ACTION_POINTER_DOWN -> {
                this.mScrollPointerId = e.getPointerId(actionIndex)
                this.mInitialTouchX = (e.getX(actionIndex) + 0.5F).toInt()
                this.mInitialTouchY = (e.getY(actionIndex) + 0.5F).toInt()
                return super.onInterceptTouchEvent(e);
            }

//            if (viewFlag == 1) {
//                return false
//            } else {
//                return super.onInterceptTouchEvent(e)
//            }
        }
        return super.onInterceptTouchEvent(e)
    }

    private var mLastX: Float = 0f
    private var mLastY: Float = 0f
    override fun onTouchEvent(e: MotionEvent?): Boolean {
        Logger.t(SCTag).d("$viewFlag onTouchEvent ${e?.action}")
        when (e?.action) {
            MotionEvent.ACTION_DOWN -> {
                mLastX = e.x
                mLastY = e.y
            }
            MotionEvent.ACTION_MOVE -> {
                val currentX = e.x
                val currentY = e.y

            }
            MotionEvent.ACTION_UP -> {
                mLastX = 0f
                mLastY = 0f
            }
        }

//        if (viewFlag == 1) {//
        return super.onTouchEvent(e)
//        } else {
//            super.onTouchEvent(e)
//            return true
//        }
    }

    private fun isVertical(currentX: Float, currentY: Float) {
        currentX - mLastX
        currentY - mLastY

    }
}

