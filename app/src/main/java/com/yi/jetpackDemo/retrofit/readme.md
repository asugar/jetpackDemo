# 引入retrofit
```groovy

```


# 解决多base得网络


# 处理h5网络请求


# 将代码引进到校长端


# 问题
h5请求网络，使用app得能力，有个接口使用了一个数组，发现接口无法处理classTimeList=[-1,-2]，只能处理classTimeList="-1,-2

gson解析后发现int -->Double 了，需要使用typeAdapter处理 -- ok
自定义解析可以处理部分问题，但是解析规则不能时无线嵌套的
