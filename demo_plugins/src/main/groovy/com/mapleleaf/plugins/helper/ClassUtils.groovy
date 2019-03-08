package com.mapleleaf.plugins.helper

import javassist.ClassPool

class ClassUtils {
    static String ErrStr = ""

    static void importBaseClass(ClassPool pool) {

        pool.importPackage("android.os.Bundle")
        pool.importPackage("com.base.event.OkBus")
        pool.importPackage("com.base.event.Event")
        pool.importPackage("android.os.Message")
    }
}