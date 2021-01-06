package com.yi.rxjavademo

import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

/**
 * 需求：依次执行多个网络请求，上个网络请求的结果是下个网络请求的参数，如何使用rxjava实现
 * 使用flatmap
 * concatMap
 * 疑问：
 * 1）添加线程切换的时候就会报错
 * 2）每个concat中执行onComplete之后才会在最后的onComplete里执行
 */
fun main2() {
    val dis = Observable.just(1)
//        .observeOn(Schedulers.newThread())
        .concatMap { it ->
            analogNetwork(it)
        }
//        .observeOn(Schedulers.io())
        .concatMap { it ->
            analogNetwork2(it)
        }
        .subscribe({
            println("onNext $it")
        }, {
            println("onError ${it.message}")
        }, {
            println("onComplete")
        })
}

private fun analogNetwork(num: Int): Observable<String> {
    return Observable.create<String> {
        println("--- analogNetwork  $num  ${Thread.currentThread().name}")
        it.onNext("analogNetwork-".plus(num))
        it.onComplete()// 所有位置执行，总体才结束结束
    }
}

private fun analogNetwork2(str: String): Observable<String> {
    return Observable.create<String> {
        println("--- analogNetwork2 $str ${Thread.currentThread().name} ")
        it.onNext(str.plus("-analogNetwork2"))
        it.onComplete()
    }
}

/**
 * 依次执行多个操作，上个操作依赖下一个操作结果，中间有UI交互
 */
fun main() {
    val dis = Observable.create<Int> {
        it.onNext(1)
    }
        .observeOn(Schedulers.io())
        .map {
            println("---1---  $it  ${Thread.currentThread()}")
            it * 2
        }
        .observeOn(Schedulers.newThread())
        .map {
            println("---2---  $it  ${Thread.currentThread()}")
            it * 2
        }
//        .subscribeOn(Schedulers.io())
        .subscribe({
            println("---onNext--- $it ${Thread.currentThread()} ")
        }, {
            println("---onError--- ${it.message} ${Thread.currentThread()} ")
        }, {
            println("---onComplete--- ")
        })
}



