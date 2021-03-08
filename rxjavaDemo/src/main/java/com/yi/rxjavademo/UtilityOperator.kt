package com.yi.rxjavademo

import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import java.lang.Exception
import java.lang.NullPointerException
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread

fun mainDelay() {
    /**
     * delay
     * 延迟一段事件再执行事件
     */
    thread {
        Observable.just(1, 2, 3, 4, 5, 6)
            .delay(2, TimeUnit.SECONDS)
            .subscribe(mIntObserver)
        Thread.sleep(3000)
    }
}

fun mainDoOnEach() {
    /**
     * doOnEach
     * 每次发送事件之前都执行doOnEach
     * 只能利用发送的数据，不能改变数据
     */
    Observable.just(1, 2, 3)
        .doOnEach {
            println("doOnEach ${it.value}")
        }
        .subscribe(mIntObserver)
}

fun mainDoOn() {
    /**
     * doOnNext
     * doAfterNext
     * doOnComplete
     * doOnError
     * doOnSubscribe
     * doOnDispose
     * doOnLifecycle = (doOnSubscribe+doOnDispose
     * doOnTerminate 在onError或者onComplete发送之前回调
     * doAfterTerminate 在onError或者onComplete发送之后回调
     * doFinally 最后执行，如果取消订阅doAfterTerminate不会执行，但是doFinally会执行
     * 相当于再做这些事之前，还有一次机会操作
     */
    Observable.create<Int> {
        it.onNext(1)
        it.onNext(2)
        it.onNext(3)
        it.onNext(4)
        it.onError(NullPointerException())
        it.onComplete()
    }
        .doOnNext {
            println("doOnNext $it")
        }
        .doAfterNext {
            println("doAfterNext $it")
        }
        .doOnComplete {
            println("doOnComplete ")
        }
        .doOnError {
            println("doOnError ${it.message}")
        }
        .doOnSubscribe {
            println("doOnSubscribe $it")
        }
        .doOnDispose {
            println("doOnDispose")
        }
        .doOnLifecycle({
            println("doOnLifecycle subscribe $it")
        }, {
            println("doOnLifecycle disposable")
        })
        .doOnTerminate {
            println("doOnTerminate")
        }
        .doAfterTerminate {
            println("doAfterTerminate")
        }
        .doFinally {
            println("doFinally")
        }
        .subscribe(mIntObserver)
}

fun mainonErrorReturn() {
    /**
     * onErrorReturn
     * 在接受到error的时候，会返回一个值当作onNext执行
     * onErrorResumeNext
     * 在接受到onError的时候，返回一个Observable
     * onExceptionResumeNext
     * 再接受到exception的时候，会拿到observable
     */
    Observable.create<Int> {
        it.onNext(1)
        it.onNext(2)
        it.onNext(3)
        it.onError(NullPointerException())
    }
//        .onErrorReturn {
//            println("onErrorReturn ${it.message}")
//            6
//        }
//        .onErrorResumeNext(Function<Throwable, ObservableSource<Int>> { t ->
//            println("onErrorResumeNext ${t.message}")
//            Observable.just(66)
//        })
        .onExceptionResumeNext {
            println("onExceptionResumeNext")
            it.onNext(5)
            it.onComplete()
        }
        .subscribe(mIntObserver)
}

fun mainRetry() {
    /**
     * retry
     * 在出现错误事件，会重新发送所有事件，times是发送的次数
     */
    Observable.create<Int> {
        it.onNext(1)
        it.onNext(2)
        it.onNext(3)
        it.onError(Exception("404"))
    }
        .retry(2)
        .subscribe(mIntObserver)
}

fun mainRetryUtilAndRetryWhen() {
    /**
     * retryUtil
     * true：停止
     * false：一直执行
     * 出现错误时，是否继续发送事件；这个方法没有参数传递，不能根据上面的数量去判断是否还有执行
     *
     * retryWhen
     * 当出现错误时，判断该错误，如果要继续执行，就发送一个observable，然后就会继续执行；但是错误后面的事件不在发送
     */
    var times = 1
    Observable.create<Int> {
        it.onNext(1)
        it.onNext(2)
        it.onNext(3)
        it.onError(Exception("911"))
        it.onNext(4)
    }
//        .retryUntil {
//            println("retryUntil ")
//            times++
//            times > 3// 执行三次
//        }
        .retryWhen {
            it.flatMap {
                if (it.toString() == "java.lang.Exception: 911") {
                    Observable.just(66)
                } else {
                    Observable.error(Throwable("停止"))
                }
            }
        }
        .subscribe(mIntObserver)
}

fun mainRepeat() {
    /**
     * repeat
     * 不会执行onComplete，会执行x次结束
     * repeatWhen
     * 不太理解
     */
    Observable.create<Int> {
        it.onNext(5)
        it.onNext(6)
        it.onNext(7)
        it.onComplete()
    }
//        .repeat(2)
        .repeatWhen {
            //                return Observable.empty<Int>()
            Observable.just(1)// 这时发送之前的事件
        }
        .subscribe(mIntObserver)
}

fun main() {
    /**
     * subscribeOn
     * 指定被观察者的线程，要注意的时，如果多次调用此方法，只有第一次有效
     * observeOn
     * 指定观察者的线程，每指定一次就会生效一次
     * 不知道为什么加上Thread.currentThread().name 之后不在打印结果了
     */
    val dis = Observable.create<Int> {
        println("currentThread name= ${Thread.currentThread().name}")
        it.onNext(1)
        it.onNext(2)
        it.onNext(3)
        it.onComplete()
    }
//        .subscribeOn(Schedulers.io())
//        .subscribeOn(Schedulers.io())
        .observeOn(Schedulers.io())
        .map { t ->
            println("observeOn  ${Thread.currentThread().name}")
            t + 1
        }
        .subscribe({
            println("observeOn onNext= $it")
        }, {
            println("observeOn onError= ${it.message}")
        }, {
            println("observeOn onComplete")
        })
}