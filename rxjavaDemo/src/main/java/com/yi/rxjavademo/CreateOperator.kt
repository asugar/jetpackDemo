package com.yi.rxjavademo

import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Callable
import java.util.concurrent.FutureTask
import java.util.concurrent.TimeUnit

fun mainCreate() {
    /**
     * create
     */
    val observable = Observable.create(ObservableOnSubscribe<String> { emitter ->
        emitter.onNext("hello")
        emitter.onNext("world")
        emitter.onNext("from")
        emitter.onNext("java")
        emitter.onComplete()
    }).subscribe({ dt ->
        println("onNext $dt")
    }, {
        println("onError ${it.message}")
    }, {
        println("onComplete ")
    })
}

fun mainJust() {
    /**
     * just 发送的事件最多不能超过10个
     */
    val dis = Observable.just(1, 2, 3)
        .subscribe({
            println("onNext $it")
        }, {
            println("onError ${it.message}")
        }, {
            println("onComplete ")
        })
}

fun mainFromArray() {
    /**
     * fromArray 类似just 可以操作10个，可以传入数组
     * 不能使用array传入，可以使用intArrayof或者array.toIntArray
     */
    val array = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
//    val dis = Observable.fromArray(1, 2, 4, 5, 6, 7, 8)
    val aa = intArrayOf(1, 3, 4, 5, 6, 7)
    val dis = Observable.fromArray(array.toIntArray())
        .subscribe({
            println("onNext $it")
        }, {
            println("onError ${it.message}")
        }, {
            println("onComplete ")
        })
}

val mIntObserver = object : Observer<Int> {
    override fun onComplete() {
        println("onComplete ")
    }

    override fun onSubscribe(d: Disposable) {
        println("onSubscribe ")
    }

    override fun onNext(t: Int) {
        println("onNext $t ${Thread.currentThread().name}")
    }

    override fun onError(e: Throwable) {
        println("onError ${e.message}")
    }
}

fun mainFromCallable() {
    /**
     * fromCallable
     * todo need study callable
     */
    val dis = Observable.fromCallable { 1 }.subscribe(mIntObserver)
}

fun mainFromFuture() {
    /**
     * fromFuture
     * @todo need study future
     */
    val futureTask = FutureTask<Int>(Callable<Int> {
        println("futureTask call 1")
        1
    })

    val dis = Observable.fromFuture(futureTask)
        .doOnSubscribe {
            println("doOnSubscribe")
            futureTask.run()
        }
        .subscribe(mIntObserver)
}

fun mainFromIterable() {
    /**
     * fromIterable
     * 接受一个iterable接口的类
     */
    val list = ArrayList<Int>()
    list.add(1)
    list.add(2)
    list.add(3)
    list.add(4)
    list.add(5)
    Observable.fromIterable(list).subscribe(mIntObserver)
}

fun mainDefer() {
    /**
     * defer
     * 这个方法的作用就是直到被观察者被订阅后才会创建被观察者。
     * 对比create的情况，就是每次subscribe都会去执行一遍Observable
     */
    var i: Int = 100
    val observable = Observable.defer {
        println("defer observable")
        Observable.just(i)
    }
    val observable2 = Observable.create(ObservableOnSubscribe<Int> {
        println("default create observable")
        it.onNext(i)
    })

    i = 200
    observable.subscribe(mIntObserver)
//    observable2.subscribe(mIntObserver)
    i = 300
    observable.subscribe(mIntObserver)
//    observable2.subscribe(mIntObserver)
    /**
     * create
     *  onSubscribe
    default create observable
    onNext 200
    onSubscribe
    default create observable
    onNext 300
     */

    /**
     * defer
     *  defer observable
    onSubscribe
    onNext 200
    onComplete
    defer observable
    onSubscribe
    onNext 300
    onComplete
     */

}

val mLongObserver = object : Observer<Long> {
    override fun onComplete() {
        println("onComplete ")
    }

    override fun onSubscribe(d: Disposable) {
        println("onSubscribe mLongObserver ")
    }

    override fun onNext(t: Long) {
        println("onNext $t ")
    }

    override fun onError(e: Throwable) {
        println("onError ${e.message} ")
    }
}

fun mainTimer() {
    /**
     * timer 只执行一次，多久之后执行一次
     * 没有执行onNext？
     * 感觉这个方法没有生效 使用Schedulers.trampoline() 可以生效
     */
    Observable.timer(2, TimeUnit.SECONDS, Schedulers.trampoline())
        .subscribe(mLongObserver)
}

fun mainInterval() {
    /**
     * interval
     * 间隔多久执行一次
     */
    Observable.interval(2, 2, TimeUnit.SECONDS, Schedulers.trampoline())
        .subscribe(mLongObserver)
}

fun mainIntervalRange() {
    /**
     * intervalRange
     * 在区间内间隔执行
     */
    Observable.intervalRange(0, 10, 2, 3, TimeUnit.SECONDS, Schedulers.trampoline())
        .subscribe(mLongObserver)
}

fun mainRange() {
    /**
     * range
     * rangeLong() 类似 range
     * 区间内连续执行
     */
    Observable.range(0, 10).subscribe(mIntObserver)
}

fun main() {

    /**
     * empty:直接发送 onComplete() 事件
     * never:不发送任何事件
     * error:发送 onError() 事件
     */
    Observable.empty<Int>().subscribe(mIntObserver)
    Observable.never<Int>().subscribe(mIntObserver)
    Observable.error<Int>(Throwable("myErrror")).subscribe(mIntObserver)
}