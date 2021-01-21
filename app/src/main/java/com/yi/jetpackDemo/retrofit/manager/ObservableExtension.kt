package com.yi.jetpackDemo.retrofit.manager

import com.yi.jetpackDemo.retrofit.entiry.Result
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T> Observable<T>.subscribeOnIO(): Observable<T> {
    return subscribeOn(Schedulers.io())
}

fun <T> Observable<T>.observeOnMainThread(): Observable<T> {
    return observeOn(AndroidSchedulers.mainThread())
}

fun <R> Observable<Result<R>>.convert(): Observable<R> {
    return map(ResultFunc())
}

fun <T> Observable<T>.async(): Observable<T> = subscribeOnIO().observeOnMainThread()
