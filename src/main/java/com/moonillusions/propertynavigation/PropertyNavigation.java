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
		getPathBuilder().setRoot(clazz);
		return navigate(clazz);
	}

	public static <T> T navigate(Class<T> clazz) {

		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(clazz);

		enhancer.setCallback(new MethodInterceptor() {

			@Override
			public Object intercept(Object obj, Method method, Object[] args,
					MethodProxy proxy) throws Throwable {
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
