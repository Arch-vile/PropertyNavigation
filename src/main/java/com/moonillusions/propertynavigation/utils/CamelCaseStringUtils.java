package com.moonillusions.propertynavigation.utils;

public class CamelCaseStringUtils {

	public static String startWithLowerCase(String methodName) {
		if(methodName.length() > 1 && Character.isUpperCase(methodName.charAt(1))) return methodName;
		char firstChar = Character.toLowerCase(methodName.charAt(0));
		return firstChar + methodName.substring(1);
	}

}
