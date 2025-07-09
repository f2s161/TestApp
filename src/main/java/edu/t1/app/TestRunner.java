package edu.t1.app;

import edu.t1.annotation.*;
import edu.t1.exception.TestCountException;
import edu.t1.exception.TestInstantiationException;
import edu.t1.exception.TestNoStaticMethodException;
import edu.t1.exception.TestPriorityException;

import java.lang.reflect.Method;
import java.util.*;
import java.util.function.Function;

public class TestRunner {
    private static final Map<String, Function<String, Object>> typesMap = new HashMap<>();

    public static void main(String[] args) {
        init();
        runTests(Sample.class);
    }

    public static void runTests(Class<?> testClass) {
        Method[] methods = testClass.getDeclaredMethods();
        validateTestMethods(methods);
        Object test;
        try {
            test = testClass.getDeclaredConstructor().newInstance();
        } catch (Exception ex) {
            throw new TestInstantiationException("отсутствует конструктор без параметров", ex);
        }
        Optional<Method> beforeSuite = Arrays.stream(methods).filter(m -> m.isAnnotationPresent(BeforeSuite.class))
                .findFirst();
        Optional<Method> afterSuite = Arrays.stream(methods).filter(m -> m.isAnnotationPresent(AfterSuite.class))
                .findFirst();
        Optional<Method> beforeTest = Arrays.stream(methods).filter(m -> m.isAnnotationPresent(BeforeTest.class))
                .findFirst();
        Optional<Method> afterTest = Arrays.stream(methods).filter(m -> m.isAnnotationPresent(AfterTest.class))
                .findFirst();
        Optional<Method> csvSource = Arrays.stream(methods).filter(m -> m.isAnnotationPresent(CsvSource.class))
                .findFirst();

        List<Method> methodList = Arrays.stream(testClass.getDeclaredMethods())
                .filter(m -> m.isAnnotationPresent(Test.class))
                .sorted(Comparator.comparing((Method m) -> m.getAnnotation(Test.class).priority())
                        .thenComparing(Method::getName))
                .toList();
        beforeSuite.ifPresent(bs -> executeMethod(bs, test));
        for (Method method : methodList) {
            beforeTest.ifPresent(b -> executeMethod(b, test));
            executeMethod(method, test);
            afterTest.ifPresent(a -> executeMethod(a, test));
        }

        csvSource.ifPresent(method -> executeCsvMethod(method, test));
        afterSuite.ifPresent(as -> executeMethod(as, test));

    }

    public static void validateTestMethods(Method[] methods) {
        long countBeforeSuite = AnnotationValidator.countAnnotationMethods(methods, BeforeSuite.class);
        if (countBeforeSuite > 1) throw new TestCountException("@BeforeSuite должна быть объявлена только 1 раз");

        long countAfterSuite = AnnotationValidator.countAnnotationMethods(methods, AfterSuite.class);
        if (countAfterSuite > 1) throw new TestCountException("@AfterSuite должна быть объявлена только 1 раз");

        if (!AnnotationValidator.isStaticMethod(methods, BeforeSuite.class))
            throw new TestNoStaticMethodException("Метод отмеченный @BeforeSuite не является статическим");
        if (!AnnotationValidator.isStaticMethod(methods, AfterSuite.class))
            throw new TestNoStaticMethodException("Метод отмеченный @AfterSuite не является статическим");
        List<String> priorityList = AnnotationValidator.checkPriority(methods);
        if (!priorityList.isEmpty()) {
            throw new TestPriorityException("параметр priority должен быть задан в диапазоне от 1 до 10: " + priorityList);
        }
    }

    public static void executeMethod(Method method, Object test) {
        try {
            method.invoke(test);
            System.out.println("Успешно выполнен метод: " + method.getName());
        } catch (Exception e) {
            System.out.println("Ошибка выполнения метода: " + method.getName()+ "->" + e);
        }
    }

    public static void executeCsvMethod(Method method, Object test) {
        String paramsStr = method.getAnnotation(CsvSource.class).params();
        String[] params = paramsStr.split(",");
        Class<?>[] parametersClass = method.getParameterTypes();
        Object[] par = new Object[method.getParameterCount()];
        for (int i = 0; i < par.length; i++) {
            Class<?> p = parametersClass[i];
            par[i] = typesMap.getOrDefault(p.getTypeName(), String::trim).apply(params[i]);
        }
        try {
            method.invoke(test, par);
        } catch (Exception e) {
            System.out.println("Ошибка выполнения метода: " + method.getName() + "->" + e);
        }
        System.out.println("Успешно выполнен метод: " + method.getName());
    }

    private static void init() {
        typesMap.put("int", Integer::valueOf);
        typesMap.put("long", Long::valueOf);
        typesMap.put("double", Double::valueOf);
        typesMap.put("boolean", Boolean::valueOf);
        typesMap.put("java.lang.String", String::valueOf);
        typesMap.put("java.lang.Integer", Integer::valueOf);
        typesMap.put("java.lang.Long", Long::valueOf);
        typesMap.put("java.lang.Double", Double::valueOf);
    }
}
