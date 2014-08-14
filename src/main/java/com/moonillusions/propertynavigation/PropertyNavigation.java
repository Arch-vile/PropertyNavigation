package com.moonillusions.propertynavigation;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class PropertyNavigation {

	private static final ThreadLocal<StringBuilder> propertyPath = new ThreadLocal<StringBuilder>() {
		@Override
		protected StringBuilder initialValue() {
			return new StringBuilder();
		};
	};

	public static <T> T of(Class<T> clazz) {
		PropertyNavigation.propertyPath.get().setLength(0);
		PropertyNavigation.propertyPath.get().append(clazz.getSimpleName());

		return navigate(clazz);
	}

	public static <T> T navigate(Class<T> clazz) {

		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(clazz);

		enhancer.setCallback(new MethodInterceptor() {

			@Override
			public Object intercept(Object obj, Method method, Object[] args,
					MethodProxy proxy) throws Throwable {
				PropertyNavigation.propertyPath.get().append(".")
						.append(method.getName());

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
		return PropertyNavigation.propertyPath.get().toString();
	}

}
