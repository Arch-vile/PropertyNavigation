package com.moonillusions.propertynavigation.utils;

public class MethodNameStringUtils {

	public static String methodToProperty(String methodName) {
		if (methodName.startsWith("get")) {
			return CamelCaseStringUtils.startWithLowerCase(methodName
					.substring(3));
		}
		return CamelCaseStringUtils.startWithLowerCase(methodName);
	}

}
