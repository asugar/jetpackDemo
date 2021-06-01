package com.yi.di


import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * di plugin
 */
public class DiPlugin implements Plugin<Project> {
    public void apply(Project project) {
        // Register a task
        project.tasks.register("di") {
            doFirst {
                println("-------- Hello from plugin 'com.yi.diplugin.greeting'")
            }
            doLast {
                println("Hello from plugin 'com.yi.diplugin.greeting'")
            }
        }


    }

}
