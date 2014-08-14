package com.moonillusions.propertynavigation;

import static com.moonillusions.propertynavigation.PropertyNavigation.of;
import static com.moonillusions.propertynavigation.PropertyNavigation.prop;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class PropertyNavigationTest {

	@Test
	public void return_method_name_shallow_call() {

		assertThat(prop(of(Book.class).getPageCount()),
				equalTo("Book.getPageCount"));

	}

	@Test
	public void return_method_name_deep_call() {

		assertThat(prop(of(Book.class).getCoAuthor().getName()),
				equalTo("Book.getCoAuthor.getName"));

	}

	@Test
	public void for_deep_call_to_primitive_int() {

		assertThat(prop(of(Book.class).getCoAuthor().getBookCount()),
				equalTo("Book.getCoAuthor.getBookCount"));

	}

}