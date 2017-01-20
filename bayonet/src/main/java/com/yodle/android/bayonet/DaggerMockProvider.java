package com.yodle.android.bayonet;

import static org.mockito.Mockito.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

public class DaggerMockProvider {

    private Set<Class> realClasses = new HashSet<>();

    public void init(Object testClass) {
        realClasses = extractRealClasses(testClass.getClass(), new HashSet<Class>());
    }

    public <T> T provide(T t) {
        if (shouldMock(t.getClass())) {
            return (T) mock(t.getClass());
        } else {
            return t;
        }
    }

    public <T> boolean shouldMock(Class<T> tClass) {
        return !realClasses.contains(tClass);
    }

    private Set<Class> extractRealClasses(Class<?> annotatedClass, Set<Class> realClasses) {
        if (annotatedClass == Object.class) {
            return realClasses;
        }

        Field[] fields = annotatedClass.getDeclaredFields();
        for (Field field : fields) {
            for (Annotation annotation : field.getAnnotations()) {
                if (annotation.annotationType().equals(RealClass.class)) {
                    realClasses.add(field.getType());
                    break;
                }
            }
        }

        return extractRealClasses(annotatedClass.getSuperclass(), realClasses);
    }

    Set<Class> getRealClasses() {
        return realClasses;
    }
}