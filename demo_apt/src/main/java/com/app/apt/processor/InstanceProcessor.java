package com.app.apt.processor;

import com.app.annotations.apt.InstanceFactory;
import com.app.annotations.aspect.MemoryCache;
import com.app.apt.AnnotationProcessor;
import com.app.apt.IProcessor;
import com.app.apt.Utils;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.processing.FilerException;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.ElementFilter;
import javax.tools.Diagnostic;


/**
 * author: LQ
 * date: 2019/3/7 14:42
 * description: 注解处理器接口
 */
public class InstanceProcessor implements IProcessor {
    @Override
    public void process(RoundEnvironment roundEnvironment, AnnotationProcessor mProcessor) {
        String CLASS_NAME = "InstanceFactory";
        TypeSpec.Builder tb = TypeSpec.classBuilder(CLASS_NAME)
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .addJavadoc("@ 实例化工厂 此类由apt自动生成");

        MethodSpec.Builder mb = MethodSpec.methodBuilder("create")
                .addAnnotation(MemoryCache.class)
                .addJavadoc("@ 此方法由apt自动生成")
                .returns(Object.class)
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .addException(IllegalAccessException.class)
                .addException(InstantiationException.class)
                .addParameter(Class.class, "mClass");

        List<ClassName> mList = new ArrayList<>();
        CodeBlock.Builder blockBuilder = CodeBlock.builder();
        blockBuilder.beginControlFlow(" switch (mClass.getSimpleName())");//括号开始
        try {
            for (TypeElement element : ElementFilter.typesIn(roundEnvironment.getElementsAnnotatedWith(InstanceFactory.class))) {
                mProcessor.getMessager().printMessage(Diagnostic.Kind.NOTE, "正在处理: " + element.toString());
                if (!Utils.isValidClass(mProcessor.getMessager(), element)) return;
                ClassName currentType = ClassName.get(element);
                if (mList.contains(currentType)) continue;
                mList.add(currentType);
//             String className = null;
//                try {
//                    Class<?> clazz = element.getAnnotation(InstanceFactory.class).value();
//                    className = clazz.getCanonicalName();
//                } catch (MirroredTypeException mte) {
//                    DeclaredType classTypeMirror = (DeclaredType) mte.getTypeMirror();
//                    TypeElement classTypeElement = (TypeElement) classTypeMirror.asElement();
//                    className = classTypeElement.getQualifiedName().toString();
//                } catch (Exception e) {
//                }
//                if (className != null && !className.equals(InstanceFactory.class.getName())) {
//                    blockBuilder.addStatement("case $S: return  new $T()", currentType.simpleName(), Utils.getType(className));//初始化Repository
//                } else {
                blockBuilder.addStatement("case $S: return  new $T()", currentType.simpleName(), currentType);//初始化Presenter
                //               }
            }
            blockBuilder.addStatement("default: return mClass.newInstance()");
            blockBuilder.endControlFlow();
            mb.addCode(blockBuilder.build());
            tb.addMethod(mb.build());
            JavaFile javaFile = JavaFile.builder(Utils.PackageName, tb.build()).build();// 生成源代码
            javaFile.writeTo(mProcessor.getFiler());// 在 app module/build/generated/source/apt 生成一份源代码
        } catch (FilerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
