# 启动activityA --> 退出activityA
11-19 17:53:17.320 D/launchModel(21440): ActivityA onCreate null
11-19 17:53:17.328 D/launchModel(21440): ActivityA onStart
11-19 17:53:17.331 D/launchModel(21440): ActivityA onResume

11-19 17:53:20.360 D/launchModel(21440): ActivityA onPause
11-19 17:53:20.723 D/launchModel(21440): ActivityA onStop
11-19 17:53:20.723 D/launchModel(21440): ActivityA onDestroy

# 启动activityA --> 横竖屏切换
11-19 17:48:59.159 D/launchModel(21440): ActivityA onCreate null
11-19 17:48:59.174 D/launchModel(21440): ActivityA onStart
11-19 17:48:59.177 D/launchModel(21440): ActivityA onResume

11-19 17:49:24.637 D/launchModel(21440): ActivityA onPause
11-19 17:49:24.637 D/launchModel(21440): ActivityA onSaveInstanceState
11-19 17:49:24.639 D/launchModel(21440): ActivityA onStop
11-19 17:49:24.640 D/launchModel(21440): ActivityA onDestroy
11-19 17:49:24.664 D/launchModel(21440): ActivityA onCreate Bundle[{android:viewHierarchyState=Bundle[{android:views={16908290=android.view.AbsSavedState$1@d3b44, 2131231031=android.view.AbsSavedState$1@d3b44}}], view=activityA, state=open-p, android:lastAutofillId=1073741823}]
11-19 17:49:24.678 D/launchModel(21440): ActivityA onStart
11-19 17:49:24.678 D/launchModel(21440): ActivityA onRestoreInstanceState open-p activityA
11-19 17:49:24.684 D/launchModel(21440): ActivityA onResume

# 启动A->B
11-19 17:53:54.899 D/launchModel(21440): ActivityA onCreate null
11-19 17:53:54.907 D/launchModel(21440): ActivityA onStart
11-19 17:53:54.911 D/launchModel(21440): ActivityA onResume

11-19 17:55:38.381 D/launchModel(21440): ActivityA onPause
11-19 17:55:38.391 D/launchModel(21440): ActivityB onCreate
11-19 17:55:38.400 D/launchModel(21440): ActivityB onStart
11-19 17:55:38.403 D/launchModel(21440): ActivityB onResume
11-19 17:55:38.715 D/launchModel(21440): ActivityA onSaveInstanceState
11-19 17:55:38.716 D/launchModel(21440): ActivityA onStop
# 关闭B回到A
11-19 17:56:30.819 D/launchModel(21440): ActivityB onPause
11-19 17:56:30.835 D/launchModel(21440): ActivityA onRestart
11-19 17:56:30.835 D/launchModel(21440): ActivityA onStart
11-19 17:56:30.836 D/launchModel(21440): ActivityA onResume
11-19 17:56:31.182 D/launchModel(21440): ActivityB onStop
11-19 17:56:31.182 D/launchModel(21440): ActivityB onDestroy


# fragment的执行



