package com.yi.rxjavademo

import io.reactivex.Observable
import io.reactivex.functions.Predicate
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread

fun mainAll() {
    /**
     * all
     * 判断事件序列是否全部满足某个事件，如果都满足则返回 true，反之则返回 false
     */
    val dis = Observable.just(1, 2, 3, 4, 5, 6)
        .all { t -> t < 7 }
        .subscribe({
            println("all onNext $it")
        }, {
            println("all onError ${it.message}")
        })
}

fun mainTakeWhileAndTakeUntil() {
    /**
     * takeWhile
     * 满足条件即发送事件，不满足就不发送
     * takeUntil
     * 满足条件后，当时及其以后事件不在发送
     */
    Observable.just(1, 2, 3, 4, 5, 6)
//        .takeWhile { it < 3 }
        .takeUntil { it < 3 }
        .subscribe(mIntObserver)
}

fun mainSkipWhileAndSkipUnitl() {
    /**
     * skipWhile
     * 和tackWhile相反，满足条件就跳过
     * skipUntil
     * 当 skipUntil() 中的 Observable 发送事件了，原来的 Observable 才会发送事件给观察者
     * 试了一下无效？？
     */
    thread {
        Observable.intervalRange(1, 5, 0, 1, TimeUnit.SECONDS)
//        .skipWhile { it < 3 }
            .skipUntil<Int> { Observable.intervalRange(6, 5, 3, 1, TimeUnit.SECONDS) }
            .subscribe {
                println("skipUntil onNext $it")
            }
        Thread.sleep(20 * 1000)
    }
}

fun mainSequenceEqual() {
    /**
     * sequenceEqual
     * 判断两个 Observable 发送的事件是否相同
     */
    val dis = Observable.sequenceEqual(Observable.just(1, 4, 3), Observable.just(1, 2, 3))
        .subscribe({
            println("sequenceEqual onNext $it")
        }, {
            println("sequenceEqual onError ${it.message}")
        })
}

fun mainContains() {
    /**
     * contains
     * 判断事件序列中是否含有某个元素，如果有则返回 true，如果没有则返回 false
     */
    val dis = Observable.just(1, 2, 3)
        .contains(2)
        .subscribe({
            println("contains onNext $it")
        }, {
            println("contains onError ${it.message}")
        })
}

fun mainIsEmpty() {
    val dis = Observable.create<Int> {
        it.onComplete()
    }
        .isEmpty
        .subscribe({
            println("isEmpty onNext $it")
        }, {
            println("isEmpty onError ${it.message}")
        })
}

fun mainAmb() {
    /**
     * amb
     * 要传入一个 Observable 集合，但是只会发送最先发送事件的 Observable 中的事件，其余 Observable 将会被丢弃。
     * 利用这种特性，可以接受多个数据源，但是只使用第一个来的数据
     */
    thread {
        val dis = Observable.amb(
            arrayListOf(
                Observable.intervalRange(1, 5, 2, 1, TimeUnit.SECONDS),
                Observable.intervalRange(6, 5, 0, 1, TimeUnit.SECONDS)
            )
        )
            .subscribe {
                println("amb onNext $it")
            }
        Thread.sleep(10 * 1000)
    }
}

fun mainDefaultIfEmpty() {
    /**
     * defaultIfEmpty
     * 代替空值，传递一个默认值
     */
    Observable.create<Int> {
        it.onComplete()
    }
        .defaultIfEmpty(666)
        .subscribe(mIntObserver)
}