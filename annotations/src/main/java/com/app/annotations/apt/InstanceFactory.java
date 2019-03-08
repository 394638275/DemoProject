package com.app.annotations.apt;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * author: LQ
 * date: 2019/3/7 14:59
 * description: 实例化注解,会被主动添加到实例化工厂,自动生成new来替换掉反射的newInstance代码
 */
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface InstanceFactory {
}
