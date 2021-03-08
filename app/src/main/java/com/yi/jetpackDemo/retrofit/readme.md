# 引入retrofit retrofit converter-gson adapter-rxjava2  okhttp3 gson
```groovy
implementation "com.squareup.retrofit2:retrofit:$retrofit2_version"
implementation("com.squareup.retrofit2:converter-gson:$retrofit2_version") {
    exclude group: 'com.squareup.okhttp3'
}
implementation("com.squareup.retrofit2:adapter-rxjava2:$retrofit2_version") {
    exclude group: 'com.squareup.okhttp3'
}
def okhttp3_version = "3.8.1"
implementation "com.squareup.okhttp3:okhttp:$okhttp3_version"
implementation("com.squareup.okhttp3:logging-interceptor:$okhttp3_version") {
    exclude group: 'com.android.support'
    exclude group: 'com.squareup.okhttp3'
}
implementation("com.squareup.okhttp3:okhttp-urlconnection:$okhttp3_version") {
    exclude group: 'com.squareup.okhttp3'
}
```

# 基本网络请求 -- ok

# header -- ok

# cache 
* 如何使用cache

# 解决多base得网络
* 利用全路径请求 使用注解@Url -- ok

```
@GET
fun getAppWithUrl(
    @Url url: String,
    @Query("") insId: String?
): Observable<Result<AppIndexDataResp>>
```
* 利用interceptor：请求的时候根据标记替换baseUrl -- ok
会出现illegalStateException：network interceptor " + interceptors.get(index - 1)+ " must retain the same host and port
* 利用callFactory:请求的时候根据标记替换baseUrl  -- ok

* 使用封好的三方库：https://juejin.cn/post/6844903635126583303

# 处理h5网络请求 -- ok 使用rul注解
```
/**
 * 此方法处理h5 get请求
 */
@GET
fun refreshH5Get(
    @Url url: String,
    @QueryMap map: Map<String, String>
): Observable<Result<Any>>

/**
 * 此方法处理h5 post请求
 */
@POST
fun refreshH5Post(
    @Url url: String,
    @Body body: H5RequestBody
): Observable<Result<Any>>
```

# 下载/上传

# 将代码引进到校长端


# 问题
h5请求网络，使用app得能力，有个接口使用了一个数组，发现接口无法处理classTimeList=[-1,-2]，只能处理classTimeList="-1,-2

gson解析后发现int -->Double 了，需要使用typeAdapter处理 -- ok
自定义解析可以处理部分问题，但是解析规则不能时无线嵌套的


# 增加kotlin协程+viewModel+liveData -- ok

# OkHttpClient 原理