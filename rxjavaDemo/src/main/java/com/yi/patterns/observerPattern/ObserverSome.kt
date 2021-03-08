package com.yi.patterns.observerPattern

class ObserverSome {
    private var mListener: SomeListener? = null

    fun setSomeListener(listener: SomeListener) {
        this.mListener = listener
    }

    fun notifySome() {
        mListener?.doSomeThings()
    }

}