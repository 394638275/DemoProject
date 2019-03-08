package com.mapleleaf.plugins

import com.mapleleaf.plugins.helper.ClassUtils
import javassist.ClassPool
import org.gradle.api.Project

class ClassInjector {
    private static final ClassPool pool = ClassPool.getDefault()

    static void injectDir(String path, String packageName, Project project) {
        pool.appendClassPath(path)
        //project.android.bootClasspath 加入android.jar，否则找不到android相关的所有类
        pool.appendClassPath(project.android.bootClasspath[0].toString())
        ClassUtils.importBaseClass(pool)
        File dir = new File(path)

        if (!dir.isDirectory()) {
            return
        }
        dir.eachFileRecurse { File file ->

        }

    }
}