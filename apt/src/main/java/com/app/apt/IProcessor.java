package com.app.apt;

import javax.annotation.processing.RoundEnvironment;

public interface IProcessor {
    void process(RoundEnvironment roundEnvironment, AnnotationProcessor mProcessor);
}
