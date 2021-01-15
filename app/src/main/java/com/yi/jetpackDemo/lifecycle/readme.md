# android lifecycle
生命周期感知型组件

# 怎么用？
```
implementation 'androidx.appcompat:appcompat:1.2.0'
```
高版本的Activity和Fragment自动实现了，可以直接使用

# 原理
LifeCycle是如何再Fragment/Activity生命周期执行时进行回调的？
```
class Fragment{
    void performCreate{
        mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_CREATE);
    }
    void performStart() {
        mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_START);
    }
}

class LifecycleRegistry{
    private void moveToState(State next) {
        if (mState == next) {
            return;
        }
        mState = next;
        if (mHandlingEvent || mAddingObserverCounter != 0) {
            mNewEventOccurred = true;
            // we will figure out what to do on upper level.
            return;
        }
        mHandlingEvent = true;
        sync();
        mHandlingEvent = false;
    }

    private void sync() {
        LifecycleOwner lifecycleOwner = mLifecycleOwner.get();
        if (lifecycleOwner == null) {
            throw new IllegalStateException("LifecycleOwner of this LifecycleRegistry is already"
                    + "garbage collected. It is too late to change lifecycle state.");
        }
        while (!isSynced()) {
            mNewEventOccurred = false;
            // no need to check eldest for nullability, because isSynced does it for us.
            if (mState.compareTo(mObserverMap.eldest().getValue().mState) < 0) {
                backwardPass(lifecycleOwner);
            }
            Entry<LifecycleObserver, ObserverWithState> newest = mObserverMap.newest();
            if (!mNewEventOccurred && newest != null
                    && mState.compareTo(newest.getValue().mState) > 0) {
                forwardPass(lifecycleOwner);
            }
        }
        mNewEventOccurred = false;
    }

}

```
最后发现是用的反射执行的Observer里的方法


# 进程的生命周期：ProcessLifecycleOwner

FragmentViewLifecycleOwner

https://developer.android.com/topic/libraries/architecture/lifecycle?hl=zh-cn
https://developer.android.com/topic/libraries/architecture/lifecycle