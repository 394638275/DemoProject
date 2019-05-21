package com.lew.mapleleaf.aop;

import android.view.View;

import com.app.annotations.aspect.SingleClick;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

/**
 * 防双击切面
 */
@Aspect
public class SingleClickAspect {

    private static final int MIN_CLICK_INTERVAL = 600;

    @Pointcut("execution(@com.app.annotations.aspect.SingleClick * *(..))")  //方法切入点
    public void methodAnnotated() {

    }

    @Around("methodAnnotated()")
    public void aroundJointPoint(ProceedingJoinPoint joinPoint) throws Throwable {
        View view = null;
        for(Object arg : joinPoint.getArgs()){
            if(arg instanceof View) {
                view = (View)arg;
                break;
            }
        }
        if(view == null){
            return;
        }

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        String tag = joinPoint.getSourceLocation().getFileName() + joinPoint.getSourceLocation().getLine();
//        SingleClick singleClick = method.getAnnotation(SingleClick.class);

    }

}
