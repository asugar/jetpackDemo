package com.yi.rxjavademo

import io.reactivex.Observable
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread

fun mainFilter() {
    /**
     * filter
     * 利用的是Predicate
     * 逻辑来过滤被观察者发送的事件，如果返回 true 则会发送事件，否则不会发送
     */
    Observable.just(1, 2, 3)
        .filter { it >= 2 }
        .subscribe(mIntObserver)
}

fun mainOfType() {
    /**
     * ofType
     * 不知道为什么只能过滤String类型
     */
    val dis = Observable.just(1L, 2, 3, "xiaoyi", "error", 4)
        .ofType(String::class.java)
        .subscribe {
            println("ofType onNext $it")
        }
}

fun mainSkip() {
    /**
     * skip
     * 跳过count个事件后，发送下面的事件
     * skipLast
     */
    Observable.just(1, 2, 3, 4, 5, 6)
        .skip(2)// 跳过前面的事件
        .skipLast(2)// 跳过后面的事件
        .subscribe(mIntObserver)
}

fun mainDistinct() {
    /**
     * distinct
     * 过滤事件序列中重复的事件
     * distinctUntilChanged
     * 过滤连续重复事件
     */
    Observable.just(1, 2, 3, 3, 1, 2, 3)
//        .distinct()
        .distinctUntilChanged()
        .subscribe(mIntObserver)
}

fun mainTake() {
    /**
     * take
     * 控制观察者接收的事件的数量
     */
    Observable.just(1, 2, 3, 0, 4, 5, 6, 7)
        .take(3)
        .subscribe(mIntObserver)
}

fun mainDebounce() {
    /**
     * debounce
     * 如果两件事件发送的时间间隔小于设定的时间间隔则前一件事件就不会发送给观察者
     * 应用在连续点击上
     * 接受到的事件是最后一次
     */
    thread {
        Observable.create<Int> {
            it.onNext(1)
            Thread.sleep(400)
            it.onNext(2)
        }
            .debounce(500, TimeUnit.MILLISECONDS)
            .subscribe(mIntObserver)
        Thread.sleep(2000)
    }
}

fun mainFirstElement() {
    /**
     * firstElement
     *  取事件序列的第一个元素
     * lastElement
     *  取事件序列的最后一个元素
     */
    Observable.just(1, 2, 3, 4, 5, 6, 7)
//        .firstElement()
        .lastElement()
        .subscribe {
            println("firstElement onNext $it")
        }
}

fun mainElementAt() {
    /**
     * elementAt
     * 取出事件序列指定位置的事件
     * elementAtOrError
     * elementAt+index越界时的处理
     */
    val dis = Observable.just(1, 2, 3, 4, 5, 6)
//        .elementAt(2)
        .elementAtOrError(7)
        .subscribe({
            println("elementAt onNext $it")
        }, {
            println("elementAt onError ${it.message}")
        })
}