package com.yi.rxjava

import com.yi.rxjavademo.mIntObserver
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import java.lang.IllegalArgumentException
import java.util.concurrent.Callable
import java.util.concurrent.Future
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread

/**
 * Async Opertors
 * The following operators are part of the distinct rxjava-async module. They are used to convert synchronous methods into Observables.
 * @author wanghuayi
 * @version
 * @since 2021/4/12
 */

/**
 * Creating Observables
 */
fun testCreate() {
//    Observable.create<String> {
//        it.onNext("first")
//        it.onNext("second")
//        it.onNext("third")
//        it.onComplete()
//    }.subscribe(mStringObserver)

    /**
     * empty
     * onSubscribe -> onComplete
     */
//    Observable.empty<String>().subscribe(mStringObserver)
    /**
     * never
     * onSubscribe
     */
//    Observable.never<String>().subscribe(mStringObserver)
    /**
     * Throw / error
     * onSubscribe -> onError
     */
//    Observable.error<String>(IllegalArgumentException("my throw")).subscribe(mStringObserver)

    /**
     * 从Callable convert into observalbe
     */
//    Observable.fromCallable(object : Callable<String> {
//        override fun call(): String {
//            return "test fromCallable"
//        }
//    }).subscribe(mStringObserver)

    /**
     * fromArray
     */
//    Observable.fromArray("a","b","c").subscribe(mStringObserver)

    /**
     * fromFuture
     */
//    Observable.fromFuture(object : Future<String> {
//        override fun isDone(): Boolean {
//            return true
//        }
//
//        override fun get(): String {
//            return "fromFuture"
//        }
//
//        override fun get(timeout: Long, unit: TimeUnit?): String {
//            return "fromFuture 2 "
//        }
//
//        override fun cancel(mayInterruptIfRunning: Boolean): Boolean {
//            return mayInterruptIfRunning
//        }
//
//        override fun isCancelled(): Boolean {
//            return false
//        }
//    }).subscribe(mStringObserver)

//    Observable.fromPublisher<> {  }

//    Observable.fromIterable(listOf("1", "2", "3")).subscribe(mStringObserver)

    /**
     * interval 定时器
     */
//    Observable.interval(1, 1, TimeUnit.SECONDS).subscribe(mLongObserver)

//    Observable.just()

//    Observable.range(0, 3).subscribe(mIntObserver)

    /**
     * repeat
     */
//    val source = Observable.range(1, 3)
//    source.repeat(3).subscribe(mIntObserver)

    /**
     * start ?
     */
//    Observable.just(1).startWith(2)
    /**
     * timer vs interval
     * timer 执行一次
     * interval 循环执行
     */
    Observable.timer(1, TimeUnit.SECONDS).subscribe(mLongObserver)

}

/**
 * 直到subscribes才去创建observable
 */
fun testDefer() {
    Observable.defer(object : Callable<ObservableSource<String>> {
        override fun call(): ObservableSource<String> {
            println("testDefer call")
            return Observable.just("defer")
        }
    }).subscribe(mStringObserver)
}


fun main() {
    thread {
        testCreate()
        Thread.sleep(3000)
    }
//    testDefer()
//    val args = arrayOf("java", "android", "hello")
//    Flowable.fromArray("java", "android", "hello").subscribe {
//        println("test $it")
//    }


}

private val mStringObserver by lazy {
    object : Observer<String> {
        override fun onComplete() {
            println("onComplete")
        }

        override fun onSubscribe(d: Disposable) {
            println("onSubscribe")
        }

        override fun onNext(t: String) {
            println("onNext $t")
        }

        override fun onError(e: Throwable) {
            println("onError ${e.message}")
        }

    }
}

private val mLongObserver by lazy {
    object : Observer<Long> {
        override fun onComplete() {
            println("onComplete")
        }

        override fun onSubscribe(d: Disposable) {
            println("onSubscribe")
        }

        override fun onNext(t: Long) {
            println("onNext $t")
        }

        override fun onError(e: Throwable) {
            println("onError ${e.message}")
        }

    }
}

