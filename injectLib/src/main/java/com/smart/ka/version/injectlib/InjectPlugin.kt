package com.smart.ka.version.injectlib

import org.gradle.api.Plugin
import org.gradle.api.Project

class InjectPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        //Task注册
        project.tasks.register("injectPlugin") {
            //参数
            it.group = "customPlugin"
            it.description = "custom custom plugin"
            it.doLast {
                println("start test custom plugin!!")
            }
        }
    }
}
