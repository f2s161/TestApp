package edu.t1.app;

import edu.t1.annotation.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;

public class AnnotationValidator {
    public static long countAnnotationMethods(Method[] methods, Class<? extends Annotation> annotation) {
        return Arrays.stream(methods).filter(m -> m.isAnnotationPresent(annotation)).count();
    }

    public static boolean isStaticMethod(Method[] methods, Class<? extends Annotation> annotation) {
        List<Method> staticMethods = Arrays.stream(methods).filter(m -> m.isAnnotationPresent(annotation))
                .filter(s -> !Modifier.isStatic(s.getModifiers())).toList();
        return staticMethods.isEmpty();
    }

    public static List<String> checkPriority(Method[] methods) {
        return Arrays.stream(methods)
                .filter(m -> m.isAnnotationPresent(Test.class))
                .filter(p -> p.getAnnotation(Test.class).priority() > 10 ||
                        p.getAnnotation(Test.class).priority() < 1)
                .map(Method::getName).toList();
    }
}
