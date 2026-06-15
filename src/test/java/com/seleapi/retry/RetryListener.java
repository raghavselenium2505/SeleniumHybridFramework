package com.seleapi.retry;

import org.apache.log4j.Logger;
import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class RetryListener
        implements IAnnotationTransformer {

    public static Logger log =
            Logger.getLogger(RetryListener.class);

    @Override
    public void transform(
            ITestAnnotation annotation,
            Class testClass,
            Constructor testConstructor,
            Method testMethod) {

        annotation.setRetryAnalyzer(
                RetryAnalyzer.class);

        log.info(
                "Retry Analyzer Added For : "
                + testMethod.getName());
    }
}