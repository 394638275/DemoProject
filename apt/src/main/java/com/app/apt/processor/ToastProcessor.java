package com.app.apt.processor;

import com.app.apt.AnnotationProcessor;
import com.app.apt.IProcessor;

import javax.annotation.processing.RoundEnvironment;

/**
 * author: LQ
 * date: 2019/3/8 18:51
 * description:
 */
public class ToastProcessor implements IProcessor {
    @Override
    public void process(RoundEnvironment roundEnvironment, AnnotationProcessor mProcessor) {
        //check ruleslass forName(String className
//        for (Element element : roundEnvironment.getElementsAnnotatedWith((Class<? extends Annotation>) Class.forName(Utils.ANNOTATION_PATH))) {
//            if (element.getKind() == ElementKind.CLASS) {
//                getAnnotatedClass(element);
//            } else
//                error("ActivityInject only can use  in ElementKind.CLASS");
//        }
//
//
//        MethodSpec.Builder injectMethod = MethodSpec.methodBuilder(Utils.METHOD_NAME)
//                .addModifiers(Modifier.PUBLIC)
//                .addParameter(TypeName.get(mTypeElement.asType()), "activity", Modifier.FINAL);
//        injectMethod.addStatement("android.widget.Toast.makeText" + "(activity, $S,android.widget.Toast.LENGTH_SHORT).show();", "from build");
//        //generaClass
//        TypeSpec injectClass = TypeSpec.classBuilder(mTypeElement.getSimpleName() + "$$InjectActivity")
//                .addModifiers(Modifier.PUBLIC)
//                .addMethod(injectMethod.build())
//                .build();
//        String packgeName = mElements.getPackageOf(mTypeElement).getQualifiedName().toString();
    }
}
