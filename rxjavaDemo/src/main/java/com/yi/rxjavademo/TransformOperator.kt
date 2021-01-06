package com.yi.rxjavademo

import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread

val mStringObserver = object : Observer<String> {
    override fun onComplete() {
        println("mStringObserver onComplete")
    }

    override fun onSubscribe(d: Disposable) {
        println("mStringObserver onSubscribe")
    }

    override fun onNext(t: String) {
        println("mStringObserver onNext $t")
    }

    override fun onError(e: Throwable) {
        println("mStringObserver onError ${e.message}")
    }
}

fun mainMap() {
    /**
     * map
     * 转换类型，返回的是一个具体类型
     */
    Observable.fromArray(1, 2, 3, 4, 5, 6)
        .map { it.toString() }
        .subscribe(mStringObserver)
}

data class Person(val name: String, val planList: List<Plan>)
data class Plan(val time: String, val content: String, val actionList: List<String>)

val persons = arrayListOf(
    Person(
        "xiaoming",
        arrayListOf(
            Plan("1-1", "1-1-java", arrayListOf("1-1-action1", "1-1-action2", "1-1-action3")),
            Plan("1-2", "1-2-java", arrayListOf("1-2-action1", "1-2-action2", "1-2-action3")),
            Plan("1-3", "1-3-java", arrayListOf("1-3-action1", "1-3-action2", "1-3-action3"))
        )
    ),
    Person(
        "xiaoliang",
        arrayListOf(
            Plan("2-1", "2-1-java2", arrayListOf("2-1-action1", "2-1-action2", "2-1-action3")),
            Plan("2-2", "2-2-java2", arrayListOf("2-2-action1", "2-2-action2", "2-2-action3")),
            Plan("2-3", "2-3-java2", arrayListOf("2-3-action1", "2-3-action2", "2-3-action3"))
        )
    ),
    Person(
        "qiuzong",
        arrayListOf(
            Plan("3-1", "3-1-java3", arrayListOf("3-1-action1", "3-1-action2", "3-1-action3")),
            Plan("3-2", "3-2-java3", arrayListOf("3-2-action1", "3-2-action2", "3-2-action3")),
            Plan("3-3", "3-3-java3", arrayListOf("3-3-action1", "3-3-action2", "3-3-action3"))
        )
    )
)

fun mainFlatMap() {
    /**
     * flatMap
     * 返回的是一个Observerable，转发的事件是无序的
     * 添加delay之后发现该条数据没有执行，需要加一个延迟
     */
    val dis =
        Observable.fromIterable(persons)
            .flatMap { person ->
                Observable.fromIterable(person.planList)
            }
            .flatMap { plan -> Observable.fromIterable(plan.actionList) }
//            .subscribe(mStringObserver)

    thread {
        val dis2 = Observable.fromIterable(persons)
            .flatMap { person ->
                if (person.name == "xiaoliang") {
                    Observable.fromIterable(person.planList).delay(10, TimeUnit.MILLISECONDS)
                } else {
                    Observable.fromIterable(person.planList).delay(5, TimeUnit.MILLISECONDS)
                }
            }.subscribe({
                println("flatMap delay onNext ${it.content}")
            }, {
                println("flatMap delay onError ${it.message}")
            }, {
                println("flatMap delay onComplete ")
            })
        Thread.sleep(20)
    }

}

fun mainConcatMap() {
    /**
     *  concatMap 类似 flatMap 区别：按照顺序执行
     */
    thread {
        val dis = Observable.fromIterable(persons)
            .concatMap { person ->
                if (person.name == "xiaoliang") {
                    Observable.fromIterable(person.planList).delay(10, TimeUnit.MILLISECONDS)
                } else {
                    Observable.fromIterable(person.planList)
                }
            }
            .concatMap { plan -> Observable.fromIterable(plan.actionList) }
            .subscribe(mStringObserver)
        Thread.sleep(20)
    }

}

val strings = arrayOf("1", "2", "3", "4", "5", "6")
fun mainBuffer() {
    /**
     * buffer
     * count 积累数量发射出去
     * skip 发射之后从哪个位置开始
     */
    val dis = Observable.just("1", "2", "3", "4", "5", "6")
        .buffer(2, 2)
        .subscribe({
            println("buffer onNext $it")
        }, {
            println("buffer onError ${it.message}")
        }, {
            println("buffer onComplete ")
        })
}

fun mainGroupBy() {
    /**
     * groupBy
     * 按照GroupedObservable中的结果为key进行分组
     */
    val dis = Observable.just("1", "2", "3", "4", "5", "6")
        .groupBy { (it.toInt() > 3).toString() }
        .subscribe({
            println("groupBy onNext ${it.key}")
            it.subscribe(mStringObserver)
        }, {
            println("groupBy onError ${it.message}")
        }, {
            println("groupBy onComplete ")
        })
}

fun mainScan() {
    /**
     * scan
     * 将数据以一定的逻辑聚合起来
     * 聚合的时候数据类型不能改变
     * 1*2*3*4*5*6
     */
    Observable.just(1, 2, 3, 4, 5, 6)
        .scan { t1: Int, t2: Int ->
            t1 * t2
        }
        .subscribe(mIntObserver)
}

fun main() {
    /**
     * window
     * 指定数量的事件为一组
     * vs buffer 感觉类似group
     */
    val dis = Observable.just(1, 2, 3, 4, 5, 6)
        .window(2)
        .subscribe {
            println("window onNext $it")
            it.subscribe(mIntObserver)
        }
}