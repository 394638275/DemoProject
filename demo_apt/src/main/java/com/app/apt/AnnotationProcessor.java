package com.app.apt;

import com.app.apt.processor.InstanceProcessor;
import com.google.auto.service.AutoService;

import java.lang.annotation.Annotation;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;

/**
 * author: LQ
 * date: 2019/3/8 16:46
 * description:
 */
@AutoService(Processor.class)
@SupportedAnnotationTypes({//标注注解处理器支持的注解类型
        "com.app.annotations.apt.InstanceFactory"
//        "com.app.annotations.apt.ApiFactory",
//        "com.app.annotations.apt.Router"
})
public class AnnotationProcessor extends AbstractProcessor {
    private Filer mFiler; //文件相关的辅助类
    private Elements mElementUtils; //元素相关的辅助类  许多元素
    private Messager mMessager; //日志相关的辅助类

    private Map<String, AnnotatedClass> mAnnotatedClassMap;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        mFiler = processingEnv.getFiler();
        mElementUtils = processingEnv.getElementUtils();
        mMessager = processingEnv.getMessager();
        mAnnotatedClassMap = new TreeMap<>();
    }

    //这个方法是核心方法，在这里处理的你的业务。检测类别参数，胜场java文件等
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        mAnnotatedClassMap.clear();

//        try {
//            processActivityCheck(roundEnv);
//        } catch (Exception e) {
//            e.printStackTrace();
//            error(e.getMessage());
//        }
//
//        for (AnnotatedClass annotatedClass : mAnnotatedClassMap.values()) {
//            try {
//                annotatedClass.generateActivityFile().writeTo(mFiler);
//            } catch (Exception e) {
//                error("Generate file failed, reason: %s", e.getMessage());
//            }
//        }

        new InstanceProcessor().process(roundEnv, this);
        return true;
    }


//    private void processActivityCheck(RoundEnvironment roundEnv) throws IllegalArgumentException, ClassNotFoundException {
//        //check ruleslass forName(String className
//        for (Element element : roundEnv.getElementsAnnotatedWith((Class<? extends Annotation>) Class.forName(Utils.ANNOTATION_PATH))) {
//            if (element.getKind() == ElementKind.CLASS) {
//                getAnnotatedClass(element);
//            } else
//                error("ActivityInject only can use  in ElementKind.CLASS");
//        }
//    }

//    private AnnotatedClass getAnnotatedClass(Element element) {
//        // tipe . can not use chines  so  ....
//        // get TypeElement  element is class's --->class  TypeElement typeElement = (TypeElement) element
//        //  get TypeElement  element is method's ---> TypeElement typeElement = (TypeElement) element.getEnclosingElement();
//        TypeElement typeElement = (TypeElement) element;
//        String fullName = typeElement.getQualifiedName().toString();
//        AnnotatedClass annotatedClass = mAnnotatedClassMap.get(fullName);
//        if (annotatedClass == null) {
//            annotatedClass = new AnnotatedClass(typeElement, mElementUtils, mMessager);
//            mAnnotatedClassMap.put(fullName, annotatedClass);
//        }
//        return annotatedClass;
//    }


    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }


    //这个个方法返回你要处理注解的类型
//    @Override
//    public Set<String> getSupportedAnnotationTypes() {
//        Set<String> types = new LinkedHashSet<>();
//        types.add(Utils.ANNOTATION_PATH);
//        return types;
//    }

//    private void error(String msg, Object... args) {
//        mMessager.printMessage(Diagnostic.Kind.ERROR, String.format(msg, args));
//    }

    public Filer getFiler() {
        return mFiler;
    }

    public Elements getElementUtils() {
        return mElementUtils;
    }

    public Messager getMessager() {
        return mMessager;
    }

    public Map<String, AnnotatedClass> getAnnotatedClassMap() {
        return mAnnotatedClassMap;
    }
}
