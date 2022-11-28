import org.gradle.api.Plugin;
import org.gradle.api.Project;

/**
 * 2.buildSrc project
 * Terminal中输入  gradlew TestPlugin  来执行TestPlugin任务
 */
class TestPlugin implements Plugin<Project>{

    @Override
    void apply(Project project) {

        project.task("TestPlugin") {
            doLast() {
                println "start test plugin!!"
            }
        }
    }
}
