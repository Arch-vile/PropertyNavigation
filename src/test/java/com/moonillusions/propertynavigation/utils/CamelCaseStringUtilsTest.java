package com.moonillusions.propertynavigation.utils;


import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CamelCaseStringUtilsTest {

	@Test
	public void startWithLowerCase_simple_name() {
		assertThat(CamelCaseStringUtils.startWithLowerCase("Book"), equalTo("book"));
	}
	
	@Test
	public void startWithLowerCase_camel_case_class_name() {
		assertThat(CamelCaseStringUtils.startWithLowerCase("ScienceBook"), equalTo("scienceBook"));
	}
	
	@Test
	public void startWithLowerCase_abbreviation() {
		assertThat(CamelCaseStringUtils.startWithLowerCase("HTTP_OK"), equalTo("HTTP_OK"));
	}
	
	@Test
	public void startWithLowerCase_abbreviation_camel_case() {
		assertThat(CamelCaseStringUtils.startWithLowerCase("HTTPRequest"), equalTo("HTTPRequest"));
	}
	
	@Test
	public void startWithLowerCase_simple_name_already_with_lower() {
		assertThat(CamelCaseStringUtils.startWithLowerCase("author"), equalTo("author"));
	}
	
	@Test
	public void startWithLowerCase_camel_case_already_with_lower() {
		assertThat(CamelCaseStringUtils.startWithLowerCase("iPad"), equalTo("iPad"));
	}
	
	@Test
	public void startWithLowerCase_camel_case_single_letter() {
		assertThat(CamelCaseStringUtils.startWithLowerCase("A"), equalTo("a"));
	}
}
