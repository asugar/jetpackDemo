package com.yi.rxjavademo

import io.reactivex.Observable

/**
 * 需求：依次执行多个网络请求，上个网络请求的结果是下个网络请求的参数，如何使用rxjava实现
 * 使用flatmap
 * concatMap
 */
fun main() {
    val dis = Observable.just(1)
        .concatMap { it -> analogNetwork(it) }
        .concatMap { it -> analogNetwork2(it) }
        .subscribe {
            System.out.println("$it")
        }
}

private fun analogNetwork(num: Int): Observable<String> {
    return Observable.create<String> {
        it.onNext("analogNetwork-".plus(num))
    }
}

private fun analogNetwork2(str: String): Observable<String> {
    return Observable.create<String> {
        it.onNext(str.plus("-analogNetwork2"))
    }
}