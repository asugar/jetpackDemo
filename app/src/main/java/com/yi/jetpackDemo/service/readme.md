# servcie

## 是什么，分几类
提供一些类似服务的后台任务
前台；后台；绑定
startService和bindService
自己调用：stopSelf或者别人调用stopService来停止startService的服务
bindService则自动销毁

## 怎么用
扩展Service和IntentService可以实现自己的服务类
Service
IntentService：内部使用HandlerThread+Handler，所以是依次执行，不会并行；在内部handleMessage会自己stopSelf自动销毁；


### bindservice有三种实现
* Binder扩展
* Messenger
* AIDL
都可以实现双向通信
Messenger和AIDL适用多进程；Binder单进程


## 原理
AIDL 接口里面有Stub，利用Proxy类
底层使用的是binder通信

