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

# 解决多base得网络

# 处理h5网络请求


# 将代码引进到校长端


# 问题
h5请求网络，使用app得能力，有个接口使用了一个数组，发现接口无法处理classTimeList=[-1,-2]，只能处理classTimeList="-1,-2

gson解析后发现int -->Double 了，需要使用typeAdapter处理 -- ok
自定义解析可以处理部分问题，但是解析规则不能时无线嵌套的
