# Gradle插件的作用和好处
Gradle插件可以做什么呢？主要有以下的几点
- 为项目配置依赖。

- 为项目配置约定，比如约定源代码的存放位置。

- 为项目添加任务，完成测试、编译、打包等任务。

- 为项目中的核心对象和其他插件的对象添加拓展类型。

使用Gradle插件主要有以下的好处：

- 重用和减少维护在多个项目类似的逻辑的开销。

- 更高程度的模块化。

- 封装必要的逻辑，并允许构建脚本尽可能是声明性地。

# 自定义Gradle插件的实现方式
自定义Gradle插件一般有如下三种方式：

## 1.Build Script
每个Android工程里，都会有build.gradle文件。我们可以直接在build.gradle中构建一些简单的插件代码，这样做可以自动编译并包含在构建脚本的类路径中，无需执行任何操作。

首先在build.gradle中添加如下代码：

class TestPlugin implements Plugin<Project>{

    @Override
    void apply(Project project) {
        project.task("TestPlugin") {
            doLast() {
                println "start test plugin"
            }
        }
    }
}

apply plugin: TestPlugin

然后执行一下如下命令行，执行一下插件内容
./gradlew -p app TestPlugin --stacktrace

## 2.buildSrc project
除了在build.gradle中编写的自定义插件，还可以将插件的源代码放在rootProjectDir/buildSrc/src/main/groovy目录中，Gradle会自动识别来完成编译和测试。

首先需要在项目的根目录下创建一个buildSrc的文件夹，并在此文件夹下创建/src/main/groovy目录，然后同步一下项目

注：文件夹名称必须正确，因为这是Gradle指定文件夹，否则功能无法实现。

在groovy目录中创建一个groovy文件，比如我的是TestPlugin.groovy，并在groovy文件中实现相关的插件功能，如图所示：
import org.gradle.api.Plugin;
import org.gradle.api.Project;

class TestPlugin implements Plugin<Project>{

    @Override
    void apply(Project project) {
        project.task("TestPlugin") {
            doLast() {
                println "start test plugin"
            }
        }
    }
}

修改主项目的build.gradle文件，添加上相关依赖

apply plugin: TestPlugin

Terminal中输入gradlew TestPlugin 来执行TestPlugin任务，会打印出我们想要的结果。

## 3.独立项目

参考 injectLib