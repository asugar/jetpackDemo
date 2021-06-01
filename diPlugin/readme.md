# 关于自定义gradle插件

### 创建步骤 
* 新建文件夹名称xxxPlugin
* 执行命令：../gradlew init -> 4 -> 根据语言选择
* 删除gradle文件夹；gradlew；gradlew.bat;setting.gradle;.gradle文件夹；.idea文件夹
* 或者只拷贝：src和resources文件夹；build.gradle;
* gradle.build关键定义
```
gradlePlugin {
    // Define the plugin
    plugins {
        // 执行的gralde的任务名字
        di {
            // app 中引用plgin id
            id = 'com.yi.plugin'
            implementationClass = 'com.yi.plugin.DiPluginPlugin'
        }
    }
}
```

```
publishing {
    publications {
        // 指定Publish名字
        di(MavenPublication) {
            // 插件的组ID，建议设置为插件的包名
            groupId = 'com.yi.plugin'
            // 翻译过来是 工件ID，我的理解是插件的名字
            artifactId = 'diplugin'
            version = '1.0.0'
            // 组件类型，我们的插件其实就是Java组件
            from components.java
        }
    }
    repositories {
        maven {
            // 对应mavenRepository
            url = "$rootDir/repo"
        }
    }
}
```

### 使用步骤
* 在工程的build.gradle中引入插件，参考发布定义的groupId:artifactId:version
* 在app的build.gradle中引入插件，参考plugins定义:id
* 测试，在other中找到对应插件名，参考plugins中插件名定义


# 插件如何发布到远程：JCenter Bintry