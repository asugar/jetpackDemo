package com.yi.rxjava

import com.yi.rxjavademo.mIntObserver
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.Observer
import io.reactivex.functions.Function
import io.reactivex.functions.Predicate
import java.util.concurrent.Callable
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread

/**
 * Filtering Observables
 * 有针对性的发射item
 * @author wanghuayi
 * @version
 * @since 2021/4/22
 */

/**
 * debounce 去抖动
 * 间隔不是累计，是单次必须大于间隔
 */
private fun testDebounce() {
    Observable.create<Int> {
        it.onNext(1)
        Thread.sleep(110)
        it.onNext(2)
        Thread.sleep(50)
        it.onNext(3)
        Thread.sleep(50)
        it.onNext(4)
        Thread.sleep(50)
        it.onNext(5)
        Thread.sleep(50)
        it.onNext(6)
    }
        .debounce(100, TimeUnit.MILLISECONDS)
        .subscribe(mIntObserver)
}

/**
 * distinct
 * 去重，利用的set，可以自定义去重对象和去重规则
 */
private fun testDistinct() {

    data class Person(var id: Int, var name: String) {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Person

            if (id != other.id) return false
            if (name != other.name) return false

            return true
        }

        override fun hashCode(): Int {
            var result = id
            result = 31 * result + name.hashCode()
            return result
        }
    }

    Observable.just(
        Person(1, "android"),
        Person(1, "android2"),
        Person(1, "ios"),
        Person(2, "ios2"),
        Person(3, "java"),
        Person(3, "java")
    )
        .distinct(object : Function<Person, Person> {
            override fun apply(t: Person): Person {
                return t
            }
        }, object : Callable<HashSet<Person>> {
            override fun call(): HashSet<Person> {
                return HashSet()
            }
        })
        .subscribe {
            println("testDistinct ${it.id}")
        }

}

/**
 * elementAt
 * 只发射一个item，index索引从0开始
 */
private fun testElementAt() {
    Observable.just(1, 2, 3, 4, 5, 6)
        .elementAt(3, 6)
        .subscribe({
            println("testElementAt $it")
        }, {
            println("testElementAt ${it.message}")
        })
}

/**
 * filter
 * Predicate
 */
private fun testFilter() {
    Observable.just(1, 2, 3, 4, 5, 6)
        .filter(object : Predicate<Int> {
            override fun test(t: Int): Boolean {
                return t % 2 == 0
            }
        }).subscribe(mIntObserver)
}

/**
 * first
 * 只发送符合条件的第一个item
 * 里面使用的是elementAt
 */
private fun testFirst() {
//    Observable.just(1, 2, 3, 4, 5, 6)
    Observable.create<Int> { it.onComplete() }
        .first(2)
        .subscribe({
            println("testFirst $it")
        }, {
            println("testFirst ${it.message}")
        })

    Observable.just(1, 2, 3, 4, 5, 6)
//        .last(9)
        .lastElement()
        .subscribe({
            println("testLast $it")
        }, {
            println("testLast ${it.message}")
        })
}

/**
 * ignoreElements
 * 忽略所有的item，但是会收到停止的通知
 */
private fun testIgnoreElements() {
    Observable.just(1, 2, 3, 4, 5, 6)
        .ignoreElements()
        .subscribe {
            println("testIgnoreElements complete")
        }
}

/**
 * sample 采样率
 * todo 不太理解
 */
private fun testSample() {
//    Observable.just(1, 2, 3, 4, 5, 6)
    Observable.create<Int> {
        it.onNext(1)
        Thread.sleep(1000)
        it.onNext(2)
        Thread.sleep(1000)
        it.onNext(3)
        Thread.sleep(1000)
        it.onNext(4)
        Thread.sleep(1000)
        it.onNext(5)
        Thread.sleep(1000)
        it.onNext(6)
    }
//        .sample(object : ObservableSource<String> {
//            override fun subscribe(observer: Observer<in String>) {
//                observer.onNext("sample")
//            }
//        })
        .sample(1000, TimeUnit.MILLISECONDS)
        .subscribe({
            println("testSample $it")
        }, {
            println("testSample ${it.message}")
        })
}

/**
 * skip
 * 不发送前n个item或者前time时间内的
 * skipLast
 * 类似skip，不发送后n个item
 */
private fun testSkip() {
    Observable.just(1, 2, 3, 4, 5, 6)
        .skip(2)
        .skipLast(2)
        .subscribe(mIntObserver)
}

/**
 * take和skip正好相反
 * 只发送前n个item
 * takeLast
 */
private fun testTake() {
    Observable.just(1, 2, 3, 4, 5, 6)
        .take(4)
        .takeLast(2)
        .subscribe(mIntObserver)
}


fun main() {
    testTake()
//    testSkip()
//    thread {
//        testSample()
//        Thread.sleep(20000)
//    }
//    testIgnoreElements()
//    testFirst()
//    testFilter()
//    testElementAt()
//    testDistinct()
//    thread {
//        testDebounce()
//        Thread.sleep(1000)
//    }
}