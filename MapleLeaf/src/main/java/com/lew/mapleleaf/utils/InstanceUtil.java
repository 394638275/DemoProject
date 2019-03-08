package com.lew.mapleleaf.utils;

import com.apt.InstanceFactory;

/**
 * author: LQ
 * date: 2019/3/7 14:18
 * description:
 */
public class InstanceUtil {

    @SuppressWarnings("unchecked")
    public static <T> T getInstance(Class clz) {
        try {
            return (T) InstanceFactory.create(clz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
