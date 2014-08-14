package com.moonillusions.propertynavigation.utils;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class MethodNameStringUtilsTest {

	@Test
	public void getter_method_to_prop() {
		assertThat(MethodNameStringUtils.methodToProperty("getTemperature"),
				equalTo("temperature"));
	}

	@Test
	public void method_to_prop() {
		assertThat(MethodNameStringUtils.methodToProperty("gimmeSome"),
				equalTo("gimmeSome"));
	}

	@Test
	public void uppercase_method_to_prop() {
		assertThat(MethodNameStringUtils.methodToProperty("GimmeSome"),
				equalTo("gimmeSome"));
	}

}
