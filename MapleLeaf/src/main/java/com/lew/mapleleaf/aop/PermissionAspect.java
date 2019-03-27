package com.lew.mapleleaf.aop;

import android.app.Activity;

import com.app.annotations.aspect.RequestPermission;
import com.lew.mapleleaf.ui.MapleLeafApplication;
import com.lew.mapleleaf.utils.app.PermissionUtils;
import com.lew.mapleleaf.utils.logger.Logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * author: LQ
 * date: 2019/3/12 11:28
 * description: 权限切面
 */
@Aspect
public class PermissionAspect {
    @Pointcut("execution(@com.app.annotations.aspect.RequestPermission * *(..)) && @annotation(permission)")
    public void methodAnnotationd(RequestPermission permission) {
    }

    @Around("methodAnnotationd(permission)")
    public void aroundJoinPoint(ProceedingJoinPoint joinPoint, RequestPermission permission) throws Throwable {
        Activity activity = MapleLeafApplication.getInstance().getCurrentActivity();
        String[] permissions = permission.value();
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
