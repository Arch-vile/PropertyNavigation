package com.moonillusions.propertynavigation;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class PropertyNavigation {

    private static final ThreadLocal<PropertyBuilder> propertyPath = new ThreadLocal<PropertyBuilder>() {
        @Override
        protected PropertyBuilder initialValue() {
            return new PropertyBuilder();
        };
    };

    private static PropertyBuilder getPathBuilder() {
        return PropertyNavigation.propertyPath.get();
    }

    public static <T> T of(Class<T> clazz) {
        getPathBuilder().clear();
        return navigate(clazz);
    }

    public static <T> T of(T object) {
        Class<T> clazz = (Class<T>) object.getClass();
        getPathBuilder().clear();
        return navigate(clazz);
    }

    public static <T> T to(Class<T> clazz) {
        getPathBuilder().setRoot(clazz);
        return navigate(clazz);
    }

    public static <T> T to(T object) {
        Class<T> clazz = (Class<T>) object.getClass();
        getPathBuilder().setRoot(clazz);
        return navigate(clazz);
    }

    private static <T> T navigate(Class<T> clazz) {

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clazz);

        enhancer.setCallback(new MethodInterceptor() {

            @Override
            public Object intercept(Object obj, Method method, Object[] args,
                    MethodProxy proxy) throws Throwable {

                if (method.getReturnType().getCanonicalName()
                        .equals("groovy.lang.MetaClass")) {
                    return proxy.invokeSuper(obj, args);
                }

                if (method.getName().equals("invokeMethod")) {
                    throw new NoSuchMethodError("No such method: " + args[0]);
                }

                getPathBuilder().append(method);

                if (Modifier.isFinal(method.getReturnType().getModifiers())) {
                    return proxy.invokeSuper(obj, args);
                } else {
                    return navigate(method.getReturnType());
                }
            }
        });

        T proxy = (T) enhancer.create();
        return proxy;
    }

    public static String prop(Object object) {
        return getPathBuilder().toProperty();
    }

}
