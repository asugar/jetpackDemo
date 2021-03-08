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
* fragment的启动依赖activity，和activity是相关联的
11-20 11:00:16.019 D/launchModel(28318): ActivityA onCreate null
11-20 11:00:16.027 D/launchModel(28318): FragmentA onAttach
11-20 11:00:16.027 D/launchModel(28318): FragmentA onCreate
11-20 11:00:16.028 D/launchModel(28318): FragmentA onCreateView
11-20 11:00:16.031 D/launchModel(28318): FragmentA onViewCreated
11-20 11:00:16.062 D/launchModel(28318): FragmentA onViewStateRestored null
11-20 11:00:16.063 D/launchModel(28318): FragmentA onStart
11-20 11:00:16.063 D/launchModel(28318): ActivityA onStart
11-20 11:00:16.067 D/launchModel(28318): ActivityA onResume
11-20 11:00:16.068 D/launchModel(28318): FragmentA onResume

11-20 11:00:27.836 D/launchModel(28318): FragmentA onPause
11-20 11:00:27.837 D/launchModel(28318): ActivityA onPause
11-20 11:00:28.191 D/launchModel(28318): FragmentA onStop
11-20 11:00:28.191 D/launchModel(28318): ActivityA onStop
11-20 11:00:28.192 D/launchModel(28318): FragmentA onDestroyView
11-20 11:00:28.193 D/launchModel(28318): FragmentA onDestroy
11-20 11:00:28.193 D/launchModel(28318): FragmentA onDetach
11-20 11:00:28.194 D/launchModel(28318): ActivityA onDestroy

# fragment切换横竖屏
* activity会销毁，所以fragment也会销毁；
* activity的onSaveInstanceState和onRestoreInstanceState  vs Fragment的 onSaveInstanceState和onViewStateRestored
* onPause和onStop之间onSaveInstanceState
* activity：onStart和onResume之间onRestoreInstanceState  fragment：onViewCreated和onStart之间

# fragment预加载和懒加载
BEHAVIOR_SET_USER_VISIBLE_HINT 模式废弃，会执行setUserVisibleHint 不可见是也会执行onResume
BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT 不执行setUserVisibleHint 可见是执行onResume
setPrimaryItem --》 setMaxLifecycle

## FragmentPagerAdapter VS FragmentStatePagerAdapter
占用内存多   占用内存少 原因：再不使用的时候会销毁，消息代码再destroyItem方法中，会remove掉

# shared-element-trainsitions
## 启动fragment
1）需要再动画的view上添加 android:transitionName="shared_image"
2）此处的shared_image 对应transition中的动画文件
3）在启动的fragment的oncreate方法中指定
sharedElementEnterTransition = androidx.transition.TransitionInflater.from(requireContext())
                                            .inflateTransition(R.transition.shared_image)

## 启动activity
1）启动的activity页面使用ActivityOptionsCompat

## SingleTask：Activity 一次只能有一个实例存在
### 查看activity任务栈信息：adb shell dumpsys activity | grep com.xxx.xxx.xx
### 如果SingleTask的Activity已经存在任务中，会不会把之上的activity清栈
a--singleTask
b
c --> 启动a，b，c出栈
### 两个Task，每个task里有两个activity，其中有一个task中的Activity时SingleTask的
a(singleTask,taskAffinity="com.a") --> b(taskAffinity="com.a") --> c(taskAffinity="com.b") --> d(taskAffinity="com.b") --> 启动a
按返回键 -- 回调d
再返回 -- 回到c
再返回 -- 回首页


## 在Fragment之间传递数据

## 与其他Fragment通信

