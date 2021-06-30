package com.yi.demo

import com.android.build.gradle.AppExtension
import com.android.build.gradle.api.ApkVariantOutput
import com.android.build.gradle.api.ApplicationVariant
import com.android.build.gradle.api.BaseVariantOutput
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * plugin demo
 */
public class DemoPlugin implements Plugin<Project> {
    public void apply(Project project) {
        // Register a task
        project.tasks.register("demo") {
            doFirst {
                println("-------- Hello from plugin 'com.yi.diplugin.greeting'")
            }
            doLast {
                println("Hello from plugin 'com.yi.diplugin.greeting'")
            }
        }

        getBaseInfo()

//        project.getExtensions().findByType(BaseExtension.class)
//        .registerTransform(ne MyCu)
    }

    private void getBaseInfo() {
        def projectname = project.name
        println("projectname= $projectname")
        def buildTime = new Date().format("yyyy-MM-dd HH-mm-ss")
        println("buildTime= $buildTime")
        def appExtension = project.extensions.getByType(AppExtension)
        appExtension.applicationVariants.all { variant ->
            def versionCode = ((ApplicationVariant) variant).versionCode
            def versionName = ((ApplicationVariant) variant).versionName
            def buildType = ((ApplicationVariant) variant).buildType.name
            def flavor = ((ApplicationVariant) variant).flavorName
            println("versionCode= $versionCode versionName= $versionName buildType= $buildType flavor= $flavor")
            ((ApplicationVariant) variant).outputs.all { output ->
//                 变体输出的除了apk还有其他文件，这里我们只修改apk的文件名
                def outputFile = ((BaseVariantOutput) output).outputFile
                if (outputFile != null && outputFile.name.endsWith(".apk")) {
                    ((ApkVariantOutput) output).outputFileName = "$projectName-$versionCode-$versionName-$buildType-$flavor-${buildTime}.apk"
                }
            }
        }
    }

}
