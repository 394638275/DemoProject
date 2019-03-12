package com.lew.mapleleaf.aop;

import android.app.Activity;

import com.app.annotations.aspect.RequestPermission;
import com.lew.mapleleaf.ui.MapleLeafApplication;
import com.lew.mapleleaf.utils.app.PermissionUtils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * author: LQ
 * date: 2019/3/12 11:28
 * description: 权限切面
 */
@Aspect
public class PermissionAspect {
    @Around("execution(@com.app.annotations.aspect.RequestPermission * *(..)) && @annotation(permission)")
    public void aroundJoinPoint(ProceedingJoinPoint joinPoint, RequestPermission permission) throws Throwable {
        Activity activity = MapleLeafApplication.getInstance().getCurrentActivity();
        String[] permissions = permission.values();
        boolean hasPermission = PermissionUtils.checkPermissions(activity, permissions);
        if (hasPermission) {
            joinPoint.proceed();
        } else {
            PermissionUtils.showRequestPermissionDialog(activity, permissions, new PermissionUtils.OnPermissionListener() {
                @Override
                public void onPermissionGranted() {
                    try {
                        joinPoint.proceed();
                    } catch (Throwable throwable) {
                        throwable.printStackTrace();
                    }
                }

                @Override
                public void onPermissionDenied() {
                    PermissionUtils.showTipsDialog(activity);
                }
            });
        }
    }
}
