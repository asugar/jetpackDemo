package com.yi.rxjavademo

import io.reactivex.Observable
import io.reactivex.functions.BiConsumer
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Consumer
import io.reactivex.functions.Function
import java.lang.NullPointerException
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread

fun mainConcat() {
    /**
     * concat
     * 将多个观察者组合在一起，按照顺序执行，最多有4个事件
     */
    Observable.concat(
        Observable.just(1, 2),
        Observable.just(3, 4),
        Observable.just(5, 6)
    ).subscribe(mIntObserver)
}

fun mainConcatArray() {
    /**
     * concatArray 类似concat
     * 数量可以超过4个
     */
    Observable.concatArray(
        Observable.just(1, 2),
        Observable.just(3, 4),
        Observable.just(5, 6),
        Observable.just(7, 8),
        Observable.just(9, 10),
        Observable.just(11, 12)
    ).subscribe(mIntObserver)
}

fun mainMerge() {
    /**
     * merge
     * 用法和concat基本一样，只是concat是串行发送事件，merge并行发送事件
     */
    thread {
        Observable.merge(
//        Observable.concat(
            Observable.intervalRange(0, 6, 2, 2, TimeUnit.SECONDS).map {
                "A - $it"
            },
            Observable.intervalRange(0, 6, 2, 2, TimeUnit.SECONDS).map {
                "B - $it"
            }
        ).subscribe(mStringObserver)
        Thread.sleep(28 * 1000)
    }

    /**
     * mergeArray vs merge
     * 超过4个，    vs 4个以内
     */
}

fun mainConcatArrayDelayError() {

    /**
     * concatArray or mergeArray onError的时候会中断所有事件
     * concatArrayDelayError or mergeArrayDelayError onError的时候会执行完剩下的所有事件
     */
    Observable.concatArrayDelayError(Observable.create {
        it.onNext(1)
        it.onError(NullPointerException())
    }, Observable.just(2, 3, 4))
        .subscribe(mIntObserver)
}

fun mainZip() {
    /**
     * zip
     * 将多个被观察者合并
     * 可以是一个被观察者，也可以是多个被观察者
     */
    Observable.zip(
        Observable.just(1, 2, 3),
        Observable.just(4, 5, 6),
        BiFunction<Int, Int, String> { t1, t2 ->
            (t1 + t2).toString()
        }
    ).subscribe(mStringObserver)
}

fun mainCombineLatest() {
    /**
     * combineLatest 和 zip 类似
     * 取最后一个事件去和其他事件结合
     */
//    Observable.combineLatest(
//        Observable.just(1, 2, 3).map {
//            println("combineLatest map a $it")
//            it
//        },
//        Observable.just(4, 5, 6).map {
//            println("combineLatest map b $it")
//            it
//        },
//        BiFunction<Int, Int, String> { t1, t2 ->
//            (t1 + t2).toString()
//        }
//    ).subscribe(mStringObserver)

    // todo 此处的Function中any没有值
    Observable.combineLatestDelayError(
        Function<Any, String> {
            val data = it as? Array<Int>
            println("combineLatestDelayError $data")
            it.toString()
        },
        2,
        Observable.just(1, 2, 3).map {
            println("combineLatest map a $it")
            it
        },
        Observable.just(4, 5, 6).map {
            println("combineLatest map b $it")
            it
        }
    ).subscribe(mStringObserver)
}

fun mainReduce() {
    /**
     * reduce
     * 将所有的数据聚合在一起发送
     */
    val dis = Observable.just(1, 2, 3, 4, 5, 6)
        .reduce { t1, t2 -> t1 + t2 }
        .subscribe {
            println("reduce onNext $it")
        }
}

fun mainCollect() {
    /**
     * collect
     * 将数据收集到数据结构当中，相当于重新拼接组装数据
     */
    val dis = Observable.just(1, 2, 3, 4, 5, 6)
        .collect({
            ArrayList<Int>()
        }, { al, t2 ->
            al.add(t2)
        })
        .subscribe({
            println("collect onNext $it")
        }, {
            println("collect onError ${it.message}")
        })
}

fun mainStartWith() {
    /**
     * startWith or startWithArray
     * 再发送事件之前追加事件
     */
    Observable.just(4, 5, 6)
        .startWithArray(1, 2, 3)
        .startWith(0)
        .subscribe(mIntObserver)
}

fun mainCount() {
    /**
     * count
     * 计算总数
     */
    val dis = Observable.just(1, 2, 3)
        .count()
        .subscribe(Consumer<Long> {
            println("count onNext $it")
        })

}
