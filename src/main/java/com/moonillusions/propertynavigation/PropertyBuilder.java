package com.moonillusions.propertynavigation;

import java.lang.reflect.Method;

import com.moonillusions.propertynavigation.utils.CamelCaseStringUtils;
import com.moonillusions.propertynavigation.utils.MethodNameStringUtils;

public class PropertyBuilder {

	private StringBuilder path = new StringBuilder();

	public PropertyBuilder() {

	}

	public void setRoot(Class clazz) {
		clear();
		path.append(CamelCaseStringUtils.startWithLowerCase(clazz
				.getSimpleName()));
	}

	public void append(Method method) {
		if (path.length() != 0)
			path.append(".");
		path.append(MethodNameStringUtils.methodToProperty(method.getName()));
	}

	public String toProperty() {
		return path.toString();
	}

	public void clear() {
		path.setLength(0);
	}

}
