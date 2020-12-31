# 模拟应用被系统杀死，恢复的时候一些静态变量（单例）引用为空了

* 开启设置中不允许有后台进程
* 启动两个activity，再第一个activity初始化，进入第二个activity
* 切到后台，一顿操作，看到当前precess被杀死，回到应用中
* 执行初始化的方法，这时候会报：kotlin.UninitializedPropertyAccessException: lateinit property retrofit has not been initialized

## 通过log监听进程被杀死
12-31 15:17:50.959 27352 27375 D PowerKeeper.Event: notifyActiveKilled processName: com.yi.jetpackdemo, pid:32748, reason:empty #2
12-31 15:17:50.963  1523  7688 D ProcessManager: remove task: TaskRecord{d1f4b0 #4197 A=com.yi.jetpackdemo U=0 StackId=1 sz=2}

## 进入开发者选项，限制后台进程数，退到后台时，打开多个别的应用，直到看到自己的进程被杀死后，再回到应用