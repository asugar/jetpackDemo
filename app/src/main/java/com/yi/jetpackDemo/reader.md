# android 渲染速度

## GPU渲染
### 开启设备GPU渲染方法：手机-开发者选项-GPU显现模式分析--在屏幕显示为条形图/在adb shell dumpsys gfxinfo 中
### 如何看条形图
每个竖条代表一个帧，竖条高度代表了该帧花的时间
水平绿线表示16ms。就是每秒60帧

竖条会有不同的颜色，不同颜色代表不同的过程

参考链接：https://developer.android.com/topic/performance/rendering/inspect-gpu-rendering?hl=zh-cn


## 使用 Traceview 检查跟踪日志

traceview已经启用，可以使用命令行：dmtracedump 生成跟踪日志文件的图形化调用堆栈图
dmtracedump使用说明：https://developer.android.com/studio/command-line/dmtracedump?hl=zh-cn
todo dmtracedump 命令不会用

使用sdk目录下：C:\Users\59292\AppData\Local\Android\Sdk\tools monitor工具


参考链接：https://developer.android.com/studio/profile/traceview?hl=zh-cn


## Systrace 方法
View->Tools Window->Profile


 adb shell dumpsys gfxinfo com.gaosi.masterapp
 
 
## EasyPermissions 
如何用
 
 
参考链接：https://github.com/googlesamples/easypermissions
  
 
## ContentProvider
 
 
## 相册库得数据
 
